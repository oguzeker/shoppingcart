package com.trendyol.tr.shoppingcart.service.msg;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A utility class to handle resource bundles easily
 * 
 * @author Oguz Erhan Eker
 */
public final class MessageResource {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageResource.class);

	private static final String BASE_NAME = "com.trendyol.tr.shoppingcart.resources.Messages";

	private static Map<Locale, ResourceBundle> bundles = new HashMap<Locale, ResourceBundle>();

	/**
	 * Utility classes should not have a public constructor.
	 * Added a private constructor to hide the implicit public one.
	 */
	private MessageResource() {
	}

	/**
	 * Returns the message with the given <code>key</code>
	 * 
	 * @param key
	 *        the key of the desired message
	 * @return the message string
	 */
	public static String getMessage(String key) {
		return getMessage(key, Locale.getDefault(), (Object[]) null);
	}

	/**
	 * Returns the message with the given <code>key</code>
	 * 
	 * @param key
	 *        the key of the desired message
	 * @param args
	 *        the object to format
	 * @return the message string
	 */
	public static String getMessage(String key, Object... args) {
		return getMessage(key, Locale.getDefault(), args);
	}

	/**
	 * Returns the message with the given <code>key</code> for the given
	 * <code>locale</code>
	 * 
	 * @param key
	 *        the key of the desired message
	 * @param locale
	 *        the locale for which a message is desired
	 * @param args
	 *        the object to format
	 * @return the message string for the given <code>locale</code>
	 */
	public static String getMessage(String key, Locale locale, Object... args) {
		try {
			String msg = getBundle(locale).getString(key);
			if (args != null) {
				MessageFormat messageFormat = new MessageFormat(msg, locale);
				msg = messageFormat.format(args);
			}
			return msg;
		} catch (RuntimeException e) {
			logger.warn("Could not found message for key: " + key, e);
			return key;
		}
	}

	private static ResourceBundle getBundle(Locale locale) {
		if (locale == null) {
			return getBundle(Locale.getDefault());
		}
		ResourceBundle bundle = (ResourceBundle) bundles.get(locale);
		if (bundle == null) {
			bundle = ResourceBundle.getBundle(BASE_NAME, locale, Thread.currentThread().getContextClassLoader());
			bundles.put(locale, bundle);
		}
		return bundle;
	}

}
