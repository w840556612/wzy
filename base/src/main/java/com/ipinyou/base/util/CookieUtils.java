package com.ipinyou.base.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public abstract class CookieUtils {

	public static final Cookie getCookie(HttpServletRequest request, String name){
		if(name==null||request.getCookies()==null){
			return null;
		}
		for (Cookie c : request.getCookies()) {
			if(name.equalsIgnoreCase(c.getName())){
				return c;
			}
		}
		return null;
	}
	
	public static final String getCookieValue(HttpServletRequest request, String name){
		Cookie c = getCookie(request, name);
		if(c!=null){
			return c.getValue();
		}else{
			return null;
		}
	}
	
}
