package com.example.test.documentapproval.dto;

import com.example.test.documentapproval.constants.DocumentStatus;
import com.example.test.documentapproval.constants.DocumentType;
import com.example.test.documentapproval.entities.Document;
import com.example.test.documentapproval.entities.Member;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@Schema(description = "Document DTO")
@NoArgsConstructor
public class DocumentDto {
	@Schema(description = "문서 ID")
	private String documentId;
	
	@Schema(description = "문서 생성한 사용자 정보")
	private Member registeredMember;
	
	@Schema(description = "문서 결재 상태")
	private DocumentStatus documentStatus;
	
	@Schema(description = "문서 분류")
	private DocumentType documentType;

	@Schema(description = "제목")
	private String title;

	@Schema(description = "내용")
	private String comment;
	
	@Schema(description = "의견")
	private String opinion;
	
	@Schema(description = "생성 시간")
	@JsonProperty("created_at")
	private LocalDateTime createdAt;
	
	@Schema(description = "수정 시간")
	@JsonProperty("updated_at")
	private LocalDateTime updatedAt;

	public DocumentDto(Document source) {
		copyProperties(source, this);
	}
}
