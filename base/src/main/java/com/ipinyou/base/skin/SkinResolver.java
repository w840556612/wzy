package com.ipinyou.base.skin;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * skin(皮肤)解析器
 * @author lijt
 *
 */
public interface SkinResolver {
	  /**
	   * Resolve the current skin via the given request.
	   * Should return a default skin as fallback in any case.
	   * @param request the request to resolve the skin for
	   * @return the current skin name (never <code>null</code>)
	   */
	String resolveSkin(HttpServletRequest request);
	
	/**
   * Set the current skin to the given one.
   * @param request the request to be used for skin modification
   * @param response the response to be used for skin modification
   * @param skin the new skin, or <code>null</code> to clear the skin
	 */
	void setSkin(HttpServletRequest request, HttpServletResponse response, String skin);
}
