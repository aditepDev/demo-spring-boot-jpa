package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorAdviser {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(HttpServletRequest request, BaseException e) {
        String path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        String url = String.format("%s", path);

        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        ErrorResponse response = new ErrorResponse();
        response.setError(e.getMessage());
        response.setStatus(e.getHttpStatus().value());
        response.setUrl(url);
        return new ResponseEntity<>(response, e.getHttpStatus());
    }

    @Getter
    @Setter
    public static class ErrorResponse {

        private LocalDateTime timestamp = LocalDateTime.now();
        private int status;
        private String error;
        private String url;
    }
}
