package com.ipinyou.base.skin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 更换皮肤的拦截器
 * @author lijt
 *
 */
public class SkinChangeInterceptor extends HandlerInterceptorAdapter {

	private String paramName = "skin.name";

	private SkinResolver skinResolver;

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public void setSkinResolver(SkinResolver skinResolver) {
		this.skinResolver = skinResolver;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String skin = request.getParameter(this.paramName);
		if (!StringUtils.isBlank(skin)) {
			if (skinResolver == null) {
				throw new IllegalStateException("No skinResolver found");
			}
			skinResolver.setSkin(request, response, skin);
		}else{
			skinResolver.resolveSkin(request);
		}
		// Proceed in any case.
		return true;
	}

}
