/**
 * 
 */
package com.ipinyou.base.freemarker;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 在模板中调用静态方法：<p>
 * ${statics["java.lang.System"].currentTimeMillis()} 
 * @author zhyhang
 *
 */
public class StaticClassInvokeVariable implements TemplateHashModel {
	
	private TemplateHashModel statics=BeansWrapper.getDefaultInstance().getStaticModels();

	@Override
	public TemplateModel get(String key) throws TemplateModelException {
		return statics.get(key);
	}

	@Override
	public boolean isEmpty() throws TemplateModelException {
		return statics.isEmpty();
	}

}
