package com.ipinyou.base.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 强制不缓存的过滤器，如果url中存在filter.no_cache=false参数则不强制不缓存
 * 
 * @author lijt
 * 
 */
public class ForceNoCacheFilter extends OncePerRequestFilter {
	// private List<String> includeContentTypes;

	public static final String NO_CACHE_ATTR = "filter.no_cache";

	private String excludePathPrefix;

	/**
 * 
 */
	public ForceNoCacheFilter() {
		super();
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// includeContentTypes = null;
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
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		// String contentType = arg1.getContentType();
		// if(contentType==null){
		// arg2.doFilter(arg0, arg1);
		// return;
		// }
		// if (includeContentTypes.contains(contentType.toLowerCase())) {
		// ((HttpServletResponse) arg1).setHeader("Cache-Control",
		// "no-cache, no-store");
		// ((HttpServletResponse) arg1).setHeader("Pragma", "no-cache");
		// ((HttpServletResponse) arg1).setDateHeader("Expires", -1);
		// }
		// arg2.doFilter(arg0, arg1);
		// try {
		// arg2.doFilter(arg0, arg1);
		// } finally {
		// String contentType = arg1.getContentType();
		// if (contentType == null) {
		// return;
		// }
		// int position = contentType.indexOf(';');
		// if (position != -1) {
		// contentType = contentType.substring(0, position);
		// }
		// if (includeContentTypes.contains(contentType.toLowerCase())) {
		// arg1.setHeader("Cache-Control",
		// "no-cache, no-store");
		// arg1.setHeader("Pragma", "no-cache");
		// arg1.setDateHeader("Expires", -1);
		// }
		// return;
		// }
		boolean flag = true;
		if(excludePathPrefix!=null){
			String uri = request.getRequestURI();
			String contenxtPath = request.getContextPath();
			if (contenxtPath.length() != 0) {
				uri = uri.substring(contenxtPath.length());
			}
			if(uri.startsWith(excludePathPrefix)){
				flag = false;
			}
		}
		if (flag && !"false".equals(request.getParameter(NO_CACHE_ATTR))) {
			response.setHeader("Cache-Control", "no-cache, no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", -1);
		}
		chain.doFilter(request, response);
	}

	/*
	 * （非 Javadoc）
	 * 
	 * @see org.springframework.web.filter.GenericFilterBean#initFilterBean()
	 */
	@Override
	protected void initFilterBean() throws ServletException {
		super.initFilterBean();
		String str = this.getFilterConfig().getInitParameter("excludePathPrefix");
		if(!StringUtils.isBlank(str)){
			excludePathPrefix = str.trim();
		}
		// String includeContentTypesStr = this.getFilterConfig()
		// .getInitParameter("includeContentTypes");
		// includeContentTypes = new ArrayList<String>();
		// if (includeContentTypesStr != null
		// && !"".equals(includeContentTypesStr)) {
		// String[] a = includeContentTypesStr.split(";");
		// for (int i = 0; i < a.length; i++) {
		// includeContentTypes.add(a[i].toLowerCase());
		// }
		// } else {
		// includeContentTypes.add("text/html");
		// includeContentTypes.add("text/xml");
		// includeContentTypes.add("text/plain");
		// }
	}
}