package com.rankinteractive.exception.errors;

import lombok.Data;

@Data
public class ErrorField {
    private String code;
    private String message;

    public ErrorField(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorField() {

    }

    public ErrorField(ErrorCodes errorCode) {
        code = errorCode.getResponseCode();
        message = errorCode.getResponseDesc();
    }
}
