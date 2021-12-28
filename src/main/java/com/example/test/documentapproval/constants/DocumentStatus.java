package com.example.test.documentapproval.constants;

import com.example.test.common.EnumType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DocumentStatus implements EnumType {
	PROGRESS("진행중"),
	APPROVE("승인"),
	REJECT("거절");
	
	@Getter
	private final String title;
	
	@Override
	public String getCode() {
		return name();
	}
}
