/**
 * 
 */
package com.ipinyou.base.constant;


/**
 * 对于前台响应的一些错误码信息常量，为了前台统一处理错误框架服务
 * @author zhyhang
 *
 */
public interface ResponseExtStatus {
	
	String KEY_RESPONSE_EXT_STATUS="extStatusPy";

	/**
	 * 频繁刷新错误
	 */
	int STATUS_ABUSE_REFRESH = 481;

}
