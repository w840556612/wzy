package com.ipinyou.base.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ipinyou.base.ClientInfoHolder;
import com.ipinyou.base.model.ClientInfo;
import com.ipinyou.base.model.LoginInfo;
import com.ipinyou.base.util.IpUtils;
import com.mcas.tools.PyidUtils;

/**
 * 将客户端信息(ClientInfo)放到ClientInfoHolder(ThreadLocal方式)
 * 
 * @author lijt
 * 
 */
public class SetClientInfoFilter extends OncePerRequestFilter {

	/**
	 * 
	 */
	public SetClientInfoFilter() {
	}

	private final static String NO_LOGIN_INFO = "noLoginInfo";

	private boolean noLoginInfo;

	@Override
	protected void initFilterBean() throws ServletException {
		super.initFilterBean();
		FilterConfig config = this.getFilterConfig();
		if ("true".equals(config.getInitParameter(NO_LOGIN_INFO))) {
			noLoginInfo = true;
		}
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
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Map<String, String> cookieMap = getCookiesMap(request);
		String domain = ".ipinyou.com";
		String pyid = getPyid(response, domain, cookieMap);
		String sessionId = getSessionId(response, domain, cookieMap);
		String ip = IpUtils.getIpAddr(request);
		String userAgent = request.getHeader("User-Agent");
		String referrerUrl = request.getHeader("Referer");
		String source = getSource(request, response, cookieMap);
		LoginInfo loginInfo = null;
		if (!noLoginInfo) {
			String uri = request.getRequestURI();
			String contextPath = request.getContextPath();
			if (contextPath.length() != 0) {
				uri = uri.substring(contextPath.length());
			}
			if (!"/test/".equals(uri)) { // 健康检查不生成session
				loginInfo = (LoginInfo) SecurityUtils.getSubject()
						.getPrincipal();
				Session session = SecurityUtils.getSubject().getSession();
				if (loginInfo != null && session != null) {
					loginInfo.setLoginId((String) session.getId());
				}
			}
		}
		ClientInfo info = null;
		if (loginInfo != null) {
			info = new ClientInfo(pyid, sessionId, ip, userAgent, referrerUrl,
					source, String.valueOf(loginInfo.getRealUserId()),
					loginInfo.getRealName(), loginInfo.getLoginId(),
					loginInfo.getLoginTime());
		} else {
			info = new ClientInfo(pyid, sessionId, ip, userAgent, referrerUrl,
					source);
		}
		try {
			ClientInfoHolder.setClientInfo(info);
			ClientInfoHolder.setOperationLog(true);
			filterChain.doFilter(request, response);
		} finally {
			ClientInfoHolder.setOperationLog(false);
			ClientInfoHolder.setClientInfo(null);

		}

	}

	private Map<String, String> getCookiesMap(HttpServletRequest req) {
		Map<String, String> map = new HashMap<String, String>();
		if (req.getCookies() != null) {
			for (Cookie c : req.getCookies()) {
				map.put(c.getName(), c.getValue());
			}
		}
		return map;
	}

	public static final String COOKIE_KEY_PYID = "PYID";
	public static final String COOKIE_KEY_SESSIONID = "sessionId";

	public static final int COOKIE_YEAR_EXPIRE = 24 * 3600 * 365;
	public static final String HEADER_P3P_VALUE = "CP=\"NON DSP COR CURa ADMa DEVa TAIa PSAa PSDa IVAa IVDa CONa HISa TELa OTPa OUR UNRa IND UNI COM NAV INT DEM CNT PRE LOC\"";

