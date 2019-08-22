package com.exchnage.service.currencyexchangedemo;

import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandling {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public String handleIOException(Exception ex)
	{
		logger.error("error has occoured");
		return "exception has occured"+ex.toString();
	}

	
}
