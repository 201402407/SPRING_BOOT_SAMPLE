package com.example.test.service;

import com.example.test.vo.KeywordDTO;
import com.example.test.vo.SearchVO;

public interface KeywordService {

	/*
     * 검색 기록 저장(횟수)
     */
	public KeywordDTO search(SearchVO pvo) throws Exception;
	
}
