package com.trendyol.tr.shoppingcart.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ResponseUtils {
	
	private static final String EMPTY_RESPONSE_REGEXP = "^(\\[\\s*\\])$";
	public static final String EMPTY_RESPONSE = "[]";
	public static final String CHARSET_UTF_8 = ";charset=utf-8";
	
	/**
	 * Utility classes should not have a public constructor.
	 * Added a private constructor to hide the implicit public one.
	 */
	private ResponseUtils() {
	}
	
	public static boolean isOutputBlank(String output){
		Pattern regex = Pattern.compile(EMPTY_RESPONSE_REGEXP);
		Matcher matcher = regex.matcher(output);
		if (matcher.find()){
		    return true;
		}
		
		return false;
	}

	public static String getErrorMessage(Exception e) {
		if(e == null) {
			return null;
		}
		
		if(e.getMessage() == null && e.getCause() != null) {
			return getErrorMessage((Exception)e.getCause());
		}
		
		return e.getMessage() == null ? e.getClass().getName() : e.getMessage();
	}
	
}
