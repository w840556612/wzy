/**
 * 
 */
package com.ipinyou.base.install;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 系统安装监听器，使用监听器实现，启动启动后即执行实现了com.ipinyou.base.install.Installer接口的bean
 * @author lijt
 * 
 */
public class InstallListener implements ServletContextListener {
	protected final transient Logger logger = LoggerFactory
			.getLogger(getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {

		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		Collection<Installer> c = ctx.getBeansOfType(Installer.class).values();
		List<Installer> list = new ArrayList<Installer>(c);
		Collections.sort(list,new Comparator<Installer>(){
			@Override
			public int compare(Installer o1, Installer o2) {
				return o1.getOrder()-o2.getOrder();
			}});
		logger.debug("开始安装!");
		for (Installer in : list) {
			logger.info("开始执行" + in.getClass() + ".run()");
			try {
				in.run();
				logger.info("执行" + in.getClass() + ".run()完成！");
			} catch (Throwable e) {
				logger.error("执行" + in.getClass() + ".run()时,发生错误:"
						+ e.getLocalizedMessage());
				logger.error(ExceptionUtils.getStackTrace(e));
				continue;
			}

		}
		logger.debug("安装结束!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
