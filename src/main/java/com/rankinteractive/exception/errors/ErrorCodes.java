package com.rankinteractive.exception.errors;

import org.springframework.http.HttpStatus;

public enum ErrorCodes {
    // ==============================================================================================================================
    // GENERAL ERRORS
    // Range: 1 - 99
    // ==============================================================================================================================
    GENERAL_SYSTEM_ERR(1, "General System Error", HttpStatus.INTERNAL_SERVER_ERROR),
    GENERAL_DATABASE_ERR(2, "General Database Error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_ID_PATH_VALUE(3, "Invalid value for 'id' path parameter.", HttpStatus.BAD_REQUEST),
    FIELD_VALIDATION_ERR(4, "Invalid field: ", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST(7, "Invalid Request", HttpStatus.BAD_REQUEST),
    DUPLICATE_FIELD(9, "Duplicate field", HttpStatus.BAD_REQUEST),
    AUTHENTICATION_FAILED(106,"Authentication failed.",HttpStatus.FORBIDDEN);




    // ==============================================================================================================================
    private String responseCode;
    private String responseDesc;
    private HttpStatus httpStatus;
    private static final String RESPONSE_CODE_FORMAT = "%04d";

    ErrorCodes(int responseCode, String responseDesc, HttpStatus httpStatus) {
        if (responseCode >= 0) {
            this.responseCode = String.format(RESPONSE_CODE_FORMAT, responseCode);
        } else {
            this.responseCode = String.valueOf(responseCode);
        }
        this.responseDesc = responseDesc;
        this.httpStatus = httpStatus;
    }


    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
