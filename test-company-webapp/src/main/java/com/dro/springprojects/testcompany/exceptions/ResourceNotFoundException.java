package com.dro.springprojects.testcompany.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * The ResourceNotFoundException is thrown when no resources are found in a REST call
 * @author david
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="No resource found in database")
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7718828512143293558L;
	    
    public ResourceNotFoundException() {
        super();
    }
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }
}
