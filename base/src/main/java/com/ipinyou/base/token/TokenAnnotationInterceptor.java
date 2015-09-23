package com.ipinyou.base.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipinyou.base.exception.TokenException;

public class TokenAnnotationInterceptor extends TokenInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (TokenInterceptor.notCheck) {
			return true;
		}
		if(!(handler instanceof HandlerMethod)){
			return true;
		}
		HandlerMethod h = (HandlerMethod) handler;
		TokenValidator token = h.getMethodAnnotation(TokenValidator.class);
		if (token == null) {
			return true;
		} else if (token.value() == TokenType.Page) {
			if(!checkToken(request, response)){
				throw new TokenException("token不一致，可能是重复提交。");
			}
		} else if (token.value() == TokenType.Json) {
			if(!checkToken(request, response)){
				throw new TokenException("token不一致，可能是重复提交。"); //TODO 返回json对象
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(handler instanceof HandlerMethod){
			TokenWriteInterceptor.setToken(request, response);
		}
	}

	

}
