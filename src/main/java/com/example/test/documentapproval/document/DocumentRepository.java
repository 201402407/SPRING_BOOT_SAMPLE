package com.example.test.documentapproval.document;

import com.example.test.documentapproval.constants.DocumentStatus;
import com.example.test.documentapproval.entities.Document;
import com.example.test.documentapproval.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    List<Document> findByRegisteredMember(Member member);
    List<Document> findByRegisteredMemberAndDocumentStatus(Member member, DocumentStatus status);
}