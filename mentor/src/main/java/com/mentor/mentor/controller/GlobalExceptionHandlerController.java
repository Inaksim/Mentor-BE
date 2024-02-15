package com.mentor.mentor.controller;


import com.mentor.mentor.service.impl.GlobalExceptionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RestControllerAdvice
@AllArgsConstructor(onConstructor = @__({@org.springframework.beans.factory.annotation.Autowired}))
public class GlobalExceptionHandlerController {

    private final GlobalExceptionServiceImpl globalExceptionServiceIml;
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleExceptions(Throwable e, WebRequest request) {
        return globalExceptionServiceIml.handleException(e, request);
    }
}
