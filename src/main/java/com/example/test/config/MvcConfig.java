package com.example.test.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.test.config.annotation.PageableLimits;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		int maxSize = Integer.MAX_VALUE;
		
		/*
		 * Pageable parameter에 사용하는 Annotation Custom
		 */
		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver() {
            @Override
            public Pageable resolveArgument(MethodParameter methodParameter, @Nullable ModelAndViewContainer mavContainer,
                                            NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) {
                Pageable p = super.resolveArgument(methodParameter, mavContainer, webRequest, binderFactory);
                return getLimitsFromAnnotation(p, methodParameter);
            }

            private Pageable getLimitsFromAnnotation(Pageable p, MethodParameter methodParameter) {

            	// @interface custom한 interface로 가져온 max, min size 반영
                PageableLimits limits = methodParameter.getParameterAnnotation(PageableLimits.class);

                if (limits == null) return p;

                // max로 설정한 size보다 페이지 개수가 더 높으면, max 설정 개수를 size로 설정
                if (p.getPageSize() > limits.maxSize())
                    return PageRequest.of(p.getPageNumber(), limits.maxSize(), p.getSort());
                else if (p.getPageSize() < limits.minSize())
                    return PageRequest.of(p.getPageNumber(), limits.minSize(), p.getSort());

                return p;
            }
        };
        
        resolver.setMaxPageSize(Integer.MAX_VALUE);
        resolvers.add(resolver);
		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
	}
}
