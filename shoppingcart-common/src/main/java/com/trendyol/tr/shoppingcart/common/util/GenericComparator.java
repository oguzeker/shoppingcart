package com.trendyol.tr.shoppingcart.common.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sorts a List of data transfer objects (DTO's) based on the name of the field
 * to sort on.<br>
 * <br>
 * <code>
 * Collections.sort(myList, new DtoComparator(&quot;id&quot;, true));
 * Collections.sort(myList, new DtoComparator(&quot;myBean.id&quot;, true));
 * </code>
 * <p>
 * Very useful for lists of DTO's used in JSF datatables.
 * </p>
 * 
 * @author Oguz Erhan Eker
 */
@SuppressWarnings("unchecked")
public class GenericComparator implements Comparator<Object> {

	private static final Logger logger = LoggerFactory.getLogger(GenericComparator.class);
	
	private List<String> properties;
	private boolean ascending;

	/**
	 * Constructs a new <tt>DtoComparator</tt> object.
	 * 
	 * @param property
	 *        the property (field) name
	 * @param ascending
	 *        the sort order.
	 */
	public GenericComparator(String property, boolean ascending) {
		this.properties = new ArrayList<String>();
		for (String name : property.split("\\.")) {
			this.properties.add(name);
		}
		this.ascending = ascending;
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Object object1, Object object2) {
		Object o1 = object1;
		Object o2 = object2;
		try {
			Iterator<String> iter = properties.iterator();
			while (o1 != null && o2 != null && iter.hasNext()) {
				String fieldName = iter.next();
				o1 = PropertyUtils.getProperty(o1, fieldName);
				o2 = PropertyUtils.getProperty(o2, fieldName);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		if (o1 == null) {
			return ascending ? -1 : 1; // If ascending, current null first.
		} else if (o2 == null) {
			return ascending ? 1 : -1; // If ascending, another null first.
		}

		return ascending ? ((Comparable<Object>) o1).compareTo(o2) 
				: ((Comparable<Object>) o2).compareTo(o1);
	}

}
