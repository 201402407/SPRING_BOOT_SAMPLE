package com.example.test.bank.dto;

import lombok.Data;

@Data
public class KakaoApiDocument {
	String id;	// 장소 ID
	String placeName;	// 장소명, 업체명
	String categoryName;	// 카테고리 이름
	String categoryGroupCode;	// 중요 카테고리만 그룹핑한 카테고리 그룹 코드
	String categoryGroupName;	// 중요 카테고리만 그룹핑한 카테고리 그룹명
	String phone;	// 전화번호
	String addressName;	// 전체 지번 주소
	String roadAddressName;	// 전체 도로명 주소
	String x;	// X 좌표값, 경위도인 경우 longitude (경도)
	String y;	// Y 좌표값, 경위도인 경우 latitude(위도)
	String placeUrl;	// 장소 상세페이지 URL
	String distance;	// 중심좌표까지의 거리 (단, x,y 파라미터를 준 경우에만 존재) - 단위: meter
}
