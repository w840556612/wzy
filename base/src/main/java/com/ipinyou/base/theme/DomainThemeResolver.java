/**
 * 
 */
package com.ipinyou.base.theme;

import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.util.WebUtils;

/**
 * 根据域名解析theme
 * @author lijt
 * 
 */
public class DomainThemeResolver extends CookieThemeResolver {
	private Map<String, List<String>> domainThemes;

	private String defaultThemeName = "default";

	public void setDomainThemes(Map<String, List<String>> domainThemes) {
		this.domainThemes = domainThemes;
	}

	public void setDefaultThemeName(String defaultThemeName) {
		this.defaultThemeName = defaultThemeName;
	}

	@Override
	public String resolveThemeName(HttpServletRequest request) {
		String domain = request.getServerName(); //TODO 支持https 
		// Check request for preparsed or preset theme.
		String theme = (String) request
				.getAttribute(THEME_REQUEST_ATTRIBUTE_NAME);
		if (theme == null) {
			// Retrieve cookie value from request.
			Cookie cookie = WebUtils.getCookie(request, getCookieName());
			if (cookie != null) {
				theme = cookie.getValue();
			}
		}
		List<String> themes = domainThemes.get(domain);
		if (theme != null && themes != null && themes.contains(theme)) {
			return theme;
		} else if(themes != null&&themes.size()>0){
			return themes.get(0);
		}else {
			return defaultThemeName;
		}

	}

}
