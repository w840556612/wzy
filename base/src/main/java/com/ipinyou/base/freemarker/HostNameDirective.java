/**
 * 
 */
package com.ipinyou.base.freemarker;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 输出当前服务器主机名，在集群环境中便于具体处理请求的后端服务器
 * 使用示例：
 * &lt;@ostName /&gt;
 * @author lijt
 *
 */
public class HostNameDirective implements TemplateDirectiveModel {

	/* (non-Javadoc)
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.Environment, java.util.Map, freemarker.template.TemplateModel[], freemarker.template.TemplateDirectiveBody)
	 */
	@Override
	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		env.getOut().write(InetAddress.getLocalHost().getHostName());
	}

}
