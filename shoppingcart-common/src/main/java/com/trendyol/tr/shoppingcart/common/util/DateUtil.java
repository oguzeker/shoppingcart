package com.trendyol.tr.shoppingcart.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class containing set of <tt>Date</tt> related static methods.
 * 
 * @author Oguz Erhan Eker
 */
public final class DateUtil {

	private static final int HOUR_OF_DAY = 24;

	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";
	public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
	
	public static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
	public static final DateFormat DEFAULT_TIME_FORMAT = new SimpleDateFormat(DEFAULT_TIME_PATTERN);
	public static final DateFormat DEFAULT_DATETIME_FORMAT = new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);

	private static DatatypeFactory datatypeFactory;

	static {
		try {
			datatypeFactory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Utility classes should not have a public constructor.
	 * Added a private constructor to hide the implicit public one.
	 */
	private DateUtil() {
	}

	/**
	 * Formats a <code>Date</code> into a date/time string.
	 * 
	 * @param date
	 *        the time value to be formatted into a string.
	 * @param pattern
	 *        the pattern describing the date and time format
	 * @return the formatted time string, <code>null</code> if <code>date</code>
	 *         is <code>null</code>.
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * Formats a <code>Date</code> into a date/time string.
	 * 
	 * @param date
	 *        the milliseconds since January 1, 1970, 00:00:00 GMT.
	 * @param pattern
	 *        the pattern describing the date and time format
	 * @return the formatted time string.
	 */
	public static String format(long date, String pattern) {
		return new SimpleDateFormat(pattern).format(new Date(date));
	}

	/**
	 * Formats a <code>XMLGregorianCalendar</code> into a string.
	 * 
	 * @param calendar
	 *        the calendar value to be formatted into a string.
	 * @param pattern
	 *        the pattern describing the date and time format
	 * @return the formatted time string.
	 */
	public static String format(XMLGregorianCalendar calendar, String pattern) {
		return format(toDate(calendar), pattern);
	}

	/**
	 * Parses text from the beginning of the given string to produce a date.
	 * 
	 * @param str
	 *        a <tt>String</tt> whose beginning should be parsed.
	 * @param pattern
	 *        the pattern describing the date and time format
	 * @return a <tt>Date</tt> parsed from the string, <code>null</code> if
	 *         <code>str</code> is <code>null</code>.
	 */
	public static Date parse(String str, String pattern) throws ParseException {
		if (str == null) {
			return null;
		}
		return new SimpleDateFormat(pattern).parse(str);
	}

	/**
	 * Converts <tt>XMLGregorianCalendar</tt> object to <tt>java.util.Date</tt>
	 * object.
	 * 
	 * @param calendar
	 *        <tt>XMLGregorianCalendar</tt> to be converted
	 * @return a date
	 */
	public static Date toDate(XMLGregorianCalendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}

	/**
	 * Converts given <code>java.util.Date</code> object to
	 * <code>XMLGregorianCalendar</code> object with date format
	 * <code>yyyy-MM-dd</code> .
	 * 
	 * @param date
	 *        <code>java.util.Date</code> to be converted
	 * @return a <code>XMLGregorianCalendar</code> object or <code>null</code>
	 *         if the given date is <code>null</code>
	 */
	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
		return toXMLGregorianCalendar(date, DEFAULT_DATE_PATTERN);
	}

	/**
	 * Converts the given <code>java.util.Date</code> object to
	 * <code>XMLGregorianCalendar</code> object with the given date format.
	 * <p>
	 * If no pattern is specified <code>DEFAULT_DATE_PATTERN</code> will be
	 * used.
	 * 
	 * @param date
	 *        <code>java.util.Date</code> to be converted
	 * @param pattern
	 *        the pattern describing the date and time format
	 * @return a <code>XMLGregorianCalendar</code> object or <code>null</code>
	 *         if the given date is <code>null</code>
	 */
	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		
		String tempPattern = pattern;
		if (tempPattern == null) {
			tempPattern = DEFAULT_DATE_PATTERN;
		}
		DateFormat dateFormat = new SimpleDateFormat(tempPattern);
		return newXMLGregorianCalendar(dateFormat.format(date));
	}
	
	/**
	 * Converts the given <code>java.util.Date</code> object to
	 * <code>XMLGregorianCalendar</code> object with the given date format.
	 * <p>
	 * If no pattern is specified <code>DEFAULT_DATE_FORMAT</code> will be used.
	 * 
	 * @param date
	 *        <code>java.util.Date</code> to be converted
	 * @param dateFormat
	 *        a <code>java.text.DateFormat</code>
	 * @return a <code>XMLGregorianCalendar</code> object or <code>null</code>
	 *         if the given date is <code>null</code>
	 */
	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date,
			DateFormat dateFormat) {
		if (date == null) {
			return null;
		}
		
		DateFormat tempDateFormat = dateFormat; 
		if (tempDateFormat == null) {
			tempDateFormat = DEFAULT_DATE_FORMAT;
		}
		return newXMLGregorianCalendar(tempDateFormat.format(date));
	}

	/**
	 * Creates a new instance of an <code>XMLGregorianCalendar</code> with date
	 * format <code>yyyy-MM-dd'T'HH:mm:ss</code>.
	 * 
	 * @return a new <code>XMLGregorianCalendar</code> with all date/time
	 *         datatype fields set to {@link DatatypeConstants.FIELD_UNDEFINED}
	 *         or <code>null</code>.
	 */
	public static XMLGregorianCalendar newXMLGregorianCalendar() {
		return newXMLGregorianCalendar(DEFAULT_DATETIME_FORMAT.format(new Date()));
	}

	/**
	 * Create a new <code>XMLGregorianCalendar</code> by parsing the String as a
	 * lexical representation.
	 * 
	 * @param lexicalRepresentation
	 *        the lexical representation of one the eight XML Schema date/time
	 *        datatypes.
	 * @return an <code>XMLGregorianCalendar</code> created from the
	 *         <code>lexicalRepresentation</code>.
	 */
	public static XMLGregorianCalendar newXMLGregorianCalendar(
			String lexicalRepresentation) {
		if (lexicalRepresentation == null) {
			return datatypeFactory.newXMLGregorianCalendar();
		}
		return datatypeFactory.newXMLGregorianCalendar(lexicalRepresentation);
	}
	
	/**
	 * Creates a new instance of an <code>XMLGregorianCalendar</code> with given date
	 * format.
	 * 
	 * @param dateFormat
	 * @return a new <code>XMLGregorianCalendar</code> with all date/time
	 *         datatype fields set to 
	 *         {@link javax.xml.datatype.DatatypeConstants.DatatypeConstants.FIELD_UNDEFINED}
	 *         or <code>null</code>.
	 */
	public static XMLGregorianCalendar newXMLGregorianCalendar(DateFormat dateFormat) {
		return newXMLGregorianCalendar(dateFormat.format(new Date()));
	}

	/**
	 * Copies time from a specific source <code>XMLGregorianCalendar</code>
	 * object to destination <code>XMLGregorianCalendar</code> object.
	 * 
	 * @param src
	 *        the source <code>XMLGregorianCalendar</code> object
	 * @param dest
	 *        the destination <code>XMLGregorianCalendar</code> object
	 * @return the destination <code>XMLGregorianCalendar</code> or
	 *         <code>null</code> if the given source is <code>null</code>
	 */
	public static XMLGregorianCalendar timecopy(XMLGregorianCalendar src,
			XMLGregorianCalendar dest) {
		if (src == null) {
			return null;
		}
		
		XMLGregorianCalendar tempDest = dest;
		if (tempDest == null) {
			tempDest = datatypeFactory.newXMLGregorianCalendar();
		}
		tempDest.setHour(src.getHour());
		tempDest.setMinute(src.getMinute());
		tempDest.setSecond(src.getSecond());
		return tempDest;
	}

	/**
	 * Copies date from a specific source <code>XMLGregorianCalendar</code>
	 * object to destination <code>XMLGregorianCalendar</code> object.
	 * 
	 * @param src
	 *        the source <code>XMLGregorianCalendar</code> object
	 * @param dest
	 *        the destination <code>XMLGregorianCalendar</code> object
	 * @return the destination <code>XMLGregorianCalendar</code> or
	 *         <code>null</code> if the given source is <code>null</code>
	 */
	public static XMLGregorianCalendar datecopy(XMLGregorianCalendar src,
			XMLGregorianCalendar dest) {
		if (src == null) {
			return null;
		}
		
		XMLGregorianCalendar tempDest = dest;
		if (tempDest == null) {
			tempDest = datatypeFactory.newXMLGregorianCalendar();
		}
		tempDest.setDay(src.getDay());
		tempDest.setMonth(src.getMonth());
		tempDest.setYear(src.getYear());
		return tempDest;
	}

	/**
	 * Removes the time information from the given date.
	 * 
	 * @param date
	 *        the <code>Date</code>
	 * @return the <code>Date</code> with no time information or
	 *         <code>null</code> if the given date is <code>null</code>
	 */
	public static Date removeTime(Date date) {
		if (date == null) {
			return null;
		}
		// Get Calendar object set to the date and time of the given Date object
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// Set time fields to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		// Put it back in the Date object
		return cal.getTime();
	}

	/**
	 * Obtain a new instance of a Duration specifying the Duration as its string
	 * representation, "PnYnMnDTnHnMnS", as defined in XML Schema 1.0 section
	 * 3.2.6.1.
	 * 
	 * @param lexicalRepresentation
	 */
	public static Duration newDuration(String lexicalRepresentation) {
		return datatypeFactory.newDuration(lexicalRepresentation);
	}

	/**
	 * Adds given second(s) to the given date
	 * 
	 * @param date
	 *        the <code>Date</code>
	 * @param second
	 *        as integer for adding to the date
	 * @return the <code>Date</code> is later than parameter <code>null</code>
	 *         if the given date is <code>null</code>
	 */
	public static Date addSeconds(Date date, int second) {
		if (date == null) {
			return null;
		}

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, second);
		return calendar.getTime();
	}

	/**
	 * Adds given minute(s) to the given date
	 * 
	 * @param date
	 *        the <code>Date</code>
	 * @param minute
	 *        as integer for adding to the date
	 * @return the <code>Date</code> is later than parameter <code>null</code>
	 *         if the given date is <code>null</code>
	 */
	public static Date addMinutes(Date date, int minute) {
		if (date == null) {
			return null;
		}

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	 * Adds given minute(s) to the given date
	 * 
	 * @param date
	 *        the <code>Date</code>
	 * @param hours
	 *        as integer for adding to the date
	 * @return the <code>Date</code> is later than parameter <code>null</code>
	 *         if the given date is <code>null</code>
	 */
	public static Date addHours(Date date, int hours) {
		if (date == null) {
			return null;
		}

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hours);
		return calendar.getTime();
	}

	/**
	 * Adds given day(s) to the given date
	 * 
	 * @param date
	 *        the <code>Date</code>
	 * @param days
	 *        as integer for adding to the date
	 * @return the <code>Date</code> is later than parameter <code>null</code>
	 *         if the given date is <code>null</code>
	 */
	public static Date addDays(Date date, int days) {
		if (date == null) {
			return null;
		}

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	/**
	 * Adds given month(s) to the given date
	 * 
	 * @param date
	 *        the <code>Date</code>
	 * @param months
	 *        as integer for adding to the date
	 * @return the <code>Date</code> is later than parameter <code>null</code>
	 *         if the given date is <code>null</code>
	 */
	public static Date addMonths(Date date, int months) {
		if (date == null) {
			return null;
		}

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}

	/**
	 * Adds given year(s) to the given date
	 * 
	 * @param date
	 *        the <code>Date</code>
	 * @param years
	 *        as integer for adding to the date
	 * @return the <code>Date</code> is later than parameter <code>null</code>
	 *         if the given date is <code>null</code>
	 */
	public static Date addYears(Date date, int years) {
		if (date == null) {
			return null;
		}

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);
		return cal.getTime();
	}

	/**
	 * Sets the time field of given date to 00:00:00:00. Date field stays the
	 * same.
	 * 
	 * @param date
	 *        the <code>Date</code>
	 * @return beginning of the <code>Date</code> <code>null</code> if the given
	 *         date is <code>null</code>
	 */
	public static Date beginningOfDay(Date date) {
		if (date == null) {
			return null;
		}

		GregorianCalendar beginingOfDay = new GregorianCalendar();
		beginingOfDay.setTime(date);
		beginingOfDay.set(Calendar.HOUR_OF_DAY, 0);
		beginingOfDay.set(Calendar.MINUTE, 0);
		beginingOfDay.set(Calendar.SECOND, 0);
		beginingOfDay.set(Calendar.MILLISECOND, 0);
		return beginingOfDay.getTime();
	}

	/**
	 * Sets the time field of given date to 24:00:00:00. Date field rolls 1 day
	 * up.
	 * 
	 * @param date
	 *        the <code>Date</code>
	 * @return end of the <code>Date</code> <code>null</code> if the given date
	 *         is <code>null</code>
	 */
	public static Date endOfDay(Date date) {
		if (date == null) {
			return null;
		}

		GregorianCalendar endOfDay = new GregorianCalendar();
		endOfDay.setTime(date);
		// sets hour as 24
		endOfDay.set(Calendar.HOUR_OF_DAY, HOUR_OF_DAY);
		endOfDay.set(Calendar.SECOND, 0);
		endOfDay.set(Calendar.MINUTE, 0);
		endOfDay.set(Calendar.MILLISECOND, 0);
		return endOfDay.getTime();
	}
}
