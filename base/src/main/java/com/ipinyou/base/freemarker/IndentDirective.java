/**
 * 
 */
package com.ipinyou.base.freemarker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 增加或者减少缩进 缩进使用\t
 * 例如： &lt;@indent&gt;...&lt;/@indent&gt;
 * 
 * @author lijt
 * @see: http://yuanhuiwu.iteye.com/blog/1133067
 * @see: http://comments.gmane.org/gmane.comp.web.freemarker.devel/3419
 * @see: google for indent freemarker
 */
public class IndentDirective implements TemplateDirectiveModel {
	public final static String DIRECTIVE_NAME = "indent";

	private final static String PARAM_NAME = "num";

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
		if (body == null) {
			return;// do nothing
		}
		int num = 1;
		if (params.containsKey(PARAM_NAME)) {
			Object value = params.get(PARAM_NAME);
			try {
				num = Integer.parseInt(value.toString());
			} catch (Throwable e) {
				throw new TemplateModelException("Wrong	argument format: "
						+ value.toString() + " Info:" + e.getLocalizedMessage());
			}
		}
		if (num == 0) {
			body.render(env.getOut());
			return;// do nothing
		}
		StringWriter writer = new StringWriter();
		body.render(writer);
		String results = this.indent(writer.toString(), num);
		if (results != null) {
			env.getOut().write(results);
		}
	}

	/**
	 * @param string
	 * @param num
	 * @return
	 * @throws IOException
	 */
	private String indent(String source, int num) throws IOException {
		if (source == null) {
			return null;
		}
		StringBuffer append = null;
		if (num == 0) {
			return source;
		} else if (num > 0) {
			append = new StringBuffer();
			for (int i = 0; i < num; i++) {
				append.append("\t");
			}
		}
		StringReader stringReader = new StringReader(source);
		BufferedReader reader = new BufferedReader(stringReader);

		StringBuffer buf = new StringBuffer();
		String line = null;
		int lineNum = 0;// 行号
		while ((line = reader.readLine()) != null) {
			lineNum++;
			// 处理换行符
			if (lineNum >= 2) {// 第2行开始，先增加一个换行符号
				buf.append("\n");
			}
			// 处理行
			if (num > 0) {// 增加缩进
				buf.append(append).append(line);
			} else {// 减少缩进
				int i = 0;
				for (; i < num * -1; i++) {
					char c = line.charAt(i);
					if (c == '\t') {
						i++;
					} else {
						break;
					}
				}
				if (i > 0) {
					buf.append(line.substring(i));
				} else {
					buf.append(line);
				}
			}
		}
		return buf.toString();
	}

}
