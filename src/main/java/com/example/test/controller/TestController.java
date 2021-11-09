package com.example.test.controller;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.vo.SameWordRVO;
import com.example.test.vo.SameWordVO;

import ch.qos.logback.classic.Logger;
import common.CommonResponse;
import common.CustomResponse;
import common.ErrorResponse;

// @Controller + @ResponseBody
@RestController
public class TestController {
	// TRACE < DEBUG < INFO < WARN < ERROR
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String getTestPage() {
		String viewName = "index.html";
		return viewName;
	}
	
	@RequestMapping(value = "/getTemp", method = RequestMethod.GET)
	public String getTemp() {
		logger.debug("DEBUG");
		logger.info("INFO");
		logger.error("ERROR");
		logger.trace("TRACE");
		String value = "getTemp String"; 
		return value;
	}
	
	@RequestMapping(value = "/checkSameWord/{word}", method = RequestMethod.GET)
	public ResponseEntity<? extends CustomResponse> checkSameWordGet(@PathVariable("word") String word) {
		String answerWord = "TEST";
		final ResponseEntity responseEntity;
		
		// 값 존재
		if(Optional.ofNullable(word).isPresent()) {
			String resultMsg = word.equals(answerWord) ? "정답입니다!" : "틀렸습니다ㅠㅠ";
			boolean isSame = word.equals(answerWord);
			String resultWord = answerWord;
			
			SameWordRVO rvo = SameWordRVO.builder()
					.resultMsg(resultMsg)
					.isSame(isSame)
					.resultWord(resultWord)
					.build();
			
			return ResponseEntity.ok().body(new CommonResponse<SameWordRVO>(rvo));	
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("입력 값 오류 발생", HttpStatus.BAD_REQUEST.value()));
	}
	
	@RequestMapping(value = "/checkSameWord", method = RequestMethod.POST)
	public ResponseEntity<? extends CustomResponse> checkSameWord(@RequestBody SameWordVO pvo) {
		String answerWord = "TEST";
		final ResponseEntity responseEntity;
		
		// 값 존재
		if(Optional.ofNullable(pvo.getWord()).isPresent()) {
			String word = pvo.getWord();
			String resultMsg = word.equals(answerWord) ? "정답입니다!" : "틀렸습니다ㅠㅠ";
			boolean isSame = word.equals(answerWord);
			String resultWord = answerWord;
			
			SameWordRVO rvo = SameWordRVO.builder()
					.resultMsg(resultMsg)
					.isSame(isSame)
					.resultWord(resultWord)
					.build();
			
			return ResponseEntity.ok().body(new CommonResponse<SameWordRVO>(rvo));	
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("입력 값 오류 발생", HttpStatus.BAD_REQUEST.value()));
	}
}
