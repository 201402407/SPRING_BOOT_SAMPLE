package com.example.test.documentapproval.repository;

import com.example.test.documentapproval.entities.Document;
import com.example.test.documentapproval.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
	Optional<Member> findByMemberId(String memberId);
	Optional<Member> findByMemberIdAndPwd(String memberId, String pwd);

	// N+1 문제를 해결하기 위해 Join Fetch 사용
	@Query("select distinct m from Member m join fetch m.documentList")
	List<Member> findAllWithFetchJoin();

//	@Query("select m from Member m join fetch m.documentList join fetch m.tempList")
//	List<Member> findAllWithFetchTwoJoin();
}
