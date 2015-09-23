/**
 * 
 */
package com.ipinyou.base.shiro;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;

/**
 * @author lijt
 * 
 */
public class ShiroFilterFactoryBean extends
		org.apache.shiro.spring.web.ShiroFilterFactoryBean {
	protected final transient Logger logger = LoggerFactory
			.getLogger(getClass());

	@Override
	protected AbstractShiroFilter createInstance() throws Exception {

		logger.debug("Creating Shiro Filter instance.");

		SecurityManager securityManager = getSecurityManager();
		if (securityManager == null) {
			String msg = "SecurityManager property must be set.";
			throw new BeanInitializationException(msg);
		}

		if (!(securityManager instanceof WebSecurityManager)) {
			String msg = "The security manager does not implement the WebSecurityManager interface.";
			throw new BeanInitializationException(msg);
		}

		FilterChainManager manager = createFilterChainManager();

		// Expose the constructed FilterChainManager by first wrapping it in a
		// FilterChainResolver implementation. The AbstractShiroFilter
		// implementations
		// do not know about FilterChainManagers - only resolvers:
		PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
		chainResolver.setFilterChainManager(manager);

		// Now create a concrete ShiroFilter instance and apply the acquired
		// SecurityManager and built
		// FilterChainResolver. It doesn't matter that the instance is an
		// anonymous inner class
		// here - we're just using it because it is a concrete
		// AbstractShiroFilter instance that accepts
		// injection of the SecurityManager and FilterChainResolver:
		return new SpringShiroFilter((WebSecurityManager) securityManager,
				chainResolver);
	}

	/**
	 * Ordinarily the {@code AbstractShiroFilter} must be subclassed to
	 * additionally perform configuration and initialization behavior. Because
	 * this {@code FactoryBean} implementation manually builds the
	 * {@link AbstractShiroFilter}'s
	 * {@link AbstractShiroFilter#setSecurityManager(org.apache.shiro.web.mgt.WebSecurityManager)
	 * securityManager} and
	 * {@link AbstractShiroFilter#setFilterChainResolver(org.apache.shiro.web.filter.mgt.FilterChainResolver)
	 * filterChainResolver} properties, the only thing left to do is set those
	 * properties explicitly. We do that in a simple concrete subclass in the
	 * constructor.
	 */
	private static final class SpringShiroFilter extends AbstractShiroFilter {
		protected final transient Logger logger = LoggerFactory
				.getLogger(getClass());

		protected SpringShiroFilter(WebSecurityManager webSecurityManager,
				FilterChainResolver resolver) {
			super();
			if (webSecurityManager == null) {
				throw new IllegalArgumentException(
						"WebSecurityManager property cannot be null.");
			}
			setSecurityManager(webSecurityManager);
			if (resolver != null) {
				setFilterChainResolver(resolver);
			}
		}

		@Override
		protected void doFilterInternal(ServletRequest req,
				ServletResponse response, FilterChain chain)
				throws ServletException, IOException {
			HttpServletRequest request = (HttpServletRequest) req;
			String uri = request.getRequestURI();
			String contextPath = request.getContextPath();
			if (contextPath.length() != 0) {
				uri = uri.substring(contextPath.length());
			}
			if (!"/test/".equals(uri)) {// 健康检查不生成session
				super.doFilterInternal(request, response, chain);
			} else {
				chain.doFilter(request, response);
			}
		}

		@Override
		protected void updateSessionLastAccessTime(ServletRequest request,
				ServletResponse response) {
			if (!isHttpSessions()) { // 'native' sessions
				Subject subject = SecurityUtils.getSubject();
				// Subject should never _ever_ be null, but just in case:
				if (subject != null) {
					Session session = subject.getSession(false);
					if (session != null) {
						try {
							Date lastAccessTime = session.getLastAccessTime();
							if (lastAccessTime == null
									|| new Date().getTime()
											- lastAccessTime.getTime() >= 10 * 60 * 1000) {// 最后访问时间超过10分钟才更新一次

								session.touch();
							}
						} catch (Throwable t) {
							logger.error(
									"session.touch() method invocation has failed.  Unable to update"
											+ "the corresponding session's last access time based on the incoming request.",
									t);
						}
					}
				}
			}
		}

	}

}
