package com.example.test.mapConstruct;

import com.example.test.entity.KeywordEntity;
import com.example.test.entity.KeywordEntity.KeywordEntityBuilder;
import com.example.test.vo.KeywordDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class KeywordMapperImpl implements KeywordMapper {

    @Override
    public KeywordDTO toDto(KeywordEntity entity) {
        if ( entity == null ) {
            return null;
        }

        KeywordDTO keywordDTO = new KeywordDTO();

        keywordDTO.setKeyword( entity.getKeyword() );
        keywordDTO.setSearchCount( entity.getSearchCount() );

        return keywordDTO;
    }

    @Override
    public KeywordEntity toEntity(KeywordDTO dto) {
        if ( dto == null ) {
            return null;
        }

        KeywordEntityBuilder keywordEntity = KeywordEntity.builder();

        keywordEntity.keyword( dto.getKeyword() );
        keywordEntity.searchCount( dto.getSearchCount() );

        return keywordEntity.build();
    }

    @Override
    public List<KeywordDTO> toDtoList(List<KeywordEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<KeywordDTO> list = new ArrayList<KeywordDTO>( entityList.size() );
        for ( KeywordEntity keywordEntity : entityList ) {
            list.add( toDto( keywordEntity ) );
        }

        return list;
    }

    @Override
    public List<KeywordEntity> toEntityList(List<KeywordDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<KeywordEntity> list = new ArrayList<KeywordEntity>( dtoList.size() );
        for ( KeywordDTO keywordDTO : dtoList ) {
            list.add( toEntity( keywordDTO ) );
        }

        return list;
    }
}
