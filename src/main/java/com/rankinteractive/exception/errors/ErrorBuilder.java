package com.rankinteractive.exception.errors;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Collections;
import java.util.Comparator;

public class ErrorBuilder {

    public static Error validationErrors(Errors errors) {
        Error error = new Error();
        for (ObjectError objectError : errors.getAllErrors()) {
            FieldError fieldError = (FieldError) objectError;
            error.addError(new ErrorField(ErrorCodes.FIELD_VALIDATION_ERR.getResponseCode(),
                    ErrorCodes.FIELD_VALIDATION_ERR.getResponseDesc() + fieldError.getField() + ". Reason: "
                            + objectError.getDefaultMessage()));
        }

        Collections.sort(error.getErrors(), Comparator.comparing(ErrorField::getCode));
        return error;
    }
}
