package com.ipinyou.base.filter;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ipinyou.base.constant.ResponseExtStatus;
import com.ipinyou.base.util.RequestUtils;

/**
 * 控制对资源的恶意刷新
 * 30秒之内页面访问超过10次，或者300秒内页面访问超过60次时提示用户输入验证码
 * @author zhyhang
 *
 */
public class DenyFrequentRefreshFilter extends OncePerRequestFilter {
	
	public final static String KEY_CLIENTACCESSHOLDER="clientaccessholder";
	public final static String KEY_KAPTCHAR_DENYFREQUENT="kaptcha_denyfrequent";
	
	/**
	 * 每个session的访问计数
	 */
	public static class ClientAccessHolder implements Serializable{
		
		private static final long serialVersionUID = 1785169890105567462L;

		//5 minutes counting
		private short[] accessCounter=new short[300];
		
		private long baseTime=System.currentTimeMillis();
		
		public synchronized void access(){
			long time=System.currentTimeMillis()-baseTime;
			int elapseSeconds=(int) TimeUnit.MILLISECONDS.toSeconds(time);
			if(elapseSeconds>=accessCounter.length){
				reset();
				return;
			}
			accessCounter[elapseSeconds]++;
		}
		
		public synchronized void reset() {
			this.baseTime = System.currentTimeMillis();
			for (int i = 0; i < accessCounter.length; i++) {
				accessCounter[i] = 0;
			}
		}
		
		public synchronized boolean isOverAccess(int limit,int seconds){
			int counter=0;
			int index=(int) TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()-this.baseTime);
			for (int i = index; i >(index>=seconds?(index - seconds):-1); i--) {
				counter+=accessCounter[i];
			}
			return counter>limit;
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			String url = request.getServletPath();
			//非动态请求则不算刷新
			if(isNeedFilter(url)){
				ClientAccessHolder accessHolder = (ClientAccessHolder) SecurityUtils.getSubject().getSession().getAttribute(KEY_CLIENTACCESSHOLDER);
				if(null==accessHolder){
					accessHolder=new ClientAccessHolder();
					SecurityUtils.getSubject().getSession().setAttribute(KEY_CLIENTACCESSHOLDER, accessHolder);
				}
				accessHolder.access();
				if(StringUtils.isNotBlank((String)SecurityUtils.getSubject().getSession().getAttribute(KEY_KAPTCHAR_DENYFREQUENT))
						|| accessHolder.isOverAccess(30, 10)){
					if((request.getMethod().equalsIgnoreCase(RequestMethod.POST.name()) ||
							request.getMethod().equalsIgnoreCase(RequestMethod.PUT.name())) && RequestUtils.isRedirectHttp(request)){//在某个https提交处产生了频繁刷新异常
						response.sendRedirect(RequestUtils.issueRedirectHttp(request, null, null, null, null, "/security/captcha/"));
						return;
					}
					response.addIntHeader(ResponseExtStatus.KEY_RESPONSE_EXT_STATUS, ResponseExtStatus.STATUS_ABUSE_REFRESH);
					response.sendError(ResponseExtStatus.STATUS_ABUSE_REFRESH);
					return;
				}
			}
			filterChain.doFilter(request, response);
	}
	
	private boolean isNeedFilter(String url){
		boolean flag=true;
		String pureUrl=url.trim().toLowerCase();
		flag=flag && StringUtils.isNotBlank(pureUrl) 
				 && (pureUrl.endsWith("/") || pureUrl.substring(pureUrl.lastIndexOf('/')).indexOf('.')<0)
				 && SecurityUtils.getSubject().getPrincipal()!=null
				 && pureUrl.indexOf("/security/captcha")<0;
		return flag;
	}
}