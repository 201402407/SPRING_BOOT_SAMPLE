package com.example.test.documentapproval.constants;

import com.example.test.common.EnumType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DocumentType implements EnumType {
	COST_SETTLEMENT("비용정산"),
	VACATION("연차"),
	COOPERATION("협조");
	
	@Getter
	private final String title;
	
	@Override
	public String getCode() {
		return name();
	}
}
