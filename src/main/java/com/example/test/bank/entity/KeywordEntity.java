package com.example.test.bank.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter // Entity Setter 금지 -> 별도 메소드를 만들어, 변경에 대해 명확히 인지 팔요
@Entity
@DynamicInsert  // 변경된 필드만 적용(세팅되지 않은 필드는 NULL로)
@Table(name="SEARCH_RESULT")
@AllArgsConstructor
@NoArgsConstructor
public class KeywordEntity {
	
	@Id
	private String keyword;	// 키워드
	
	private long searchCount;	// 검색횟수
	
	// 첫 검색인 경우
	public void firstSearching() {
		this.searchCount = 1;
	}
	
	// 검색 시 검색 횟수 증가
	public void incrementSearchCount() {
		this.searchCount += 1;
	}
}
