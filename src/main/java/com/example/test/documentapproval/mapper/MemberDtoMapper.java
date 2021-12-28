package com.example.test.documentapproval.mapper;

import com.example.test.common.StructMapper;
import com.example.test.documentapproval.dto.MemberDto;
import com.example.test.documentapproval.entities.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberDtoMapper extends StructMapper<MemberDto, Member> {
	
	MemberDtoMapper INSTANCE = Mappers.getMapper(MemberDtoMapper.class);
}
