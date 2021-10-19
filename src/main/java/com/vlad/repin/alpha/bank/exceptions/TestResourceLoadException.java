package com.vlad.repin.alpha.bank.exceptions;

public class TestResourceLoadException extends BaseException {
    public TestResourceLoadException(String message) {
        super(message, ExceptionType.TEST_RESOURCE_LOAD_EXCEPTION);
    }

    public TestResourceLoadException(String message, Throwable cause) {
        super(message, cause, ExceptionType.TEST_RESOURCE_LOAD_EXCEPTION);
    }
}