	/**
	 * @param res
	 * @param domain
	 * @param key
	 * @param value
	 * @param expire
	 * 
	 * @see http
	 *      ://stackoverflow.com/questions/4620172/expires-string-in-cookie-
	 *      header
	 * @see http
	 *      ://stackoverflow.com/questions/361231/persistent-cookies-from-a-
	 *      servlet -in-ie/
	 * @see http://wozailongyou.iteye.com/blog/619864
	 */
	public void createCookie(HttpServletResponse res, String domain,
			String key, String value, int expire) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(COOKIE_YEAR_EXPIRE);
		cookie.setPath("/");
		cookie.setDomain(domain);
		res.addCookie(cookie);
		// Date expdate = new Date();
		// expdate.setTime(expdate.getTime() + (COOKIE_YEAR_EXPIRE * expire));
		// DateFormat df = new SimpleDateFormat("dd MMM yyyy kk:mm:ss z");
		// df.setTimeZone(TimeZone.getTimeZone("GMT"));
		// res.setHeader("Set-Cookie", key + "=" + value + "; Path=/; Domain="
		// + domain + "; Expires=" + df.format(expdate));
	}

	private String getPyid(HttpServletResponse res, String domain,
			Map<String, String> cookieMap) {
		String pyid = cookieMap.get(COOKIE_KEY_PYID);
		if (StringUtils.isBlank(pyid)) {
			pyid = PyidUtils.generate();
			res.setHeader("P3P", HEADER_P3P_VALUE);
			createCookie(res, domain, COOKIE_KEY_PYID, pyid, COOKIE_YEAR_EXPIRE);
		}
		return pyid;
	}

	private String getSessionId(HttpServletResponse res, String domain,
			Map<String, String> cookieMap) {
		String sid = cookieMap.get(COOKIE_KEY_SESSIONID);
		if (StringUtils.isBlank(sid)) {
			sid = PyidUtils.generateSessionId();
			res.setHeader("P3P", HEADER_P3P_VALUE);
			createCookie(res, domain, COOKIE_KEY_SESSIONID, sid, -1);
		}
		return sid;
	}

	private String getSource(HttpServletRequest request,
			HttpServletResponse res, Map<String, String> cookieMap) {
		String source = cookieMap.get("source");
		if (StringUtils.isBlank(source)) {
			String cookie = request.getHeader("Cookie");
			if (cookie != null) {
				for (String arr : StringUtils.split(cookie, ';')) {
					if (arr.trim().startsWith("__utmz=")) {
						return arr.trim().substring("__utmz=".length());
					}
				}
			}
			// return cookieMap.get("__utmz");
		} else {

		}
		return source;
	}

	// private static String parserUtmz(String utmz){
	// if(StringUtils.isBlank(utmz)){
	// return null;
	// }
	// int pos = utmz.indexOf(".utmcsr=");
	// if(pos==-1){
	// return null;
	// }
	// String[] arr = StringUtils.split(utmz.substring(pos+1),'|');
	// Map<String,String> map = new HashMap<String, String>(4);
	// for(String s:arr){
	// String[] a = StringUtils.split(s,'=');
	// if(a.length==2){}
	// map.put(a[0], a[1]);
	// }
	// if(map.containsKey("utmcsr")){
	// if(map.containsKey("utmcct")){
	// return map.get("utmcsr")+map.get("utmcct");
	// }else{
	// return map.get("utmcsr");
	// }
	// }else{
	// return null;
	// }
	// }

	// public static void main(String[] args) {
	// // String s =
	// "193610029.1366775296.69.2.utmcsr=redmine.ipinyou.com.cn|utmccn=(referral)|utmcmd=referral|utmcct=/projects/wolf001/wiki/%E6%9D%8E%E6%B1%9F%E6%B6%9B";
	// // System.out.println(parserUtmz(s));
	// for(String
	// arr:StringUtils.split("JSESSIONID=CD9F68E2051610AC2EE9CCD550438744; __utma=68420435.878975389.1366943712.1366943712.1366943712.1; __utmb=68420435.168.10.1366943712; __utmz=68420435.1366943712.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); PYID=D4QAcDAQaVH; sessionId=D4QB9r1e+01Q; __utmc=68420435",';')){
	// if(arr.trim().startsWith("__utmz=")){
	// System.out.println(arr.trim().substring("__utmz=".length()));
	// }
	// }
	// }
}