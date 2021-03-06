package com.example.test.bank.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.test.config.annotation.PageableLimits;
import com.example.test.bank.entity.KeywordEntity;
import com.example.test.bank.repository.KeywordRepository;
import com.example.test.bank.service.KeywordService;
import com.example.test.bank.dto.KakaoWebClientRVO;
import com.example.test.bank.dto.KeywordDTO;
import com.example.test.bank.dto.NaverWebClientRVO;
import com.example.test.bank.dto.SameWordRVO;
import com.example.test.bank.dto.SameWordVO;
import com.example.test.bank.dto.SearchVO;

import ch.qos.logback.classic.Logger;
import com.example.test.bank.common.CommonResponse;
import com.example.test.bank.common.CustomResponse;
import com.example.test.bank.common.ErrorResponse;
import reactor.core.publisher.Mono;

// @Controller + @ResponseBody
@RestController
public class TestController {
	// TRACE < DEBUG < INFO < WARN < ERROR
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	// ???????????? ????????? ??? ?????? ????????? ?????? ??????
	@Autowired
	@Qualifier("kakaoWebClient")
	private WebClient kakaoWebClient;
	
	@Autowired
	@Qualifier("naverWebClient")
	private WebClient naverWebClient;
	
	@Autowired
	private KeywordService keywordService;
	
	@Autowired
	private KeywordRepository keywordRepository;
	
//    @PostConstruct
//    public void initializing() {
//        for (int i = 0; i < 97; i++) {
//            KeywordEntity keyword = KeywordEntity.builder()
//            		.keyword("TEMP" + i)
//            		.searchCount(i / 10)
//                    .build();
//            keywordRepository.save(keyword);
//        }
//    }
    
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String getTestPage() {
		String viewName = "index.html";
		return viewName;
	}
	
	// Content-type : Application/json
	@RequestMapping(value = "/getKakaoLocalForKeyword/{keyword}/{format}", method = RequestMethod.GET)
	public Mono<KakaoWebClientRVO> getKakaoLocalForKeyword(@PathVariable("keyword") String keyword, @PathVariable("format") String format) {
		// PathVariable??? null??? ?????? -> ????????? 405 ERROR
		Mono<KakaoWebClientRVO> kakaoApiResult = kakaoWebClient.mutate()
			.build()	// ????????? Bean?????????, ?????? ????????? ?????? ??? ?????? ?????? ???????????? ??????
			.get()
			.uri(	// baseURL ?????? URI Custom Building
					uriBuilder -> uriBuilder.path("/v2/local/search/keyword.{format}")
					.queryParam("query", keyword)
					.queryParam("page", 1)	// default: 1
					.queryParam("size", 5)	// 5?????? ??????
					.build(format))
			.retrieve()
			.onStatus(
					httpStatus -> httpStatus != HttpStatus.OK,
				    clientResponse -> {
				      return clientResponse.createException()
				         .flatMap(it -> Mono.error(new RuntimeException("code : " + clientResponse.statusCode())));
				      })
			.bodyToMono(KakaoWebClientRVO.class)
			.onErrorResume(throwable -> {	// ?????? ?????? ???
				return Mono.error(new RuntimeException(throwable));
			});
		
		return kakaoApiResult;
	}
	
	// Content-type : Application/json
	@RequestMapping(value = "/getNaverLocalForKeyword/{keyword}/{format}", method = RequestMethod.GET)
	public Mono<NaverWebClientRVO> getNaverLocalForKeyword(@PathVariable("keyword") String keyword, @PathVariable("format") String format) {
		// PathVariable??? null??? ?????? -> ????????? 405 ERROR
		Mono<NaverWebClientRVO> naverApiResult = naverWebClient.mutate()
			.build()	// ????????? Bean?????????, ?????? ????????? ?????? ??? ?????? ?????? ???????????? ??????
			.get()
			.uri(	// baseURL ?????? URI Custom Building
					uriBuilder -> uriBuilder.path("/v1/search/local.{format}")
					.queryParam("query", keyword)
					.queryParam("start", 1)	// default: 1
					.queryParam("display", 29)	// 5?????? ??????
					.build(format))
			.retrieve()
			.onStatus(
					httpStatus -> httpStatus != HttpStatus.OK,
				    clientResponse -> {
				      return clientResponse.createException()
				         .flatMap(it -> Mono.error(new RuntimeException("code : " + clientResponse.statusCode())));
				      })
			.bodyToMono(NaverWebClientRVO.class)
//			.retry()	// Error ?????? ??? ?????????.
			.onErrorResume(throwable -> {	// ?????? ?????? ??? ????????? Catch?????? ?????? Workflow ??????
				return Mono.error(new RuntimeException(throwable));
			});
		
		return naverApiResult;
	}
	
