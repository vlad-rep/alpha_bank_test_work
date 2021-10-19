package com.vlad.repin.alpha.bank.api;

import lombok.Data;

@Data
public class ResponseDto<T> {
    private T data;
    private Status statusType;
    private String message;

    public ResponseDto() {
        this.data = null;
        this.statusType = null;
        this.message = null;
    }

    public ResponseDto(T data, Status statusType, String message) {
        this.data = data;
        this.statusType = statusType;
        this.message = message;
    }

    public static <T> ResponseDto<T> success(T dataClass) {
        return new ResponseDto<>(
            dataClass,
            Status.OK,
            null
        );
    }

    public static <T> ResponseDto<T> success() {
        return new ResponseDto<>(
            null,
            Status.OK,
            null
        );
    }

    public static <T> ResponseDto<T> error(String message) {
        return new ResponseDto<>(
            null,
            Status.ERROR,
            message
        );
    }
}
