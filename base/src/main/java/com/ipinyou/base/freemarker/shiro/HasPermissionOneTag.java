package com.ipinyou.base.freemarker.shiro;

import java.util.List;

public class HasPermissionOneTag extends PermissionOneTag {
	
	@Override
    protected boolean showTagBody(List<Object> perms) {
    	String[] a = new String[perms.size()];
        String[] p = (String[])perms.toArray(a);
        return isPermittedOne(p);
    }
}