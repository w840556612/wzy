package com.ipinyou.base.skin;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

/**
 * 从cookie中读取皮肤设置
 * @author lijt
 *
 */
public class CookieSkinResolver extends CookieGenerator implements
		SkinResolver, ServletContextAware, InitializingBean {

	private ServletContext servletContext;

	private Set<String> all;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * The name of the request attribute that holds the skin.
	 * <p>
	 * Only used for overriding a cookie value if the skin has been changed in
	 * the course of the current request!
	 */
//	public static final String SKIN_REQUEST_ATTRIBUTE_NAME = CookieSkinResolver.class
//			.getName() + ".SKIN";
	public static final String SKIN_REQUEST_ATTRIBUTE_NAME = "skin.name";
	/**
	 * The default cookie name used if none is explicitly set.
	 */
	public static final String DEFAULT_COOKIE_NAME = "skin";

	private String defaultSkin = "default";

	private String webPath = "/s/skins";

	public CookieSkinResolver() {
		super();
		setCookieName(DEFAULT_COOKIE_NAME);
		setCookieMaxAge(Integer.MAX_VALUE);
	}

	/** 指定放置皮肤的路径,不要加后斜线，例如: /s/skins
	 * @param webPath
	 */
	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}

	protected String getDefaultSkin() {
		return defaultSkin;
	}

	/** 设置默认皮肤
	 * @param defaultSkin
	 */
	public void setDefaultSkin(String defaultSkin) {
		this.defaultSkin = defaultSkin;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		all = new HashSet<String>();
		File f = new File(servletContext.getRealPath(webPath));
		for (File subFile : f.listFiles()) {
			if (subFile.isDirectory()) {
				all.add(subFile.getName());
			}
		}
		if (!all.contains(defaultSkin)) {
			if (!all.contains("default")) {
				String msg = "defaultSkin设置错误，指定的皮肤目录不存在: " + webPath + "/"
						+ defaultSkin;
				logger.error(msg);
				throw new RuntimeException(msg);
			} else {
				logger.warn(defaultSkin + "皮肤不存在，将使用default皮肤");
				defaultSkin = "default";
			}
		}
	}

	@Override
	public String resolveSkin(HttpServletRequest request) {
		// Check request for pre-parsed or preset Skin.
		String skin = (String) request
				.getAttribute(SKIN_REQUEST_ATTRIBUTE_NAME);
		if (!StringUtils.isBlank(skin)&& all.contains(skin)) {
			return skin;
		}

		// Retrieve and parse cookie value.
		Cookie cookie = WebUtils.getCookie(request, getCookieName());
		if (cookie != null) {
			skin = cookie.getValue();
			if (logger.isDebugEnabled()) {
				logger.debug("Parsed cookie value [" + cookie.getValue()
						+ "] into skin '" + skin + "'");
			}
			if (!StringUtils.isBlank(skin) && all.contains(skin)) {
				request.setAttribute(SKIN_REQUEST_ATTRIBUTE_NAME, skin);
				return skin;
			}
		}
		request.setAttribute(SKIN_REQUEST_ATTRIBUTE_NAME, defaultSkin);
		return defaultSkin;
	}

	@Override
	public void setSkin(HttpServletRequest request,
			HttpServletResponse response, String skin) {
		if (!StringUtils.isBlank(skin)) {
			if(!all.contains(skin)){
				if(checkPatch(skin)){
					try {
						afterPropertiesSet();
					} catch (Exception e) {
						logger.warn(ExceptionUtils.getStackTrace(e));
					}
				}else{
					logger.warn("指定的skin不存在: "+skin);
					return;
				}
			}
			// Set request attribute and add cookie.
			request.setAttribute(SKIN_REQUEST_ATTRIBUTE_NAME, skin);
			addCookie(response, skin);
		} else {
			// Set request attribute to fallback skin and remove cookie.
			request.setAttribute(SKIN_REQUEST_ATTRIBUTE_NAME,defaultSkin);
			removeCookie(response);
		}
	}

	private boolean checkPatch(String skin){
		File f = new File(servletContext.getRealPath(webPath+"/"+skin));
		return f.exists()&&f.isDirectory();
	}

}
