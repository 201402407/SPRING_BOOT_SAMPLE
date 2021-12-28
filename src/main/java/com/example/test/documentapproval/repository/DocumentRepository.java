package com.example.test.documentapproval.repository;

import com.example.test.documentapproval.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

    // N+1 문제를 해결하기 위해 Join Fetch 사용
    @Query("select doc from Document doc join fetch doc.registeredMember")
    List<Document> findAllWithFetchJoin();
}