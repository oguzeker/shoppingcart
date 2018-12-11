package com.trendyol.tr.shoppingcart.converter;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

public class ByteBooleanConverter implements CustomConverter {

	/* (non-Javadoc)
	 * @see net.sf.dozer.util.mapping.converters.CustomConverter#convert(java.lang.Object, java.lang.Object, java.lang.Class, java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Object destination, Object source, Class destClass, Class sourceClass) {
		if (source == null) {
			return null;
		}

		if (source instanceof Byte) {
			return (Byte) source != 0;
		} else if (source instanceof Boolean) {
			return Byte.valueOf((byte)((Boolean) source ? 1 : 0));
		} else {
			throw new MappingException("Invalid source [" + source + "]");
		}
	}

}
