package com.example.test.documentapproval.dto;

import com.example.test.documentapproval.constants.DocumentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Builder
@Schema(description = "문서 생성 RequestBody")
public class CreateDocumentRequest {
	@NotBlank(message = "생성하는 사용자의 ID는 필수입니다.")
	@Schema(description = "사용자 ID", nullable = false)
	String memberId;
	
	@NotNull(message = "문서 분류는 필수입니다.")
	@Schema(description = "문서 분류", nullable = false)
	DocumentType documentType;

	@Size(max = 50, message = "문서 제목은 최대 50자까지 가능합니다.")
	@NotBlank(message = "문서 제목은 필수입니다.")
	@Schema(description = "문서 제목", nullable = false)
	String title;

	@Size(max = 255, message = "문서 내용은 최대 255자까지 가능합니다.")
	@NotBlank(message = "문서 내용은 필수입니다.")
	@Schema(description = "문서 내용", nullable = false)
	String comment;

	@NotEmpty(message = "최소 한 명 이상의 결재자가 필요합니다.")
	@Schema(description = "결재자 ID 리스트", nullable = false)
	List<String> approvalMemberIdList;
}
