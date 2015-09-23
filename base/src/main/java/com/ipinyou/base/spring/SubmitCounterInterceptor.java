/**
 * 
 */
package com.ipinyou.base.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author lijt
 * 
 */
public class SubmitCounterInterceptor extends HandlerInterceptorAdapter {

	public final static String NAME_FIELD = "submit.counter";
	
	public final static String NO_COUNT_FIELD = "submit.noCount";
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String param = request.getParameter(NAME_FIELD);
		
		if (!StringUtils.isBlank(param)) {
			int count = Integer.parseInt(param.trim());
			request.setAttribute(NAME_FIELD, ++count);
		}
		return true;
	}
}
