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
}
