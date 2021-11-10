package com.example.test.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Data;

@Data
@Entity
@DynamicInsert  // 변경된 필드만 적용(세팅되지 않은 필드는 NULL로)
@Table(name="SEARCH_RESULT")
public class KeywordEntity {
	
	@Id
	private String keyword;	// 키워드
	
	private long searchCount;	// 검색횟수
	
}
