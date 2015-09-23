/**
 * 
 */
package com.ipinyou.base.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ipinyou.base.exception.IllegalUrlException;

/**
 * @author lijt
 *
 */
public class TrailingSlashInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		if (uri != null && uri.endsWith("form/")) { 
			throw new IllegalUrlException("错误的URL！");
		}
		return super.preHandle(request, response, handler);
	}

}
