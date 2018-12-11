package com.trendyol.tr.shoppingcart.exception;

import javax.ejb.ApplicationException;

import org.apache.http.HttpStatus;

/**
 * 
 * @author Oguz Erhan Eker
 * 
 */
@ApplicationException(rollback = true)
public class EntityNotFoundException extends ShoppingCartException {

	private static final long serialVersionUID = 1L;
	
	public EntityNotFoundException(ErrorCode errorCode, String... args) {
		super(errorCode, args);		
	}
	
	@Override
	public int getHttpStatusCode() {
		return HttpStatus.SC_NOT_FOUND;
	}

}
