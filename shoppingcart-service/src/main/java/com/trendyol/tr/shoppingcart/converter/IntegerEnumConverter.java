package com.trendyol.tr.shoppingcart.converter;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.MethodUtils;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

public class IntegerEnumConverter implements CustomConverter {

	/* (non-Javadoc)
	 * @see net.sf.dozer.util.mapping.converters.CustomConverter#convert(java.lang.Object, java.lang.Object, java.lang.Class, java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Object destination, Object source, Class destClass, Class sourceClass) {
		if (source == null) {
			return null;
		}

		if (source instanceof Enum) {
			Integer retVal = null;
	    	try {
	    		retVal = Integer.valueOf(
	    					(String) MethodUtils.invokeMethod(
	    	    				MethodUtils.invokeStaticMethod(sourceClass, "valueOf", String.valueOf(((Enum) source).name())), 
	    	    				"value", null)
	    					);
	    	} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
	            throw new MappingException("Unable to get values() from enum of type: " + destClass.getName());
	        }
	    	
	    	return retVal;
		} else if (source instanceof Integer) {
			Enum retVal = null;
	    	try {
	    		retVal = (Enum) MethodUtils.invokeStaticMethod(destClass, "fromValue", String.valueOf(source));
	    	} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
	            throw new MappingException("Unable to get values() from enum of type: " + destClass.getName());
	        }
	    	
	    	return retVal;
		} else {
			throw new MappingException("Invalid source [" + source + "]");
		}
	}

}
