package com.trendyol.tr.shoppingcart.ws.util;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trendyol.tr.shoppingcart.common.util.ResponseUtils;
import com.trendyol.tr.shoppingcart.exception.ErrorCode;
import com.trendyol.tr.shoppingcart.exception.ShoppingCartException;
import com.trendyol.tr.shoppingcart.service.msg.MessageResource;

public final class ExceptionMapper {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionMapper.class);
	
	/**
	 * Utility classes should not have a public constructor.
	 * Added a private constructor to hide the implicit public one.
	 */
	private ExceptionMapper() {
	}
	
	public static Response handleException(Exception e) {
		if (e instanceof ShoppingCartException) {
			
			ShoppingCartException ex = (ShoppingCartException) e;
			
			if (logger.isDebugEnabled()) { 
				String message = MessageResource.getMessage(ex.getErrorCode().getCode(), (Object[]) ex.getArgs());
				logger.debug(ex.getMessage() != null ? ex.getMessage() : message, e);
			}
			
			return prepareResponse(ex.getHttpStatusCode(), ex.getErrorCode().getCode(), ex.getArgs());
		} 
			
		logger.error(e.getMessage(), e);
		
		return prepareResponse(HttpStatus.SC_INTERNAL_SERVER_ERROR, 
				ErrorCode.INTERNAL_SERVER_ERROR.getCode(), 
				new String[]{ ResponseUtils.getErrorMessage(e) });
	}
	
	public static Response prepareResponse(int responseStatus, String errorCode, String[] args) {
		String messageString = MessageResource.getMessage(errorCode, (Object[]) args);
		ErrorMessage errorMessage = new ErrorMessage(responseStatus, errorCode, messageString);
		return Response.status(errorMessage.getStatus())
				.entity(errorMessage)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
	
	// for bean validation errors
	public static Response prepareResponse(int responseStatus, String message) {
		ErrorMessage errorMessage = new ErrorMessage(responseStatus, String.valueOf(responseStatus), message);
		
		return Response.status(errorMessage.getStatus())
				.entity(errorMessage)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
