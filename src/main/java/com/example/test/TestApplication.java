package com.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@EnableAutoConfiguration // 해당 패키지를 root로, 하위 패키지의 @Configuration, @Component 클래스를 찾아 설정 등록
@SpringBootApplication // 해당 클래스를 최상위 패키지로 설정
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}
