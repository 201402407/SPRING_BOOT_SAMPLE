package com.example.test.bank.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.test.bank.dto.KeywordDTO;
import com.example.test.bank.dto.SearchVO;

public interface KeywordService {

	/*
     * 검색 기록 저장(횟수)
     */
	public KeywordDTO search(SearchVO pvo) throws Exception;
	
	/*
     * 검색어 순위 리스트 조회(횟수)
     */
	public List<KeywordDTO> getKeywordRankingList(Pageable pageable) throws Exception;
}
