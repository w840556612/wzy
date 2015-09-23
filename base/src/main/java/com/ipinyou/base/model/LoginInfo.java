package com.ipinyou.base.model;

import java.util.Date;
import java.util.Locale;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 登录相关信息，用户登录后，该信息存入缓存／shiro缓存／session中（不建议使用session）
 * @author lijt
 *
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LoginInfo extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2796047668846997499L;
	/**
	 * 
	 */
	protected Long userId;
	protected Long realUserId;
	
	protected String account;
	protected String realAccount;
	
	protected String name;
	protected String realName;
	
	protected Date loginTime = new Date();

	protected String loginId;
	
	protected Locale language;
	
	protected String domain;
	
	public LoginInfo() {
		super();
	}

}
