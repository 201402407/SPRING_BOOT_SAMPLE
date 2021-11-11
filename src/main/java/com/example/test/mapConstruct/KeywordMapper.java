package com.example.test.mapConstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.test.entity.KeywordEntity;
import com.example.test.vo.KeywordDTO;

@Mapper
public interface KeywordMapper extends StructMapper<KeywordDTO, KeywordEntity> {
	
	// 알아서 KeywordMapper 인터페이스를 상속받는 KeywordMapperImpl 생성 됨(컴파일 시)
	KeywordMapper INSTANCE = Mappers.getMapper(KeywordMapper.class);
	
//	@Mapping(source = "~", target = "~") // 변수명이 다를 경우. source = Entity, target = DTO
//	@Mapping(target = "~", constant = "~") // 임의의 값 집어넣을 때
	/*
	@Override
	default KeywordDTO toDto(KeywordEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
//	@Mapping(target = "~", expression = "~") // Entity에만 있는 target 이기 때문에 이 target에 값 mapping 해주기. target = Entity
	/*
	@Override
	default KeywordEntity toEntity(KeywordDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
}
