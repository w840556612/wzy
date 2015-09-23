/**
 * 
 */
package com.ipinyou.base.freemarker;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.ipinyou.base.token.TokenInterceptor;
import com.ipinyou.base.token.TokenWriteInterceptor;

import freemarker.core.Environment;
import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 生成token参数，防止重复提交和csrf 参数 name: 表单名称 例如： &lt;@token /&gt;
 * 
 * @author lijt
 * 
 */
public class TokenDirective implements TemplateDirectiveModel {

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
//		HttpServletResponse res = requestModel.getResponse();
		HttpServletRequest req = requestModel.getRequest();
		// Session session = SecurityUtils.getSubject().getSession();
		String token = (String) req.getAttribute(TokenWriteInterceptor.REQUEST_ATTR_TOKEN);
		// support multi-tokens in single page,please add name when using the
		// token directive,expamle:<@token name="campaign"/>
		// SimpleScalar nameValue = (SimpleScalar) params
		// .get(TokenInterceptor.TOKEN_PARA_NAME);
		// if (null == nameValue ||
		// StringUtils.isBlank(nameValue.getAsString())) {
		// throw new TemplateModelException(
		// "The name parameter lost,please check your ftl!");
		// }
		// session.setAttribute(TokenInterceptor.TOKEN_NAME_PREFIX + "."
		// + nameValue.getAsString(), token);
//		Cookie cookie = new Cookie(TokenInterceptor.TOKEN_NAME,
//				TokenInterceptor.encoding(token));
//		String contextPath = requestModel.getRequest().getContextPath();
//		if (contextPath == null || contextPath.length() == 0) {
//			contextPath = "/";
//		}
//		cookie.setPath(contextPath);
//		cookie.setMaxAge(-1);
//		res.addCookie(cookie);
		if(!StringUtils.isBlank(token)){
			env.getOut().write(
					"<input type=\"hidden\" name=\"" + TokenInterceptor.TOKEN_NAME
							+ "\" value=\"" + TokenInterceptor.encoding(token) + "\"/>\n");
		}
	}

}
