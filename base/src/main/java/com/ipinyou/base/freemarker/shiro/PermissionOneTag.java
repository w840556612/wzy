package com.ipinyou.base.freemarker.shiro;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

public abstract class PermissionOneTag extends SecureTag {
    List<Object> getNames(@SuppressWarnings("rawtypes") Map params) throws TemplateModelException {
        return  getParamList(params,"name");
    }
    
    @Override
    protected void verifyParameters(@SuppressWarnings("rawtypes") Map params) throws TemplateModelException {
        List<Object> permission = getNames(params);
        if (permission == null || permission.size() == 0) {
            throw new TemplateModelException("The 'name' tag attribute must be set.");
        }
    }

    @Override
    public void render(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        List<Object> perms = getNames(params);
       
        boolean show = showTagBody(perms);
        if (show) {
            renderBody(env, body);
        }
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

    protected abstract boolean showTagBody(List<Object> p);
}
