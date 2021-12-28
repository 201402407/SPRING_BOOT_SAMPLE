package com.example.test.documentapproval.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	
	@Id
	@Column(name = "member_id")
	private String memberId;
	
	@Column
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String pwd;
	
	@Column
	private String name;

	/*
	양방향이 아니지만, N+1 테스트를 위해 임시로 추가
	 */
	@BatchSize(size = 3)	// 이를 사용하기 위해선 Fetch Type을 EAGER로 변경해야함
	@JsonIgnore //JSON 변환시 무한 루프 방지용
	// default Fetch Type: LAZY
	@OneToMany(mappedBy = "registeredMember", targetEntity = Document.class, fetch = FetchType.EAGER)
	private List<Document> documentList = new ArrayList<>();

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
}
