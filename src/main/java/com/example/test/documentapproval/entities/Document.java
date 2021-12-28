package com.example.test.documentapproval.entities;

import com.example.test.documentapproval.constants.DocumentStatus;
import com.example.test.documentapproval.constants.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {
	
	@Id
	@Column(name = "document_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer documentId;

	@ManyToOne
	@JoinColumn(name = "registered_member_id", referencedColumnName = "member_id")
	private Member registeredMember;
	
	@Column
	@Enumerated(EnumType.STRING)
	private DocumentStatus documentStatus;
	
	@Column
	@Enumerated(EnumType.STRING)
	private DocumentType documentType;

	@Column
	private String title;

	@Column
	private String comment;
	
	@Column
	private String opinion;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	// 의견 수정
	public void updateOpinion(String opinion) {
		this.opinion = opinion;
	}

	// 문서 결재 반려(거절)
	public void reject() {
		this.documentStatus = DocumentStatus.REJECT;
	}

	public void approve() {
		this.documentStatus = DocumentStatus.APPROVE;
	}
}
