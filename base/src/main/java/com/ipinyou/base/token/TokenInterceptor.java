/**
 * 
 */
package com.ipinyou.base.token;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ipinyou.base.exception.TokenException;
import com.ipinyou.base.util.CookieUtils;
import com.mcas.tools.Py64;

/**
 * token检查器，防止重复提交 表单中需要加入 <@token />
 * 
 * @author lijt
 * 
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	/**
	 * The name of the field which will hold the token name
	 */
	public static final String TOKEN_NAME = "token.value";

	public static final String HEADER_NAME = "X-Token";

	/**
	 * The name of token direction parameter "name"
	 */
	// public static final String TOKEN_PARA_NAME = "name";

	static boolean notCheck = false;

	/**
	 * 设置为不检查token
	 * 
	 * @param b
	 */
	public static void setNotCheck(boolean b) {
		notCheck = b;
	}

	/**
	 * 设置为不检查token
	 * 
	 * @param b
	 */
	public static void setNotCheck() {
		notCheck = Boolean.TRUE;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (notCheck) {
			return true;
		}
		if ("PUT".equalsIgnoreCase(request.getMethod())
				|| "POST".equalsIgnoreCase(request.getMethod())
		/* 暂时不处理删除请求|| "DELETE".equalsIgnoreCase(request.getMethod()) */) {
			String uri = request.getRequestURI();
			String contextPath = request.getContextPath();
			if (contextPath.length() != 0) {
				uri = uri.substring(contextPath.length());
			}
			if (includeUri.size() == 0 && excludeUri.size() == 0
					&& includePattern.size() == 0 && excludePattern.size() == 0) {
				return true;
			}
			if (excludeUri.size() != 0 || excludePattern.size() != 0) {
				if (excludeUri.contains(uri)
						|| matchPattern(uri, excludePattern)) {
					return true;
				}
			}
			if (includeUri.size() != 0 || includePattern.size() != 0) {
				if (!includeUri.contains(uri)
						&& !matchPattern(uri, includePattern)) {
					return true;
				}
			}
			if (!checkToken(request, response)) {
				throw new TokenException("token不一致，可能是重复提交。");
			}
		}
		return true;
	}

	protected boolean checkToken(HttpServletRequest request,
			HttpServletResponse response) {
		String token = request.getHeader(HEADER_NAME); // see:
														// https://docs.djangoproject.com/en/1.6/ref/contrib/csrf/
		if (!StringUtils.isBlank(token)) {
			return checkToken(request, response, token, true);
		}

		// String tokenName = getTokenValueName(request);
		token = request.getParameter(TOKEN_NAME);
		return token != null && checkToken(request, response, token, false);// 清除对应cookie
	}

	private boolean matchPattern(String uri, Set<Pattern> patterns) {
		if (patterns == null) {
			return false;
		}
		for (Pattern p : patterns) {
			if (p.matcher(uri).matches()) {
				return true;
			}
		}
		return false;
	}

	private boolean checkToken(HttpServletRequest request,
			HttpServletResponse response, String token, boolean ajax) {
		Cookie c = CookieUtils.getCookie(request, TOKEN_NAME);
	
		if (c != null) {
			String value = c.getValue();
			c.setValue(null);
			c.setMaxAge(0);
			String contextPath = request.getContextPath();
			if (contextPath == null || contextPath.length() == 0) {
				contextPath = "/";
			}
			c.setPath(contextPath);
			response.addCookie(c);
			if (value == null) {
				return false;
			}
			if (ajax) {
				return token.equals(value);
			} else {
				return TokenInterceptor.encoding(value).equals(token);
			}
		} else {
			return false;
		}
	}

	private final static String sep = " ";

	private String[] parseToArray(String str) {
		if (str == null) {
			return new String[0];
		}
		str = StringUtils.replace(str, "\t", sep);
		str = StringUtils.replace(str, "\n", sep);
		str = StringUtils.replace(str, "\r", sep);
		return StringUtils.split(str, sep);
	}

	private Set<String> includeUri = new HashSet<String>();

	private Set<String> excludeUri = new HashSet<String>();

	/**
	 * 设置排除的uri，uri不包括contextPath，使用分号分隔，可以放在多行中
	 * 
	 * @param uri
	 */
	public void setExcludeUri(String uri) {
		if (uri != null) {
			for (String str : parseToArray(uri)) {
				str = str.trim();
				if (str.length() > 0) {
					excludeUri.add(str);
				}
			}

		}
	}

	/**
	 * 设置需要检查的uri白名单，uri不包括contextPath，使用分号分隔，可以放在多行中
	 * 
	 * @param uri
	 */
	public void setIncludeUri(String uri) {
		if (uri != null) {
			for (String str : parseToArray(uri)) {
				str = str.trim();
				if (str.length() > 0) {
					includeUri.add(str);
				}
			}
		}

	}

	private Set<Pattern> includePattern = new HashSet<Pattern>();

	private Set<Pattern> excludePattern = new HashSet<Pattern>();

	public void setExcludePattern(String str) {
		if (str != null) {
			String[] arr = parseToArray(str);
			for (String s : arr) {
				s = s.trim();
				if (s.length() > 0) {
					excludePattern.add(Pattern.compile(s));
				}
			}

		}
	}

	public void setIncludePattern(String str) {
		if (str != null) {
			for (String s : parseToArray(str)) {
				s = s.trim();
				if (s.length() > 0) {
					includePattern.add(Pattern.compile(s));
				}
			}

		}
	}

	// private String getTokenValueName(HttpServletRequest request) {
	// @SuppressWarnings("unchecked")
	// Map<String, Object> parameterMap = request.getParameterMap();
	// for (String key : parameterMap.keySet()) {
	// if (key.startsWith(TOKEN_NAME_PREFIX + ".")) {
	// return key;
	// }
	// }
	// return null;
	// }

	public static String encoding(String str) {
		try {
			return Py64.encode(MessageDigest.getInstance("MD5").digest(
					str.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// MD5和UTF-8肯定存在，不会出错，无视
			return null;
		}
	}
}
