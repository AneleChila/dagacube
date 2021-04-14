package com.rankinteractive.controller;

import com.rankinteractive.exception.AuthenticationException;
import com.rankinteractive.exception.BadRequestException;
import com.rankinteractive.exception.InvalidRequestException;
import com.rankinteractive.exception.errors.ErrorResponse;
import com.rankinteractive.exception.errors.FieldErrorResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anelechila
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	private final Logger logger = LoggerFactory.getLogger(getClass());

  @ExceptionHandler({ InvalidRequestException.class })
  protected ResponseEntity<Object> handleInvalidRequest(InvalidRequestException ire, WebRequest request) {
  	logger.info("InvalidRequestException caught", ire);
      List<FieldErrorResource> fieldErrorResources = new ArrayList<>();

      List<FieldError> fieldErrors = ire.getErrors().getFieldErrors();
      for (FieldError fieldError : fieldErrors) {
          FieldErrorResource fieldErrorResource = new FieldErrorResource();
          fieldErrorResource.setResource(fieldError.getObjectName());
          fieldErrorResource.setField(fieldError.getField());
          fieldErrorResource.setCode(fieldError.getCode());
          fieldErrorResource.setMessage(fieldError.getDefaultMessage());
          fieldErrorResources.add(fieldErrorResource);
      }

      ErrorResponse error = new ErrorResponse("InvalidRequest", ire.getMessage());
      error.setFieldErrors(fieldErrorResources);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      return handleExceptionInternal(ire, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
  }

    @ExceptionHandler( { AuthenticationException.class } )
    protected ResponseEntity<Object> handleAuthenticationException(AuthenticationException authExc, WebRequest request) {
        logger.info("AuthenticationException caught", authExc);

        ErrorResponse error = new ErrorResponse("AuthenticationException", authExc.getMessage());
        error.addGlobalError(authExc.getMessage());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(authExc, error, headers, HttpStatus.UNAUTHORIZED, request);
    }


  @ExceptionHandler( { BadRequestException.class } )
  protected ResponseEntity<Object> handleBadRequest(BadRequestException bre, WebRequest request) {
  	logger.info("BadRequestException caught", bre);

      ErrorResponse error = new ErrorResponse("BadRequest", bre.getMessage());
      error.addGlobalError(bre.getMessage());

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

    return handleExceptionInternal(bre, error, headers, HttpStatus.BAD_REQUEST, request);
  }
}
