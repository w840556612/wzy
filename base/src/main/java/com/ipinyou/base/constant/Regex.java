/**
 * 
 */
package com.ipinyou.base.constant;

/**
 * 一些正则表达式常量
 * 
 * @author lijt
 * 
 */
public interface Regex {
	String EMAIL = "^(\\S)+@(\\S)+?\\.(\\S)+$";
	String URL = "^https?://(\\S)+?\\.(\\S)+$";
	String CELLPHONE = "^\\d{11}$";
	String PHONE = "^0(\\d{2}|\\d{3})-(\\d{8}|\\d{7})$";
	String TAXPAYER = "^\\w{15,20}$";
	String JS_CONTENT=".*%%CLICK_URL%%.*";

}
