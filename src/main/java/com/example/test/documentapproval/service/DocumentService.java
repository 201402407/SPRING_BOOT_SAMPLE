package com.example.test.documentapproval.service;

import com.example.test.common.exceptions.NotFoundException;
import com.example.test.documentapproval.constants.DocumentStatus;
import com.example.test.documentapproval.constants.DocumentType;
import com.example.test.documentapproval.repository.DocumentRepository;
import com.example.test.documentapproval.dto.DocumentDto;
import com.example.test.documentapproval.entities.Document;
import com.example.test.documentapproval.mapper.DocumentDtoMapper;
import com.example.test.documentapproval.repository.MemberRepository;
import com.example.test.documentapproval.entities.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class DocumentService {
	
	/*
	 * 생성자 주입
	 */
	private final DocumentRepository documentRepository;
	private final MemberRepository memberRepository;

	public DocumentService(DocumentRepository documentRepository, MemberRepository memberRepository) {
		this.documentRepository = documentRepository;
		this.memberRepository = memberRepository;
	}

	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	public Document validateDocument(int documentId) {
		return this.documentRepository.findById(documentId)
				.orElseThrow(() -> new NotFoundException("해당 문서 정보가 존재하지 않습니다."));
	}

	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	public Member validateMember(String memberId) {
		return this.memberRepository.findByMemberId(memberId)
				.orElseThrow(() -> new NotFoundException("해당 사용자의 정보가 존재하지 않습니다."));
	}

	@Transactional
	public DocumentDto create(String memberId, DocumentType documentType, String title, String comment, List<String> approvalMemberIdList) {
		checkNotNull(memberId, "생성하는 사용자의 ID는 필수입니다.");
		checkNotNull(documentType, "문서 분류는 필수입니다.");
		checkNotNull(comment, "문서 내용은 필수입니다.");
		checkArgument(!approvalMemberIdList.isEmpty(), "최소 한 명 이상의 결재자가 필요합니다.");

		Member registeredMember = validateMember(memberId);

		Document document = Document.builder()
				.registeredMember(registeredMember)
				.documentStatus(DocumentStatus.PROGRESS)
				.documentType(documentType)
				.title(title)
				.comment(comment)
				.opinion("")
				.build();

		this.documentRepository.save(document);

		DocumentDto documentDto = DocumentDtoMapper.INSTANCE.toDto(document);
		return documentDto;
	}
}
