package com.example.base.core.exception;

import lombok.Getter;

@Getter
public class APIException extends RuntimeException {
    public APIException(String message, APIExceptionType type) {
        super(message);
        this.type = type;
    }

    private APIExceptionType type;
}
