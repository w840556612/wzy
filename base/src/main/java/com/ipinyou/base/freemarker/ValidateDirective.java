/**
 * 
 */
package com.ipinyou.base.freemarker;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

import com.ipinyou.jsr303js.JSR303Utils;

import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 生成前端js验证的宏
 * 例如： <@validate object=form formId="registerForm" min=false errorPlacement='errorPlacement'/>
 * @author lijt
 *
 */
public class ValidateDirective implements TemplateDirectiveModel{
	private static final String PARAM_OBJ = "object";
	
	private static final String PARAM_FORM_ID = "formId";
	
	private static final String PARAM_MIN = "min";
	
	private static final String ERROR_PLACEMENT = "errorPlacement";
	private static final String DEFAULT_ERROR_PLACEMENT = "defaultErrorPlacement";
	
	private MessageSourceAccessor messages;// 获取国际化文件内�? 
	
	@Autowired
	public void setMessages(MessageSource messageSource) {  
		messages = new MessageSourceAccessor(messageSource); 
	}
	
	/* (non-Javadoc)
	 * @see freemarker.template.TemplateDirectiveModel#execute(freemarker.core.Environment, java.util.Map, freemarker.template.TemplateModel[], freemarker.template.TemplateDirectiveBody)
	 */
	@Override
	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		checkVal(params, PARAM_OBJ);
		checkVal(params, PARAM_FORM_ID);
		Object obj = ((StringModel) params.get(PARAM_OBJ)).getWrappedObject();
		String formId = ((SimpleScalar) params.get(PARAM_FORM_ID)).getAsString();
		boolean min = true;
		if ( params.containsKey(PARAM_MIN) ) {
			min = ((TemplateBooleanModel) params.get(PARAM_MIN)).getAsBoolean();
		}
		String errorPlacement = DEFAULT_ERROR_PLACEMENT;
		if( params.containsKey(ERROR_PLACEMENT) ){
			errorPlacement = ((SimpleScalar) params.get(ERROR_PLACEMENT)).getAsString();
		}
		
		
		String jsCode = JSR303Utils.translate2JQueryValidates(obj.getClass(), formId, messages, null, !min, errorPlacement);
		env.getOut().write("<script type=\"text/javascript\">");
		env.getOut().write(jsCode);
		env.getOut().write("</script>");
	}
	
	private void checkVal(@SuppressWarnings("rawtypes") Map params, String paramName) throws TemplateModelException{
		if (!params.containsKey(paramName)) {
			throw new TemplateModelException("Argument " + paramName
					+ " is required!");
		}
		Object valObject = params.get(paramName);
		if( valObject == null ){
			throw new TemplateModelException("Argument " + paramName
					+ " is required!");
		}
	}
			
//	
//	public static void main(String []args){
//		System.out.println( JSR303Utils.translate2JQueryValidates(RegisterForm.class, "registerForm", null, null, true, "defaultErrorPlacement") );
//	}

}
