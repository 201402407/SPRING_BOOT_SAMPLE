package com.example.test.common;

import java.util.List;

// MapStruct 인터페이스
public interface StructMapper<DTO, Entity> {
	
	DTO toDto(Entity entity);
	Entity toEntity(DTO dto);
	List<DTO> toDtoList(List<Entity> entityList);
	List<Entity> toEntityList(List<DTO> dtoList);
}
