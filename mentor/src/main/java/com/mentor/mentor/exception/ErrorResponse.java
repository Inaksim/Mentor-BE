package com.mentor.mentor.exception;

import org.springframework.http.HttpStatus;

public interface ErrorResponse {

    String getKey();
    String getMessage();
    HttpStatus getHttpStatus();
}
