/**
 * 
 */
package com.ipinyou.base.freemarker;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 用于在ftl使用spring.bean.xxx的方式获取spring bean对象，例如：${spring.bean.userCategoryMap[key]}
 * 
 * @author lijt
 * 
 */
public class BuildSpringContextMethod implements TemplateMethodModelEx,
		ApplicationContextAware {
	private ApplicationContext ac;
	private SpringContextHashModel instance;

	/*
	 * (non-Javadoc)
	 * 
	 * @see freemarker.template.TemplateMethodModelEx#exec(java.util.List)
	 */
	@Override
	public Object exec(@SuppressWarnings("rawtypes") List args)
			throws TemplateModelException {
		if (args.size() != 0) {
			throw new TemplateModelException("Wrong	arguments!");
		}
		if (instance == null) {
			instance = new SpringContextHashModel(ac);
		}
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ac = applicationContext;
	}

	public static class SpringContextHashModel implements TemplateHashModel {
		public SpringContextHashModel(ApplicationContext ac) {
			super();
			this.ac = ac;
		}

		private ApplicationContext ac;

		/*
		 * (non-Javadoc)
		 * 
		 * @see freemarker.template.TemplateHashModel#get(java.lang.String)
		 */
		@Override
		public TemplateModel get(String key) throws TemplateModelException {
			return ObjectWrapper.DEFAULT_WRAPPER.wrap(ac.getBean(key));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see freemarker.template.TemplateHashModel#isEmpty()
		 */
		@Override
		public boolean isEmpty() throws TemplateModelException {
			return false;
		}
	}

}
