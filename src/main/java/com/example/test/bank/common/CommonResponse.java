package com.example.test.bank.common;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/*
 * Status 성공 시 Response Body
 */
@Getter
@Setter
public class CommonResponse<T> extends CustomResponse {
	private int resultCount;
	private T data;
	
	public CommonResponse(T data) {
		this.data = data;
		// List인 경우 원소 개수를, 객체인 경우는 1 대입
		resultCount = data instanceof List ? ((List<?>) data).size() : 1;
	}
}
