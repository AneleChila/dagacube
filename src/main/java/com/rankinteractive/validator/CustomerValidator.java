package com.rankinteractive.validator;

import com.rankinteractive.exception.InvalidRequestException;
import com.rankinteractive.request.CreateCustomerRequest;
import com.rankinteractive.request.LoginRequest;
import com.rankinteractive.request.UpdateCustomerRequest;
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
public class CustomerValidator implements Validator {

    @Override
    public void validate(Object obj, Errors errors) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        CreateCustomerRequest serverRequest = (CreateCustomerRequest) obj;

        try {
            Set<ConstraintViolation<CreateCustomerRequest>> violations = validator.validate(serverRequest);
            for(ConstraintViolation<CreateCustomerRequest> violation  : violations) {
                errors.rejectValue(violation.getPropertyPath().toString(), FIELD_VALIDATION_ERR.getResponseCode(), violation.getMessage());
            }

        } catch (Exception e) {
            throw new InvalidRequestException(INVALID_REQUEST.getResponseCode(), errors);
        }
    }

    public void validateOnUpdate(Object obj, Errors errors) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        UpdateCustomerRequest serverRequest = (UpdateCustomerRequest) obj;

        try {
            Set<ConstraintViolation<UpdateCustomerRequest>> violations = validator.validate(serverRequest);
            for(ConstraintViolation<UpdateCustomerRequest> violation  : violations) {
                errors.rejectValue(violation.getPropertyPath().toString(), FIELD_VALIDATION_ERR.getResponseCode(), violation.getMessage());
            }

        } catch (Exception e) {
            throw new InvalidRequestException(INVALID_REQUEST.getResponseCode(), errors);
        }
    }

    public void validateLogin(Object obj, Errors errors) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        LoginRequest serverRequest = (LoginRequest) obj;

        try {
            Set<ConstraintViolation<LoginRequest>> violations = validator.validate(serverRequest);
            for(ConstraintViolation<LoginRequest> violation  : violations) {
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
        return CustomerValidator.class.isAssignableFrom(clazz);
    }
}
