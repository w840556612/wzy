package com.ipinyou.base.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 获取ip的工具类，默认先从cookie中获取ip对应的value，如果没有值然后再从header中获取，最后还没有值再调用request.getRemoteAddr()获取
 * @author lijt
 *
 */
public class IpUtils {
	private static String getIpFromCookie(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(cookies == null){
			return null;
		}
		for(Cookie c:cookies){
			if("ip".equals(c.getName())){
				return c.getValue().trim();
			}
		}
		return null;
	}
	
	public static String getIpAddr(HttpServletRequest request) {
		String ip = getIpFromCookie(request);
		if (!StringUtils.isBlank(ip)) {
			return ip;
		}else{
			ip = request.getHeader("X-Real-IP");
		}

		if (StringUtils.isBlank(ip) || ip.toLowerCase().contains("unknown")) {
			ip = request.getHeader("x-forwarded-for");
		}else if(ip.contains(",")){
			String[] ips =StringUtils.split(ip,',');
			if (ips.length > 0) {
				return ips[ips.length - 1];
			}	
		}else{
			return ip;
		}

		// "unknown".equalsIgnoreCase(ip)
		if (StringUtils.isBlank(ip) || ip.toLowerCase().contains("unknown")) {
			ip = request.getHeader("Proxy-Client-IP");
		}else{
			return ip;
		}

		if (StringUtils.isBlank(ip) || ip.toLowerCase().contains("unknown")) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}else{
			return ip;
		}

		if (StringUtils.isBlank(ip) || ip.toLowerCase().contains("unknown")) {
			return request.getRemoteAddr();
		}else{
			return ip;
		}
	}

	
}
