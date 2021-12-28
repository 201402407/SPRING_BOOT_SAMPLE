package com.example.test.documentapproval.repository;

import com.example.test.documentapproval.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
	Optional<Member> findByMemberId(String memberId);
	Optional<Member> findByMemberIdAndPwd(String memberId, String pwd);
}
