package com.example.test.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoApiMeta {
	KakaoApiSameName sameName;	// 질의어의 지역 및 키워드 분석 정보
	Integer pageableCount;	// total_count 중 노출 가능 문서 수 (최대값: 45)
	Integer totalCount;	// 검색어에 검색된 문서 수. 
	Boolean isEnd;	// 현재 페이지가 마지막 페이지인지 여부. 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음
}
