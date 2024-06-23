package com.example.springboot.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private String code;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }
}
