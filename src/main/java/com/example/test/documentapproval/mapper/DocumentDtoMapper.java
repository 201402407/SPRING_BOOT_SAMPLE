package com.example.test.documentapproval.mapper;

import com.example.test.common.StructMapper;
import com.example.test.documentapproval.dto.DocumentDto;
import com.example.test.documentapproval.entities.Document;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DocumentDtoMapper extends StructMapper<DocumentDto, Document> {
	
	DocumentDtoMapper INSTANCE = Mappers.getMapper(DocumentDtoMapper.class);
}
