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
 * 用于产生排序状态和链接的宏 必选参数 field: 对应实体的属性 title: 显示的标题 可选参数 default: asc或者desc,
 * asc表示默认按照当前field升序排序, desc表示默认按照当前field降序排序 init: asc或者desc,
 * asc表示第一次按照当前field排序时升序（默认）, desc表示第一次按照当前field排序时降序 使用示例： &lt;@fieldTh
 * title=&quot;计划名称" field="name"/&gt;
 * 
 * @author lijt
 * 
 */
public class FieldThDirective implements TemplateDirectiveModel {

	private static final String PARAM_FIELD = "field";

	private static final String PARAM_TITLE = "title";

	private static final String PARAM_DEFAULT = "default";

	private static final String PARAM_FIRST = "init";

	private static final String URL_PARAM_SORT = "page.sort";

	private static final String URL_PARAM_DIR = "page.sort.dir";

	/*
	 * (non-Javadoc)
	 * 
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.
	 * Environment, java.util.Map, freemarker.template.TemplateModel[],
	 * freemarker.template.TemplateDirectiveBody)
	 */
	@Override
	public void execute(Environment env,
			@SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		HttpRequestHashModel requestModel = (HttpRequestHashModel) env
				.getVariable(FreemarkerServlet.KEY_REQUEST);
		HttpServletRequest req = requestModel.getRequest();
		if (!params.containsKey(PARAM_FIELD)) {
			throw new TemplateModelException("Argument " + PARAM_FIELD
					+ " is required!");
		}
		String field = ((SimpleScalar) params.get(PARAM_FIELD)).getAsString();
		if (!params.containsKey(PARAM_TITLE)) {
			throw new TemplateModelException("Argument " + PARAM_TITLE
					+ " is required!");
		}
		String title = ((SimpleScalar) params.get(PARAM_TITLE)).getAsString();
		title = title.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
		Boolean def = null;
		if (params.containsKey(PARAM_DEFAULT)) {
			SimpleScalar scalar = (SimpleScalar) params.get(PARAM_DEFAULT);
			if ("asc".equals(scalar.getAsString())) {
				def = true;
			} else if ("desc".equals(scalar.getAsString())) {
				def = false;
			}
		}
		if (field.equals(req.getParameter(URL_PARAM_SORT))) {
			if ("desc".equals(req.getParameter(URL_PARAM_DIR))) {
				def = false;
			} else {
				def = true;
			}
		}
		boolean initAsc = true;
		if (params.containsKey(PARAM_FIRST)) {
			SimpleScalar scalar = (SimpleScalar) params.get(PARAM_FIRST);
			if ("desc".equals(scalar.getAsString())) {
				initAsc = false;
			}
		}

		StringBuilder content = new StringBuilder(300);
		content.append("<a href=\"");
		content.append(req.getRequestURI());
		boolean first = true;
		for (@SuppressWarnings("unchecked")
		Enumeration<String> p = req.getParameterNames(); p.hasMoreElements();) {
			String key = p.nextElement();
			if (URL_PARAM_SORT.equals(key) || URL_PARAM_DIR.equals(key)) {
				continue;
			}
			String[] values = req.getParameterValues(key);
			for (String val : values) {
				if (first) {
					content.append("?");
					first = false;
				} else {
					content.append("&amp;");
				}
				content.append(key);
				content.append('=');
				content.append(val);
			}
		}
		if (first) {
			content.append("?");
			first = false;
		} else {
			content.append("&amp;");
		}
		content.append(URL_PARAM_SORT);
		content.append('=');
		content.append(field);
		content.append("&amp;");
		content.append(URL_PARAM_DIR);
		content.append('=');
		if (def == null) {
			if (initAsc) {
				content.append("asc");
			} else {
				content.append("desc");
			}
		} else if (!def.booleanValue()) {
			content.append("asc");
		} else {
			content.append("desc");
		}
		content.append("\">");
		content.append(title);
		if (def != null) {
			if (def.booleanValue()) {
				content.append("<span class=\"asc\"></span>");
			} else {
				content.append("<span class=\"desc\"></span>");
			}
		}
		content.append("</a>");
		env.getOut().write(content.toString());
	}

}
