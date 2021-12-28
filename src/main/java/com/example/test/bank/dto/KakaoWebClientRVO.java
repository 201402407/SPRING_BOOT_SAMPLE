package com.example.test.bank.dto;

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
