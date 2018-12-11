package com.trendyol.tr.shoppingcart.ws.provider;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.http.HttpStatus;
import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.jboss.resteasy.api.validation.ViolationReport;

import com.trendyol.tr.shoppingcart.ws.util.ExceptionMapper;

/**
 * {@link ExceptionMapper} for {@link ValidationException}.
 * <p>
 * Send a {@link ViolationReport} in {@link Response} in addition to HTTP 400 status code.
 * </p>
 *
 * @see org.jboss.resteasy.api.validation.ResteasyViolationExceptionMapper The original WildFly class:
 *      {@code org.jboss.resteasy.api.validation.ResteasyViolationExceptionMapper}
 */
@Provider
public class ValidationExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<ValidationException> {
 
    @Override
    public Response toResponse(ValidationException exception) {
    	ResteasyViolationException ex = (ResteasyViolationException) exception;
    	
    	String path = ex.getViolations().get(0).getPath();
		String message = ex.getViolations().get(0).getMessage();
		
		String errorMessage = path.substring(path.lastIndexOf(".") + 1) + " " + message;
		
        return ExceptionMapper.prepareResponse(HttpStatus.SC_BAD_REQUEST, errorMessage);
    }
	
}