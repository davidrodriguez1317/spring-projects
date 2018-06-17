package com.dro.springprojects.testcompany.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * The ResourceAlreadyExistsException is thrown when a object cannot be created because it already exists
 * @author david
 *
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason="Resource already exists")
public class ResourceAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 7718828512143293558L;
	    
    public ResourceAlreadyExistsException() {
        super();
    }
    public ResourceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
    public ResourceAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
