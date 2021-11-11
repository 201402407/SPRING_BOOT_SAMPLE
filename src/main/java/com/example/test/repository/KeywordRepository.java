package com.example.test.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.entity.KeywordEntity;

@Repository
public interface KeywordRepository extends JpaRepository<KeywordEntity, String> {
	
	// 검색 횟수 상위 10개 리스트 조회
//	@Query(value = "SELECT * FROM keyword_result p ORDER BY p.search_count DESC", nativeQuery = true)
	List<KeywordEntity> findTop10ByOrderBySearchCountDesc();

	// 검색 횟수 상위 10개 리스트 조회
//	@Query(value = "SELECT * FROM keyword_result p ORDER BY p.search_count DESC", nativeQuery = true)
	Page<KeywordEntity> findAll(Pageable limitOneHundred);
}
