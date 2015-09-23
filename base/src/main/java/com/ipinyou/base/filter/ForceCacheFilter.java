package com.ipinyou.base.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/** 强制缓存的过滤器，如果url中存在filter.cache=false参数则不强制缓存
 * 
 * 设置缓存时间单位为秒
 * 默认缓存时间请设置 validity
 * 各种扩展类型的缓存时间设置方式为： validity/js  86400 (一天)
 * @author lijt
 *
 */
public class ForceCacheFilter extends OncePerRequestFilter {

	public static final String CACHE_ATTR = "filter.cache";

	private Map<String, Long> validityMap = new HashMap<String, Long>();

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see
	 * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest arg0,
			HttpServletResponse arg1, FilterChain arg2)
			throws ServletException, IOException {
		if (!"false".equals(arg0.getParameter(CACHE_ATTR))) {
			String uri = arg0.getServletPath()
					+ (arg0.getPathInfo() == null ? "" : arg0.getPathInfo());
			Long validity = this.validityMap.get("");
			int lastDotPosition = uri.lastIndexOf(".");
			if (lastDotPosition != -1) {
				String suffix = uri.substring(lastDotPosition + 1)
						.toLowerCase();
				if (this.validityMap.containsKey(suffix)) {
					validity = this.validityMap.get(suffix);
				}
			}
			arg1.setHeader("Cache-Control", "public");
			arg1.setHeader("Pragma", "");
			// 1 hour 3600000L
			// 1 day 86400000L
			// 30 day 2592000000L
			// 365 day 31536000000L
			arg1.setDateHeader("Expires",
					new Date().getTime() + validity);
		}
		arg2.doFilter(arg0, arg1);
	}

	private final static long day_milliseconds = 86400000;
	/*
	 * （非 Javadoc）
	 * 
	 * @see org.springframework.web.filter.GenericFilterBean#initFilterBean()
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void initFilterBean() throws ServletException {
		super.initFilterBean();
		this.validityMap.put("", day_milliseconds*90l);
		// this.validityMap.put("jsp", 2l);
		this.validityMap.put("css", day_milliseconds*90l);
		this.validityMap.put("js", day_milliseconds*90l);
		this.validityMap.put("png", day_milliseconds*90l);
		this.validityMap.put("jpg", day_milliseconds*90l);
		this.validityMap.put("jpeg", day_milliseconds*90l);
		this.validityMap.put("gif", day_milliseconds*90l);
		this.validityMap.put("mp3", day_milliseconds*90l);
		this.validityMap.put("swf", day_milliseconds*90l);
		this.validityMap.put("gz", day_milliseconds*90l);
		this.validityMap.put("bz2", day_milliseconds*90l);
		this.validityMap.put("zip", day_milliseconds*90l);
		this.validityMap.put("rar", day_milliseconds*90l);
		FilterConfig config = this.getFilterConfig();
		String key = null;
		Enumeration<String> e = config.getInitParameterNames();
		if (e.hasMoreElements()) {
			for (; e.hasMoreElements();) {
				key = e.nextElement();
				if (key.equals("validity")) {
					this.validityMap.put("",
							1000*new Long(config.getInitParameter(key)));
				} else if (key.startsWith("validity/")) {
					this.validityMap.put(key.substring("validity/".length())
							.toLowerCase(),
							1000*new Long(config.getInitParameter(key)));
				}
			}
		}
	}

}