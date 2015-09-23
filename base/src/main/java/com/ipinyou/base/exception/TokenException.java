/**
 * 
 */
package com.ipinyou.base.exception;

/**
 * token验证失败时抛出此异常，主要用来防止重复提交
 * @author lijt
 * 
 */
public class TokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3480602529875839883L;

	public TokenException() {
		super();
	}

	public TokenException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public TokenException(String message) {
		super(message);
	}

	public TokenException(Throwable cause) {
		super(cause);
	}

}
