package com.example.test.documentapproval.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(description = "Member DTO")
public class MemberDto {
	@Schema(description = "사용자 ID")
	private String memberId;
	
	@Schema(description = "비밀번호")
	private String pwd;
	
	@Schema(description = "이름")
	private String name;
	
	@Schema(description = "생성 시간")
	@JsonProperty("created_at")
	private LocalDateTime createdAt;
	
	@Schema(description = "수정 시간")
	@JsonProperty("updated_at")
	private LocalDateTime updatedAt;
}
