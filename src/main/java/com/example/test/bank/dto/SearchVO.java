package com.example.test.bank.dto;


import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchVO {
	
	@NotBlank(message = "공백 없이 검색어를 입력해야 합니다.") // NULL, "", 빈 문자열 X
	String keyword;
}
