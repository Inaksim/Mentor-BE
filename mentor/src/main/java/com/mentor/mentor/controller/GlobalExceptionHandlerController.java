package com.mentor.mentor.controller;


import com.mentor.mentor.exception.ApplicationException;
import com.mentor.mentor.service.GlobalExceptionService;
import com.mentor.mentor.service.impl.GlobalExceptionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RestControllerAdvice
@AllArgsConstructor(onConstructor = @__({@org.springframework.beans.factory.annotation.Autowired}))
public class GlobalExceptionHandlerController {

    private final GlobalExceptionService globalExceptionService;
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleExceptions(AuthenticationException e, WebRequest request) {
        return globalExceptionService.handleException(e, request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleExceptions(HttpMessageNotReadableException e) {
        return globalExceptionService.handleException(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleExceptions(MethodArgumentNotValidException e) {
        return globalExceptionService.handleException(e);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<Map<String, Object>> handleExceptions(ApplicationException e, WebRequest request) {
        return globalExceptionService.handleException(e, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleExceptions(Throwable e, WebRequest request) {
        return globalExceptionService.handleException(e, request);
    }


}
