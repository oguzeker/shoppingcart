package com.trendyol.tr.shoppingcart.ws.provider;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.http.HttpStatus;

@Provider
public class CustomResourceExceptionMapper implements ExceptionMapper<WebApplicationException> { 
	@Override
	public Response toResponse(WebApplicationException ex) { 
		if (ex.getCause() instanceof java.lang.NumberFormatException) { 
			return com.trendyol.tr.shoppingcart.ws.util.ExceptionMapper.prepareResponse(HttpStatus.SC_BAD_REQUEST, "400.000", null);
		}
		return null;
	}
}
