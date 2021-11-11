package com.example.test.config.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)	// 런타임 시점까지 메모리 할당
@Target(ElementType.PARAMETER)	// 어노테이션 선언 가능 타입
public @interface PageableLimits {
	int maxSize() default 10;	// 페이지 조회 최대 개수
    int minSize() default 1;	// 페이지 조회 최소 개수
}
