package com.rankinteractive.validator;

import com.rankinteractive.exception.InvalidRequestException;
import com.rankinteractive.request.GetTransactionsRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;
import static com.rankinteractive.exception.errors.ErrorCodes.FIELD_VALIDATION_ERR;
import static com.rankinteractive.exception.errors.ErrorCodes.INVALID_REQUEST;

@Component
public class GetTransactionValidator implements Validator {

    @Override
    public void validate(Object obj, Errors errors) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        GetTransactionsRequest serverRequest = (GetTransactionsRequest) obj;

        try {
            Set<ConstraintViolation<GetTransactionsRequest>> violations = validator.validate(serverRequest);
            for(ConstraintViolation<GetTransactionsRequest> violation  : violations) {
                errors.rejectValue(violation.getPropertyPath().toString(), FIELD_VALIDATION_ERR.getResponseCode(), violation.getMessage());
            }

        } catch (Exception e) {
            throw new InvalidRequestException(INVALID_REQUEST.getResponseCode(), errors);
        }
    }


    /**
     * This Validator validates *just* Application instances
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return GetTransactionValidator.class.isAssignableFrom(clazz);
    }
}
