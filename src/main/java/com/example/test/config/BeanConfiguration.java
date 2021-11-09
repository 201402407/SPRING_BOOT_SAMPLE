package com.example.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeanConfiguration {
	
	// application.properties에서 설정한 프로퍼티 값 불러옴
	@Value("${kakao.apikey}")
	private String kakaoApiKey;
			
	@Bean(name="kakaoWebClient")
	public WebClient kakaoWebClient() {
		return WebClient.builder()
				.baseUrl("https://dapi.kakao.com")
				.defaultHeaders(httpHeaders -> {
					httpHeaders.addAll(createKakaoHeaders());
				})
				.build();
	}
	
	// 카카오 API Header 설정
	private HttpHeaders createKakaoHeaders() {
	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoApiKey);
	    return headers;
	}
}
