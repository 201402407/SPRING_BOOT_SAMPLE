package com.example.test.controller;

import org.slf4j.LoggerFactory;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;

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
}
