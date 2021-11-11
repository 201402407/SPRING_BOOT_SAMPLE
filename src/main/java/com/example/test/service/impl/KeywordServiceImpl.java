package com.example.test.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.entity.KeywordEntity;
import com.example.test.mapConstruct.KeywordMapper;
import com.example.test.repository.KeywordRepository;
import com.example.test.service.KeywordService;
import com.example.test.vo.KeywordDTO;
import com.example.test.vo.SearchVO;

@Service
public class KeywordServiceImpl implements KeywordService {
	
	@Autowired
	private KeywordRepository keywordRepository;

	@Override
	@Transactional
	public KeywordDTO search(SearchVO pvo) throws Exception {
		String keyword = pvo.getKeyword();
		try {
			KeywordDTO dto = new KeywordDTO();
			
			Optional<KeywordEntity> keywordEntity = keywordRepository.findById(keyword);
			if(keywordEntity.isPresent()) {
				KeywordEntity selectedKeywordEntity = keywordEntity.get();
				// count +1로 DB에 update
				selectedKeywordEntity.setSearchCount(selectedKeywordEntity.getSearchCount() + 1);
				KeywordEntity resultEntity = keywordRepository.save(selectedKeywordEntity);
				dto = KeywordMapper.INSTANCE.toDto(resultEntity);
//				dto = KeywordDTO.EntityToDto(resultEntity);
			}
			else {
				// count 1로 DB에 create
				KeywordEntity createkeywordEntity = KeywordEntity.builder()
						.keyword(keyword)
						.searchCount(1)
						.build();
				KeywordEntity resultEntity = keywordRepository.save(createkeywordEntity);
				dto = KeywordMapper.INSTANCE.toDto(resultEntity);
//				dto = KeywordDTO.EntityToDto(resultEntity);
			}
			
			return dto;
		}
		catch(IllegalArgumentException e) {
			throw e;
		}
		catch(Exception e) {
			throw e;
		}
	}

}
