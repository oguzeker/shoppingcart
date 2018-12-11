package com.trendyol.tr.shoppingcart.common.util;

import org.apache.commons.lang.StringUtils;

public final class StringUtil {
	public static final String UNDERSCORE = "_";
	public static final String DOT = ".";
	public static final String EMPTY_STRING = "";
	
	public static String checkAndConcatenateValues(String separator, Object... args) {
		StringBuilder builder = new StringBuilder();
		int count = 0;
		for (Object object : args) {
			if (object != null) {
				count++;
				builder.append(count > 1 ? separator : EMPTY_STRING);
				builder.append(String.valueOf(object));
			}
		}
		return builder.toString();
	}
	
	private static String checkEmptyStringAndReturn(String value, String defaultValue) {
		return StringUtils.isNotEmpty(value) ? 
				value : checkNullAndReturn(defaultValue);
	}
	
	public static String checkEmptyAndReturn(Object value, Object defaultValue) {
		if (value instanceof String) {
			return checkEmptyStringAndReturn((String)value, (String)defaultValue);
		} else if (value != null) {
			return String.valueOf(value);
		} else {
			return EMPTY_STRING;
		}
	}
	
	public static String checkNullAndReturn(Object value) {
		return value != null ? String.valueOf(value) : EMPTY_STRING;
	}
	
	/**
	 * Converts a <tt>byte</tt> array to a hexadecimal string.
	 * 
	 * @param b
	 *        the <tt>byte</tt> array to be converted.
	 * @return a hexadecimal string.
	 */
	public static String toHexString(byte[] b) {
		if (b == null) {
			return null;
		}
		StringBuilder hexStr = new StringBuilder();
		for (int i = 0; i < b.length; ++i) {
			hexStr.append(Integer.toHexString((b[i] >> 4) & 0x0f));
			hexStr.append(Integer.toHexString(b[i] & 0x0f));
		}
		return hexStr.toString();
	}
}
