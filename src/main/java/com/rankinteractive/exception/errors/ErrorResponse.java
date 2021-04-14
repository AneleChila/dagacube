package com.rankinteractive.exception.errors;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author anelechila
 */
public class ErrorResponse {
    private String code;
    private String message;
    private List<String> globalErrors;
    private Map<String, FieldErrorResource> fieldErrors;

    public ErrorResponse() { }

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public Map<String, FieldErrorResource> getFieldErrors() { return fieldErrors; }

    public void setFieldErrors(List<FieldErrorResource> fieldErrors) {
        this.fieldErrors = new LinkedHashMap<>();
        for (FieldErrorResource fieldErrorResource : fieldErrors) {
					this.fieldErrors.put(fieldErrorResource.getField(), fieldErrorResource);
				}
    }
    
    public void addGlobalError(String message) {
    	if (globalErrors == null) {
    		globalErrors = new LinkedList<String>();
    	}
    	
    	globalErrors.add(message);
    }
}