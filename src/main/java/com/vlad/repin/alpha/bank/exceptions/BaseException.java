package com.vlad.repin.alpha.bank.exceptions;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BaseException extends RuntimeException {
    private final ExceptionType type;

    public BaseException(String message, ExceptionType exceptionType) {
        super(message);
        this.type = exceptionType;
    }

    public BaseException(String message, Throwable cause, ExceptionType type) {
        super(message, cause);
        this.type = type;
    }
}
