package com.ipinyou.base.freemarker.shiro;

import java.util.List;

public class LacksPermissionOneTag extends PermissionOneTag {
    protected boolean showTagBody(List<Object> perms) {
    	String[] a = new String[perms.size()];
        String[] p = (String[])perms.toArray(a);
        boolean result = false;
        for(int i=0;i<p.length;i++){
        	String[] s = {p[i]};
        	if(isPermittedOne(p)){
        		result = true;//包含
        	}
        }
        return !result;
    }
}