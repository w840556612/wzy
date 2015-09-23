/**
 * 
 */
package com.ipinyou.base.freemarker;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import freemarker.core.Environment;
import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 用于根据当前页面url，替换其中部分参数的值，生成新的url字符串
 * 例如：
 * &lt;@changeUrlParam param=&quot;page.size&quot; value=10/&gt;
 * @author lijt
 * 
 */
public class ChangeUrlParamDirective implements TemplateDirectiveModel {

	private final static String PARAM_PARAM = "param";

	private final static String PARAM_VALUE = "value";

	/*
	 * (non-Javadoc)
	 * 
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.
	 * Environment, java.util.Map, freemarker.template.TemplateModel[],
	 * freemarker.template.TemplateDirectiveBody)
	 */
	@Override
	public void execute(Environment env, @SuppressWarnings("rawtypes") Map p, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		HttpRequestHashModel requestModel = (HttpRequestHashModel) env
				.getVariable(FreemarkerServlet.KEY_REQUEST);
		HttpServletRequest req = requestModel.getRequest();
		if (!p.containsKey(PARAM_PARAM)) {
			throw new TemplateModelException("Argument " + PARAM_PARAM
					+ " is required!");
		}
		if (!p.containsKey(PARAM_VALUE)) {
			throw new TemplateModelException("Argument " + PARAM_VALUE
					+ " is required!");
		}
		String param = ((SimpleScalar)p.get(PARAM_PARAM)).getAsString();
		Object value = p.get(PARAM_VALUE);
		StringBuilder uri = new StringBuilder(req.getRequestURI());
		boolean first = true;
		for (@SuppressWarnings("unchecked")
		Enumeration<String> params = req.getParameterNames(); params.hasMoreElements();) {
			String key = params.nextElement();
			if (param.equals(key)) {
				continue;
			}
			String[] values = req.getParameterValues(key);
			for (String val : values) {
				if (first) {
					uri.append("?");
					first = false;
				} else {
					uri.append("&amp;");
				}
				uri.append(key);
				uri.append('=');
				uri.append(val);
			}
		}
		if (first) {
			uri.append("?");
			first = false;
		} else {
			uri.append("&amp;");
		}
		uri.append(param);
		uri.append('=');
		uri.append(value.toString());
		env.getOut().write(uri.toString());
	}

}
