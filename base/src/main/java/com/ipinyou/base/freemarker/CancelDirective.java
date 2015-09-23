/**
 * 
 */
package com.ipinyou.base.freemarker;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.ipinyou.base.spring.SubmitCounterInterceptor;

import freemarker.core.Environment;
import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 取消按钮，点击此按钮之后将跳转到来当前页面的前一个页面，参数 class: 样式class， text:显示文字，默认为"取消"
 * 例如：
 * &lt;@cancel class=&quot;btn&quot; /&gt;
 * 
 * @author lijt
 * 
 */
public class CancelDirective implements TemplateDirectiveModel {

	private static final String PARAM_CLASS = "class";

	private static final String PARAM_TEXT = "text";

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
		HttpServletRequest request = ((HttpRequestHashModel) env
				.getVariable(FreemarkerServlet.KEY_REQUEST)).getRequest();
		if (StringUtils.isBlank(request.getHeader("referer"))
				|| request.getHeader("referer").endsWith("/login")
				|| "true".equals(request
						.getParameter(SubmitCounterInterceptor.NO_COUNT_FIELD))) {
			env.getOut().write(
					"<input type=\"hidden\" name=\""
							+ SubmitCounterInterceptor.NO_COUNT_FIELD
							+ "\" value=\"true\"/>\n");
			return;
		}
		String cls = "btnCancel";
		if (params.containsKey(PARAM_CLASS)) {
			cls = ((SimpleScalar) params.get(PARAM_CLASS)).getAsString();
		}
		String text = com.ipinyou.base.util.I18nResourceUtil.getResource("base.freemarker.CancelDirective.1000")/*取消*/;
		if (params.containsKey(PARAM_TEXT)) {
			text = ((SimpleScalar) params.get(PARAM_TEXT)).getAsString();
		}
		Object obj = request.getAttribute(SubmitCounterInterceptor.NAME_FIELD);
		int count = 0;
		if (obj != null) {
			count = (int) obj;
		}
		env.getOut().write(
				"<input type=\"hidden\" name=\""
						+ SubmitCounterInterceptor.NAME_FIELD + "\" value=\""
						+ count + "\"/>");
		env.getOut().write(
				"<input type=\"button\" name=\"formCancel\" value=\"" + text
					+ "\" class=\"" + cls
					+ "\" onclick=\"cancelFormConfirm(this, -"+(count+1)
					+ ");\"/>\n");

	}
}