	@RequestMapping(value = "/getTemp", method = RequestMethod.GET)
	public String getTemp() {
		logger.debug("DEBUG");
		logger.info("INFO");
		logger.error("ERROR");
		logger.trace("TRACE");
		String value = "SUDO??? ????????? ??????????????? ?????????";
		return value;
	}
	
	@RequestMapping(value = "/checkSameWord/{word}", method = RequestMethod.GET)
	public ResponseEntity<? extends CustomResponse> checkSameWordGet(@PathVariable("word") String word) {
		String answerWord = "TEST";
		
		// ??? ??????
		if(Optional.ofNullable(word).isPresent()) {
			String resultMsg = word.equals(answerWord) ? "???????????????!" : "?????????????????????";
			boolean isSame = word.equals(answerWord);
			String resultWord = answerWord;
			
			SameWordRVO rvo = SameWordRVO.builder()
					.resultMsg(resultMsg)
					.isSame(isSame)
					.resultWord(resultWord)
					.build();
			
			return ResponseEntity.ok().body(new CommonResponse<SameWordRVO>(rvo));	
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("?????? ??? ?????? ??????", HttpStatus.BAD_REQUEST.value()));
	}
	
	// Content-type : application-json ??????
	// @RequestBody ??????
	@RequestMapping(value = "/checkSameWord", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<? extends CustomResponse> checkSameWordJsonRequest (@RequestBody SameWordVO pvo) {
		String answerWord = "TEST";
		
		// ??? ??????
		if(Optional.ofNullable(pvo.getWord()).isPresent()) {
			String word = pvo.getWord();
			String resultMsg = word.equals(answerWord) ? "???????????????!" : "?????????????????????";
			boolean isSame = word.equals(answerWord);
			String resultWord = answerWord;
			
			SameWordRVO rvo = SameWordRVO.builder()
					.resultMsg(resultMsg)
					.isSame(isSame)
					.resultWord(resultWord)
					.build();
			
			return ResponseEntity.ok().body(new CommonResponse<SameWordRVO>(rvo));	
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("?????? ??? ?????? ??????", HttpStatus.BAD_REQUEST.value()));
	}
	
	// Content-type : x-www-form-urlencoded(ex. html form tag)
	// @ModelAttribute ?????? ??????
	@RequestMapping(value = "/checkSameWord", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<? extends CustomResponse> checkSameWordFormRequest (@ModelAttribute SameWordVO pvo) {
		String answerWord = "TEST";
		
		// ??? ??????
		if(Optional.ofNullable(pvo.getWord()).isPresent()) {
			String word = pvo.getWord();
			String resultMsg = word.equals(answerWord) ? "???????????????!" : "?????????????????????";
			boolean isSame = word.equals(answerWord);
			String resultWord = answerWord;
			
			SameWordRVO rvo = SameWordRVO.builder()
					.resultMsg(resultMsg)
					.isSame(isSame)
					.resultWord(resultWord)
					.build();
			
			return ResponseEntity.ok().body(new CommonResponse<SameWordRVO>(rvo));	
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("?????? ??? ?????? ??????", HttpStatus.BAD_REQUEST.value()));
	}
	
	// Content-type : application-json ??????
	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<? extends CustomResponse> search (@RequestBody @Valid SearchVO pvo) {
		
		try {
			KeywordDTO resultDto = keywordService.search(pvo);
			return ResponseEntity.ok().body(new CommonResponse<KeywordDTO>(resultDto));
		}
		catch(IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("?????? ??? ?????? ??????", HttpStatus.BAD_REQUEST.value()));
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("?????? ?????? ??????", HttpStatus.INTERNAL_SERVER_ERROR.value()));
		}
	}
	
	// Paging URI Parameter??? ???????????? ??????
	@RequestMapping(value = "/bestKeywordList", method = RequestMethod.GET)
	public ResponseEntity<? extends CustomResponse> bestKeywordList (
			@PageableDefault(size = 30)
			@PageableLimits(maxSize = 10)
			@SortDefault.SortDefaults({
					@SortDefault(sort = "searchCount", direction = Sort.Direction.DESC),
					@SortDefault(sort = "keyword", direction = Sort.Direction.ASC)
			}) Pageable pageable) {
		try {
			List<KeywordDTO> resultDto = keywordService.getKeywordRankingList(pageable);
			return ResponseEntity.ok().body(new CommonResponse<List<KeywordDTO>>(resultDto));	
		}
		catch(IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("?????? ??? ?????? ??????", HttpStatus.BAD_REQUEST.value()));
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("?????? ?????? ??????", HttpStatus.INTERNAL_SERVER_ERROR.value()));
		}
	}
}
