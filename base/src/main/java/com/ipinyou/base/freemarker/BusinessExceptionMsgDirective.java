package com.ipinyou.base.freemarker;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 打印业务异常信息，使用示例：
 * &lt;@businessExceptionMsg /&gt;
 * @author ts
 *
 */
public class BusinessExceptionMsgDirective implements TemplateDirectiveModel {

	/*
	 * (non-Javadoc)
	 * 
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.
	 * Environment, java.util.Map, freemarker.template.TemplateModel[],
	 * freemarker.template.TemplateDirectiveBody)
	 */
	@Override
	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		StringModel exception = (StringModel) env
				.getVariable(SimpleMappingExceptionResolver.DEFAULT_EXCEPTION_ATTRIBUTE);
		if (exception != null) {
			Exception e = (Exception) exception.getWrappedObject();
			if(e instanceof IllegalArgumentException){
				env.getOut().write(e.getMessage());
			}
		}
	}
}
