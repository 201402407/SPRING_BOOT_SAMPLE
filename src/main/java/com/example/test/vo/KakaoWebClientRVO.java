package com.example.test.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class KakaoWebClientRVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	KakaoApiMeta meta;
	KakaoApiDocument[] documents;
}
