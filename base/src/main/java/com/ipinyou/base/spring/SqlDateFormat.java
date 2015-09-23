/**
 * 
 */
package com.ipinyou.base.spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ipinyou.base.constant.DateFormat;

/**
 * @author lijt
 * 
 */
public class SqlDateFormat extends SimpleDateFormat {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9168373758328578282L;

	public SqlDateFormat() {
		super();
	}

	public Date parse(String text) throws ParseException {
		java.text.DateFormat df = null;
		int length = text.length();
		if (length <= DateFormat.TIME_SHORT.length()) {
			df = new SimpleDateFormat(DateFormat.TIME_SHORT);
			return new java.sql.Time(df.parse(text).getTime());
		} else if (length <= DateFormat.TIME.length()) {
			df = new SimpleDateFormat(DateFormat.TIME);
			return new java.sql.Time(df.parse(text).getTime());
		} else if (length <= DateFormat.DATE.length()) {
			df = new SimpleDateFormat(DateFormat.DATE);
			return new java.sql.Date(df.parse(text).getTime());
		} else if (length <= DateFormat.TIMESTAMP_SHORT.length()) {
			df = new SimpleDateFormat(DateFormat.TIMESTAMP_SHORT);
			return new java.sql.Timestamp(df.parse(text).getTime());
		} else {
			df = new SimpleDateFormat(DateFormat.TIMESTAMP);
			return new java.sql.Timestamp(df.parse(text).getTime());
		}
	}
}
