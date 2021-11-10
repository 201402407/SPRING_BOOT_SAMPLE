package com.example.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

@Configuration
public class BeanConfiguration {
	
	// application.properties에서 설정한 프로퍼티 값 불러옴
	@Value("${kakao.apikey}")
	private String kakaoApiKey;
	@Value("${naver.clientId}")
	private String naverClientId;
	@Value("${naver.clientSecret}")
	private String naverClientSecret;
	
			
	@Bean(name="kakaoWebClient")
	public WebClient kakaoWebClient(ObjectMapper baseConfig) {
		return WebClient.builder()
				.baseUrl("https://dapi.kakao.com")
				.exchangeStrategies(jacksonCodecStrategies(baseConfig))
				.defaultHeaders(httpHeaders -> {
					httpHeaders.addAll(createKakaoHeaders());
				})
				.build();
	}
	
	@Bean(name="naverWebClient")
	public WebClient naverWebClient(ObjectMapper baseConfig) {
		return WebClient.builder()
				.baseUrl("https://openapi.naver.com")
//				.exchangeStrategies(jacksonCodecStrategies(baseConfig))	// Naver API는 default가 Camel Case
				.defaultHeaders(httpHeaders -> {
					httpHeaders.addAll(createNaverHeaders());
				})
				.build();
	}
	
	// Json decoding(역직렬화) 시 SNAKE_CASE to CamelCase 변환
	private ExchangeStrategies jacksonCodecStrategies(ObjectMapper baseConfig) {
		ObjectMapper newMapper = baseConfig.copy();
        newMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		return ExchangeStrategies.builder()
                .codecs(configurer ->
                        configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(newMapper)))
                .build();
	}
	
	// 카카오 API Header 설정
	private HttpHeaders createKakaoHeaders() {
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoApiKey);
	    return headers;
	}
	
	// 네이버 API Header 설정
	private HttpHeaders createNaverHeaders() {
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.ACCEPT, "*/*");
	    headers.add("X-Naver-Client-Id", naverClientId);
	    headers.add("X-Naver-Client-Secret", naverClientSecret);
	    return headers;
	}
}
