package com.trendyol.tr.shoppingcart.service.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.trendyol.tr.shoppingcart.common.util.StringUtil;

/**
 * A utility class for password creation
 * 
 * @author Oguz Erhan Eker
 */
public final class PasswordUtil {

	/*
	 * The Java Cryptography Extension (JCE) MD5 message digest generator.
	 */
	private static MessageDigest md5;
	private static final String ALGORITHM_MD5 = "MD5";

	private PasswordUtil() {
	}
	
	/**
	 * Returns the MD5 hash of the given <code>password</code> string.
	 * 
	 * @param password
	 *        the password to digest
	 * @return the MD5 hash of the given <code>password</code>
	 */
	public static String getHashedPassword(String password) {
		byte[] pwd = password.getBytes();
		synchronized (PasswordUtil.class) {
			if (md5 == null) {
				try {
					md5 = MessageDigest.getInstance(ALGORITHM_MD5);
				} catch (NoSuchAlgorithmException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
			}
			byte[] hash = md5.digest(pwd);
			return StringUtil.toHexString(hash);
		}
	}
	
}
