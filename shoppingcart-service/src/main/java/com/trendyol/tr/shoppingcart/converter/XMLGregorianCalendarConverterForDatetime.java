package com.trendyol.tr.shoppingcart.converter;

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

import com.trendyol.tr.shoppingcart.common.util.DateUtil;

/**
 * @author Oguz Erhan Eker
 *
 */
public class XMLGregorianCalendarConverterForDatetime implements CustomConverter {

	/* (non-Javadoc)
	 * @see net.sf.dozer.util.mapping.converters.CustomConverter#convert(java.lang.Object, java.lang.Object, java.lang.Class, java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Object destination, Object source, Class destClass, Class sourceClass) {
		if (source == null) {
			return null;
		}

		if (source instanceof XMLGregorianCalendar) {
			return DateUtil.toDate((XMLGregorianCalendar) source);
		} else if (source instanceof Date) {
			return DateUtil.toXMLGregorianCalendar((Date) source, DateUtil.DEFAULT_DATETIME_FORMAT);
		} else {
			throw new MappingException("Invalid source [" + source + "]");
		}
	}

}
