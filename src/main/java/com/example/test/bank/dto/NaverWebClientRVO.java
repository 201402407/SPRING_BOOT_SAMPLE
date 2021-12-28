package com.example.test.bank.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class NaverWebClientRVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	<title>Naver Open API - local ::'갈비집'</title>
//    <link>http://search.naver.com</link>
//    <description>Naver Search Result</description>
//    <lastBuildDate>Tue, 04 Oct 2016 13:10:58 +0900</lastBuildDate>
//    <total>407</total>
//    <start>1</start>
//    <display>10</display>
//    <item>
//        <title>조선옥</title>
//        <link />
//        <category>한식&gt;육류,고기요리</category>
//        <description>연탄불 한우갈비 전문점.</description>
//        <telephone></telephone>
//        <address>서울특별시 중구 을지로3가 229-1 </address>
//        <roadAddress>서울특별시 중구 을지로15길 6-5 </roadAddress>
//        <mapx>311277</mapx>
//        <mapy>552097</mapy>
//    </item>
	
	Integer total;		// 검색 결과 문서의 총 개수
	Integer start;		// 검색 결과 문서 중, 문서의 시작점
	Integer display;	// 검색된 검색 결과의 개수
	String category;	// 검색 결과 업체, 기관의 분류 정보를 제공
	NaverApiItem[] items;	// 검색 결과 아이템 리스트
}
