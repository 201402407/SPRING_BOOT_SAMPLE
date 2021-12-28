package com.example.test.bank.dto;

import lombok.Data;

@Data
public class NaverApiItem {
	
	String title;		// 검색 결과 업체, 기관명
	String link;		// 검색 결과 업체, 기관의 상세 정보가 제공되는 네이버 페이지의 하이퍼텍스트 link
	String description;	// 검색 결과 업체, 기관명에 대한 설명
	String telephone;	// 빈 문자열 반환. 과거에 제공되던 항목이라 하위 호환성을 위해 존재
	String address;		// 검색 결과 업체, 기관명의 주소
	String roadAddress;	// 검색 결과 업체, 기관명의 도로명 주소
	Integer mapx;		// 검색 결과 업체, 기관명 위치 정보의 x좌표를 제공
	Integer maxy;		// 검색 결과 업체, 기관명 위치 정보의 y좌표를 제공
}
