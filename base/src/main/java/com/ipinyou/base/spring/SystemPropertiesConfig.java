/**
 * 
 */
package com.ipinyou.base.spring;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * 从System Property变量指定的路径下读取资源的实现
 * 
 *         配置示例： <bean id="propertyConfigurer" class=
 *         "org.springframework.beans.factory.config.PropertyPlaceholderConfigurer"
 *         > <property name="locations"> <list> <bean
 *         class="com.bupticet.beans.SystemPropertiesConfig"> <property
 *         name="sysPropName" value="test.home" /> <property name="subPath"
 *         value="spring/test.properties" /> </bean> <bean
 *         class="com.bupticet.beans.SystemPropertiesConfig"> <property
 *         name="subPath" value="config/config.properties" /> </bean>
 *         <value>classpath:config/config.properties</value> </list> </property>
 *         </bean>
 * @author lijt
 */
public class SystemPropertiesConfig implements FactoryBean<Resource> {

	private Resource res;

	private String sysPropName;

	private String subPath;

	public void setSysPropName(String sysPropName) {
		this.sysPropName = sysPropName;
	}

	public void setSubPath(String subPath) {
		this.subPath = subPath;
	}

	public Resource getObject() throws Exception {
		if (StringUtils.isBlank(subPath)) {
			throw new IllegalArgumentException("必须设置subPath!");
		}
		if (res == null) {
			if (sysPropName != null && !"".equals(sysPropName.trim())) {
				String sysPropPath = System.getProperty(sysPropName.trim());
				if (sysPropPath != null && !"".equals(sysPropPath)) {
					if (subPath.startsWith("/")) {
						subPath = subPath.substring(1);
					}
					String sep = System.getProperty("file.separator");
					if (!sysPropPath.endsWith(sep)) {
						sysPropPath += sep;
					}
					String path = sysPropPath
							+ StringUtils.replace(subPath, "/", sep);
					res = new FileSystemResource(path);
					return res;
				}
			}
			res = new ClassPathResource(subPath);
			return res;
		} else {
			return res;
		}
	}

	public Class<?> getObjectType() {
		return Resource.class;
	}

	public boolean isSingleton() {
		return Boolean.TRUE;
	}

}
