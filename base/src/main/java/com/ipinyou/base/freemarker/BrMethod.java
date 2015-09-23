package com.ipinyou.base.freemarker;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * freemarker模板中br(string)函数，用于将换行符号处理成<br />
 * 例如： ${br('xxxxx\nyyyyy')}
 * 
 * @author lijt
 * 
 */
public class BrMethod implements TemplateMethodModel {
	private final static String br = "<br />";

	private final static String[] new_line = { "\r\n", "\r", "\n" };

	public Object exec(@SuppressWarnings("rawtypes") List args)
			throws TemplateModelException {
		if (args.size() != 1) {
			throw new TemplateModelException("Wrong	arguments!");
		}
		String str = (String) args.get(0);
		for (String s : new_line) {
			str = StringUtils.replace(str, s, br);
		}
		return str;
	}

}