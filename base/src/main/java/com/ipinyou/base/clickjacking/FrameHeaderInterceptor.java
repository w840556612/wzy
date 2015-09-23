package com.ipinyou.base.clickjacking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class FrameHeaderInterceptor extends HandlerInterceptorAdapter {

	public final static FrameOption DEFAULT_OPTION = FrameOption.Sameorigin;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			response.addHeader("X-Frame-Options", "SAMEORIGIN");
		} else {
			HandlerMethod h = (HandlerMethod) handler;
			FrameHeader fh = h.getMethodAnnotation(FrameHeader.class);
			if (fh != null && fh.value() != FrameOption.Sameorigin) {
				if (fh.value() == FrameOption.Deny) {
					response.addHeader("X-Frame-Options", "DENY");
				} else if (fh.value() == FrameOption.AllowFrom) {
					if (!StringUtils.isBlank(fh.uri())) {
						response.addHeader("X-Frame-Options", "ALLOW-FROM "
								+ fh.uri());
					} else {
						throw new IllegalArgumentException("AllowFrom必须设置uri。");
					}
				}
			} else {
				response.addHeader("X-Frame-Options", "SAMEORIGIN");
			}
		}
		return true;
	}

}
