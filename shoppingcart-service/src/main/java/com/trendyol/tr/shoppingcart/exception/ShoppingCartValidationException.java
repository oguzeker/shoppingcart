package com.trendyol.tr.shoppingcart.exception;

import javax.ejb.ApplicationException;
import javax.ws.rs.core.Response;

/**
 * The class <code>ServiceException</code> and its subclasses are a
 * form of <code>Throwable</code> that indicates conditions that a reasonable
 * application might want to catch.
 * 
 * @author Oguz Erhan Eker
 * 
 */
@ApplicationException(rollback = false)
public class ShoppingCartValidationException extends ShoppingCartException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs a new exception with the specified cause and a detail message
	 * of <tt>(cause==null ? null : cause.toString())</tt> (which typically
	 * contains the class and detail message of <tt>cause</tt>). This
	 * constructor is useful for exceptions that are little more than wrappers
	 * for other throwables (for example, {@link
	 * java.security.PrivilegedActionException}).
	 * 
	 * @param cause
	 *        the cause (which is saved for later retrieval by the
	 *        {@link #getCause()} method). (A <tt>null</tt> value is
	 *        permitted, and indicates that the cause is nonexistent or
	 *        unknown.)
	 */
	public ShoppingCartValidationException(Throwable cause) {
		super(cause);
		errorCode = ErrorCode.BAD_REQUEST;
	}
	
	/**
	 * Constructs a new exception with the specified cause and a detail message
	 * of <tt>(cause==null ? null : cause.toString())</tt> (which typically
	 * contains the class and detail message). This
	 * constructor is useful for exceptions that are little more than wrappers
	 * for other throwables (for example, {@link
	 * java.security.PrivilegedActionException}).
	 * 
	 * @param String
	 *        the detail message (which is saved for later retrieval by the
	 *        {@link #getMessage()} method).
	 */
	public ShoppingCartValidationException(String message) {
		super(message);
		errorCode = ErrorCode.BAD_REQUEST;
	}
	
	/**
	 * Constructs a new exception with the specified detail message and cause.
	 * <p>
	 * Note that the detail message associated with <code>cause</code> is
	 * <i>not</i> automatically incorporated in this exception's detail
	 * message.
	 * 
	 * @param cause
	 *        the cause (which is saved for later retrieval by the
	 *        {@link #getCause()} method). (A <tt>null</tt> value is
	 *        permitted, and indicates that the cause is nonexistent or
	 *        unknown.)
	 * @param message
	 *        the detail message (which is saved for later retrieval by the
	 *        {@link #getMessage()} method).
	 */
	public ShoppingCartValidationException(Throwable cause, String message) {
		super(message, cause);
		errorCode = ErrorCode.BAD_REQUEST;
	}

	/**
	 * Constructs a new exception with the specified cause and a detail message
	 * of <tt>(cause==null ? null : cause.toString())</tt> (which typically
	 * contains the class and detail message of <tt>cause</tt>). This
	 * constructor is useful for exceptions that are little more than wrappers
	 * for other throwables (for example, {@link
	 * java.security.PrivilegedActionException}).
	 * 
	 * @param cause
	 *        the cause (which is saved for later retrieval by the
	 *        {@link #getCause()} method). (A <tt>null</tt> value is
	 *        permitted, and indicates that the cause is nonexistent or
	 *        unknown.)
	 * @param errorCode
	 *        the error code
	 */
	public ShoppingCartValidationException(Throwable cause, ErrorCode errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}
	
	/**
	 * Constructs a new exception with the specified detail message and cause.
	 * <p>
	 * Note that the detail message associated with <code>cause</code> is
	 * <i>not</i> automatically incorporated in this exception's detail
	 * message.
	 * 
	 * @param cause
	 *        the cause (which is saved for later retrieval by the
	 *        {@link #getCause()} method). (A <tt>null</tt> value is
	 *        permitted, and indicates that the cause is nonexistent or
	 *        unknown.)
	 * @param errorCode
	 *        the error code
	 * @param message
	 *        the detail message (which is saved for later retrieval by the
	 *        {@link #getMessage()} method).
	 */
	public ShoppingCartValidationException(Throwable cause, ErrorCode errorCode, String message) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public ShoppingCartValidationException(ErrorCode errorCode, String... args) {
		this.args = args;
		this.errorCode = errorCode;
	}
	
	@Override
	public int getHttpStatusCode() {
		return Response.Status.BAD_REQUEST.getStatusCode();
	}
}
