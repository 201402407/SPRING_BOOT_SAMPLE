package com.example.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.entity.KeywordEntity;

@Repository
public interface KeywordRepository extends JpaRepository<KeywordEntity, String> {

}
