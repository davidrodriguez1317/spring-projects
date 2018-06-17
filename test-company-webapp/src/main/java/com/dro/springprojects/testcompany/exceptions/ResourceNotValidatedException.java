package com.dro.springprojects.testcompany.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * The ResourceNotValidatedException when the object is not valid in a REST call
 * @author david
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Resource not validated or malformed")
public class ResourceNotValidatedException extends RuntimeException {

	private static final long serialVersionUID = 7718828512143293558L;
	    
    public ResourceNotValidatedException() {
        super();
    }
    public ResourceNotValidatedException(String message, Throwable cause) {
        super(message, cause);
    }
    public ResourceNotValidatedException(String message) {
        super(message);
    }
    public ResourceNotValidatedException(Throwable cause) {
        super(cause);
    }
}
