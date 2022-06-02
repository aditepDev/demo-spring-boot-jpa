package com.demo.shop.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerMapping;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ErrorAdviser {
	
    @ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleBaseException(HttpServletRequest request,Exception e) {
    	String path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
		String pathUrl = String.format("%s", path);
		log.error("ERROR : {} [IP : {} ]", pathUrl, request.getRemoteAddr());
    	log.error("{}",e);
		ErrorResponse response = new ErrorResponse();
		response.setError(e.getMessage());
		response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
		response.setUrl(pathUrl);
		return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
	}

	@Data
	public static class ErrorResponse {

		private LocalDateTime timestamp = LocalDateTime.now();
		private int status;
		private String error;
		private String url;
	}
}
