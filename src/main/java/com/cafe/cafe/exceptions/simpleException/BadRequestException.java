package com.cafe.cafe.exceptions.simpleException;

public class BadRequestException extends RequestException{

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(String message) {
        super(message);
    }

}
