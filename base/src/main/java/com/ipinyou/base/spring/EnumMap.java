/**
 * 
 */
package com.ipinyou.base.spring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 将枚举类转化为Map，枚举类必须有text（可配置）属性和相关方法，并且不要覆写toString方法，所有枚举实例的text属性不能为空字符串
 * 
 * @author lijt
 * 
 */
public class EnumMap implements FactoryBean<Map<String, String>>,
		InitializingBean {

	protected final transient Logger logger = LoggerFactory
			.getLogger(getClass());
	private Map<String, String> map;

	private String enumClass;

	private String property = "text";

	private boolean addHeader;
	
	private String headerText;
	
	public void setEnumClass(String enumClass) {
		this.enumClass = enumClass;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public void setAddHeader(boolean addHeader) {
		this.addHeader = addHeader;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		map = new LinkedHashMap<String, String>();
		if(addHeader){
			map.put("",StringUtils.isBlank(headerText)?"--请选择--":headerText);
		}
		try {
			Class<?> c = Class.forName(enumClass);
			Method method = c
					.getDeclaredMethod(
							"get" + StringUtils.capitalize(property),
							new Class<?>[] {});
			for (Object o : c.getEnumConstants()) {
				map.put(o.toString(), (String) method.invoke(o));
			}
		} catch (ClassNotFoundException | NoSuchMethodException
				| SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public Map<String, String> getObject() throws Exception {
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class<?> getObjectType() {
		return Map.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}
}
