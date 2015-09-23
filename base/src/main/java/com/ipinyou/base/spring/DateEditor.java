/**
 * 
 */
package com.ipinyou.base.spring;

import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;

import com.ipinyou.base.constant.DateFormat;

/**
 * @author lijt
 * 
 */
public class DateEditor extends CustomDateEditor {

	/**
	 * @param dateFormat
	 * @param allowEmpty
	 */
	public DateEditor(java.text.DateFormat dateFormat, boolean allowEmpty) {
		super(dateFormat, allowEmpty);
	}

	@Override
	public String getAsText() {
		java.util.Date date = (java.util.Date) getValue();
		if(date==null){
			return "";
		}
		if (date instanceof java.sql.Time) {
			return new SimpleDateFormat(DateFormat.TIME).format(date);
		} else if (date instanceof java.sql.Date) {
			return new SimpleDateFormat(DateFormat.DATE).format(date);
		}
		return new SimpleDateFormat(DateFormat.TIMESTAMP).format(date);
	}
}

