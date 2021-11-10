package com.example.test.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.entity.KeywordEntity;
import com.example.test.repository.KeywordRepository;
import com.example.test.service.KeywordService;
import com.example.test.vo.SearchVO;

@Service
public class KeywordServiceImpl implements KeywordService {
	
	@Autowired
	private KeywordRepository keywordRepository;

	@Override
	@Transactional
	public void search(SearchVO pvo) throws Exception {
		String keyword = pvo.getKeyword();
		try {
			Optional<KeywordEntity> keywordEntity = keywordRepository.findById(keyword);
			keywordEntity.ifPresentOrElse(selectedKeywordEntity -> {
				// count +1로 DB에 update
				selectedKeywordEntity.setSearchCount(selectedKeywordEntity.getSearchCount() + 1);
				keywordRepository.save(selectedKeywordEntity);
			}, 
			() -> {
				// count 1로 DB에 create
				KeywordEntity createkeywordEntity = KeywordEntity.builder()
						.keyword(keyword)
						.searchCount(1)
						.build();
				keywordRepository.save(createkeywordEntity);
			});
		}
		catch(IllegalArgumentException e) {
			throw e;
		}
		catch(Exception e) {
			throw e;
		}
	}

}
