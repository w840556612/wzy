/**
 * 
 */
package com.ipinyou.base.freemarker;

import java.util.Map;

import com.ipinyou.base.entity.UserDefineType;

import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 为了在freemarker的模板中像与程序中一样使用bean，进行一些封装处理
 * @author zhyhang
 * 
 *
 */
public class TemplateBeansWrapper extends DefaultObjectWrapper {

	@Override
	public TemplateModel wrap(Object obj) throws TemplateModelException {
		if((obj instanceof Map) && !(obj instanceof UserDefineType)){
			return super.wrap(obj);//由于BeansWrapper处理map的一些问题，还是使用DefaultObjectWrapper
		}
		else{
			return handleUnknownType(obj);//强制使用BeansWrapper
		}
	}

}
