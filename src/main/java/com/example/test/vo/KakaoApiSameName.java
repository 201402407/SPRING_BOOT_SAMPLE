package com.example.test.vo;

import lombok.Data;

@Data
public class KakaoApiSameName {
	String[] region;	// 질의어에서 인식된 지역의 리스트. 예: '중앙로 맛집' 에서 중앙로에 해당하는 지역 리스트
	String keyword;	// 질의어에서 지역 정보를 제외한 키워드. 예: '중앙로 맛집' 에서 '맛집'
	String selectedRegion;	// 인식된 지역 리스트 중, 현재 검색에 사용된 지역 정보
}
