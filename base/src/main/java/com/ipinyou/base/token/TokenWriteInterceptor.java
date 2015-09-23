package com.ipinyou.base.token;

import java.math.BigInteger;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ipinyou.base.util.CookieUtils;

public class TokenWriteInterceptor extends HandlerInterceptorAdapter {

	public final static String REQUEST_ATTR_TOKEN = "REQUEST_ATTR_TOKEN";

	private static final Random RANDOM = new Random();

	public static String generateGUID() {
		return new BigInteger(165, RANDOM).toString(36).toUpperCase();
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (handler instanceof HandlerMethod) {
			setToken(request, response);
		}
	}

	public static void setToken(HttpServletRequest request,
			HttpServletResponse response) {
		Cookie c = CookieUtils.getCookie(request, TokenInterceptor.TOKEN_NAME);
		String value = null;
		if (c != null) {
			value = c.getValue();
			if (StringUtils.isBlank(value)) {
				value = generateGUID();
				c.setValue(value);
			}
		} else {
			value = generateGUID();
			c = new Cookie(TokenInterceptor.TOKEN_NAME, value);
		}
		String contextPath = request.getContextPath();
		if (contextPath == null || contextPath.length() == 0) {
			contextPath = "/";
		}
		c.setPath(contextPath);
		c.setMaxAge(-1);
		response.addCookie(c);
		request.setAttribute(REQUEST_ATTR_TOKEN, value);
	}
}
