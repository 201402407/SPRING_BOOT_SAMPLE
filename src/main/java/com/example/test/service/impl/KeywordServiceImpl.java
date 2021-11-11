package com.example.test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
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
				selectedKeywordEntity.incrementSearchCount();
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
				
				createkeywordEntity.firstSearching();
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

	@Override
	public List<KeywordDTO> getKeywordRankingList(Pageable pageable) throws Exception {
		try {
			// Ordering
			List<Order> orders = new ArrayList<Order>();
	        Order StartTimeOrder = new Order(Sort.Direction.DESC, "StartTime");
	        orders.add(StartTimeOrder);
	        Order progDateOrder = new Order(Sort.Direction.ASC, "ProgDate");
	        orders.add(progDateOrder);
	        
	        // Paging
			Page<KeywordEntity> keywordRankingList = keywordRepository.findAll(pageable);
			return KeywordMapper.INSTANCE.toDtoList(keywordRankingList.toList());
		}
		catch(IllegalArgumentException e) {
			throw e;
		}
		catch(Exception e) {
			throw e;
		}
	}

}
