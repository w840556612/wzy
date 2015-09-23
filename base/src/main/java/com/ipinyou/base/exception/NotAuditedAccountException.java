/**
 * 
 */
package com.ipinyou.base.exception;

import org.apache.shiro.authc.AccountException;

/**
 * 未审核用户异常
 * 
 * @author lijt
 * 
 */
public class NotAuditedAccountException extends AccountException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9197532276969391216L;

	public NotAuditedAccountException() {
		super();
	}

	public NotAuditedAccountException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotAuditedAccountException(String message) {
		super(message);
	}

	public NotAuditedAccountException(Throwable cause) {
		super(cause);
	}

}
