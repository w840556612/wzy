/**
 * 
 */
package com.ipinyou.base.freemarker;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 对字符串按照javascript语法转义处理
 * 
 * @author lijt
 * 
 */
public class JsEscapeMethod implements TemplateMethodModel {

	/*
	 * (non-Javadoc)
	 * 
	 * @see freemarker.template.TemplateMethodModel#exec(java.util.List)
	 */
	@Override
	public Object exec(@SuppressWarnings("rawtypes") List args)
			throws TemplateModelException {
		if (args.size() != 1) {
			throw new TemplateModelException("Wrong	arguments!");
		}
		String str = (String) args.get(0);
		return StringEscapeUtils.escapeEcmaScript(str);
	}

	public static void main(String[] args) throws TemplateModelException {
		System.out.println(1);
		System.out.println("f\n'\"");
		System.out.println(org.apache.commons.lang.StringEscapeUtils
				.escapeJavaScript("f\n'\t\r\""));
		System.out.println(2);
		System.out.println(org.apache.commons.lang3.StringEscapeUtils
				.escapeEcmaScript("f\n'\t\r\""));
		System.out.println(3);
		System.out.println(new JsEscapeMethod().exec(Arrays
				.asList("f\n'\t\r\"")));
		System.out.println(4);
	}
}
