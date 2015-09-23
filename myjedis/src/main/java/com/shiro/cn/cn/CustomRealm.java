package com.shiro.cn.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomRealm extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		List<String> li=new  ArrayList<>();
		li.add("user:create");
		SimpleAuthorizationInfo sp=new  SimpleAuthorizationInfo();
		sp.addStringPermissions(li);
		return sp;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username=(String) arg0.getPrincipal();
		System.out.println(username);
	//√‹¬Î111111
	String bs="qwerty";
		return new SimpleAuthenticationInfo(username, "f3694f162729b7d0254c6e40260bf15c",ByteSource.Util.bytes(bs), "customrealm");
	}

}
