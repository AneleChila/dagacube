package com.rankinteractive.exception.errors;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Error {
    private List<ErrorField> errors = new ArrayList<>();

    public void addError(ErrorField error) {
        if (!alReadyAdded(error)) {
            errors.add(error);
        }
    }

    private boolean alReadyAdded(ErrorField error) {
        for (ErrorField errorField : errors) {
            if (errorField.equals(error)) {
                return true;
            }
        }

        return false;
    }
}
