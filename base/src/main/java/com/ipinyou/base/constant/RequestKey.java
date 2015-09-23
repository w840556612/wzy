package com.ipinyou.base.constant;

/**
 * 存储在request中对象的键名称(参数及attribute名称)
 * @author zhyhang
 *
 */
public enum  RequestKey {
	/**
	 * 客户访问页面中隐藏的url
	 */
	PARA_CLIENT_BASE_URL("client_base_url"),
	/**
	 * 当前表单使用的url（http or https）
	 */
	PARA_CLIENT_ACTION_BASE_URL("client_action_base_url"),
	/**
	 *前台访问后台时，相对于当前URI的URI，用于返回上一级 
	 */
	PARA_CLIENT_REFER_URI("client_refer_uri");
	
	private String key;
	
	private RequestKey(String key){
		this.key=key;
	}
	
	public String key(){
		return this.key;
	}
}
