package com.example.test.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeywordDTO {
	
	private String keyword;	// 키워드
	private long searchCount;	// 검색횟수
}
