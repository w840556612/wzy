package com.ipinyou.base.freemarker.shiro;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import java.io.IOException;
import java.util.Map;

import org.apache.shiro.SecurityUtils;

/**
 * <p>Equivalent to {@link org.apache.shiro.web.tags.PermissionTag}</p>
 */
public abstract class PermissionTag extends SecureTag {
    String getName(@SuppressWarnings("rawtypes") Map params) {
        return getParam(params, "name");
    }
    
    @Override
    protected void verifyParameters(@SuppressWarnings("rawtypes") Map params) throws TemplateModelException {
        String permission = getName(params);

        if (permission == null || permission.length() == 0) {
            throw new TemplateModelException("The 'name' tag attribute must be set.");
        }
    }

    @Override
    public void render(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        String p = getName(params);

        boolean show = showTagBody(p);
        if (show) {
            renderBody(env, body);
        }
    }

    protected boolean isPermitted(String p) {
        return getSubject() != null && getSubject().isPermitted(p);
    }
    
    protected boolean isPermittedOne(String... permissions){
    	boolean[] permitteds = getSubject().isPermitted(permissions);
		if(permitteds == null || permitteds.length < 1){
			return false;
		}
		for(boolean permitted : permitteds){
			if(permitted){
				return true;
			}
		}
		return false;
    }

    protected abstract boolean showTagBody(String p);
}
