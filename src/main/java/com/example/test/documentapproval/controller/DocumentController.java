package com.example.test.documentapproval.controller;

import com.example.test.documentapproval.dto.CreateDocumentRequest;
import com.example.test.documentapproval.dto.DocumentDto;
import com.example.test.documentapproval.mapper.DocumentDtoMapper;
import com.example.test.documentapproval.service.DocumentService;
import com.example.test.utils.ApiUtils.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static com.example.test.utils.ApiUtils.success;

@RestController
@RequestMapping("/api/documents")
@Tag(name="Document", description="문서 관련 API")
public class DocumentController {

	/*
	 * 생성자 주입
	 */
	private final DocumentService documentService;
	
	public DocumentController(DocumentService documentService) {
		this.documentService = documentService;	
	}
	
	@GetMapping(value = "/{documentId}")
	@Operation(summary = "문서 상세정보 조회", description = "문서 1개의 상세정보 조회")
	public ApiResult<DocumentDto> findDocument (
			@NotBlank(message = "문서 ID는 필수입니다.") 
			@Parameter(description="문서 ID")
			@PathVariable("documentId") Integer documentId
	) {
		return success(
				DocumentDtoMapper.INSTANCE.toDto(this.documentService.validateDocument(documentId))
		);
	}
	
	@PostMapping
	@Operation(summary = "문서 생성", description = "로그인한 사용자의 신규 문서 생성")
	public ApiResult<DocumentDto> create (
			@Valid @RequestBody CreateDocumentRequest body
	) {
		DocumentDto documentDto = documentService.create(body.getMemberId(), body.getDocumentType(), body.getTitle(), body.getComment(), body.getApprovalMemberIdList());
		return success(documentDto);
	}
}
