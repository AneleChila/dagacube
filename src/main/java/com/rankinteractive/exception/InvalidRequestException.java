package com.rankinteractive.exception;

import org.springframework.validation.Errors;

/**
 * @author anelechila
 */
public class InvalidRequestException extends RuntimeException {

    private static final long	serialVersionUID	= 1L;
    private Errors errors;

    public InvalidRequestException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
