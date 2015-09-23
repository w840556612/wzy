package com.ipinyou.base.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Locale;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ipinyou.base.entity.Entity;
import com.ipinyou.base.model.LoginInfo;

public class I18nResourceUtil {
	private static MessageSource messageSource = new ClassPathXmlApplicationContext("spring/i18nresource.xml") ;

	public static String getResource(String code, Locale locale) {
		return messageSource.getMessage(code, null, locale);
	}
	
	public static String getResource(String code) {
		return messageSource.getMessage(code, null, getLocale());
	}

	public static String getContent(@SuppressWarnings("rawtypes") Class clazz,
			Entity entity, String field, String origValue) {
		Locale locale = getLocale();
		if (Locale.ENGLISH.equals(locale)) {
			String realField = field + "En";
			try {
				PropertyDescriptor pd = new PropertyDescriptor(realField, clazz);
				// 获得get方法
				Method getMethod = pd.getReadMethod();
				return (String) getMethod.invoke(entity);
			} catch (Exception e) {
				System.out.println("I18nResourceUtil. locale: " + locale
						+ ", clazz: " + clazz.getSimpleName() + ", field: "
						+ field + ", origValue: " + origValue + ", realField: "
						+ realField);
				e.printStackTrace();
			}
		}

		return origValue;
	}

	public static Locale getLocale() {
		Locale locale = Locale.SIMPLIFIED_CHINESE;

		try {
			Subject subject = ThreadContext.getSubject();
			if (subject != null) {
				LoginInfo loginInfo = (LoginInfo) subject.getPrincipal();
				if (loginInfo != null) {
					if (loginInfo.getLanguage() != null) {
						locale = loginInfo.getLanguage();
					}
				}
			}
		} catch (Exception e) {
			locale = Locale.SIMPLIFIED_CHINESE;
		}

		return locale;
	}
}
