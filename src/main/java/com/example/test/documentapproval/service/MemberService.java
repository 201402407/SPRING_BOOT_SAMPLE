package com.example.test.documentapproval.service;

import com.example.test.common.exceptions.NotFoundException;
import com.example.test.documentapproval.dto.MemberDto;
import com.example.test.documentapproval.entities.Member;
import com.example.test.documentapproval.mapper.MemberDtoMapper;
import com.example.test.documentapproval.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class MemberService {
	
	/*
	 * 생성자 주입
	 */
	private final MemberRepository memberRepository;
  
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	

	@Transactional(readOnly = true)
	public Optional<Member> findByMemberId(String memberId) {
		return memberRepository.findByMemberId(memberId);
	}
	
	@Transactional(readOnly = true)
	public Optional<Member> findByMemberIdAndPwd(String memberId, String pwd) {
		return memberRepository.findByMemberIdAndPwd(memberId, pwd);
	}
	
	@Transactional(readOnly = true)
	public Member validateMemberId(String memberId) {
		return this.findByMemberId(memberId)
				.orElseThrow(() -> new NotFoundException("해당 ID로 가입한 사용자가 존재하지 않습니다."));
	}
	
	@Transactional(readOnly = true)
	public Member validateMemberIdAndPwd(String memberId, String pwd) {
	  return this.findByMemberIdAndPwd(memberId, pwd)
			  .orElseThrow(() -> new IllegalArgumentException("ID 또는 비밀번호가 일치하지 않습니다."));
	  }

	@Transactional
	public MemberDto join(String memberId, String pwd, String name) {
		  checkNotNull(memberId, "ID는 필수입니다.");
		  checkNotNull(pwd, "비밀번호는 필수입니다.");
		  checkNotNull(name, "이름은 필수입니다.");
		  
		  Member member = Member.builder()
				  .memberId(memberId)
				  .pwd(pwd)
				  .name(name)
				  .build();
		  
		  this.memberRepository.save(member);
		  MemberDto memberDto = MemberDtoMapper.INSTANCE.toDto(member);
		  return memberDto;
	}
	
	@Transactional
	public MemberDto login(String memberId, String pwd) {
		checkNotNull(memberId, "ID는 필수입니다.");
		checkNotNull(pwd, "비밀번호는 필수입니다.");
		
		Member member = validateMemberIdAndPwd(memberId, pwd);
		MemberDto memberDto = MemberDtoMapper.INSTANCE.toDto(member);
		return memberDto;
	}
}
