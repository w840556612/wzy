/**
 * 
 */
package com.ipinyou.base.exception;

/**
 * 对于一些访问已经超过设定的期限（例如密码找回，发送链接到邮箱后很长时间未点击）
 * @author zhyhang
 *
 */
public class OutOfDateException extends RuntimeException {

	private static final long serialVersionUID = -3974074278252759497L;

	public OutOfDateException() {
		super();
	}

	public OutOfDateException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OutOfDateException(String message, Throwable cause) {
		super(message, cause);
	}

	public OutOfDateException(String message) {
		super(message);
	}

	public OutOfDateException(Throwable cause) {
		super(cause);
	}

}
