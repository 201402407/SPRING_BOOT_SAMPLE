package com.example.test.vo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeywordDTO {
	
	@Autowired
	private ModelMapper modelMapper;
	
	private String keyword;	// 키워드
	private long searchCount;	// 검색횟수
}
