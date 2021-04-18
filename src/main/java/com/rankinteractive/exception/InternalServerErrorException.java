package com.rankinteractive.exception;
/**
 * @author anelechila
 * @since 11/03/2020
 * @version 1.0
 */
public class InternalServerErrorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InternalServerErrorException(String message) {
        super(message);
    }

}
