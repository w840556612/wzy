/**
 * 
 */
package com.ipinyou.base.exception;

/**
 * @author lijt
 * 
 */
public class IllegalUrlException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 325736873687656264L;

	public IllegalUrlException() {
		super();
	}

	public IllegalUrlException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IllegalUrlException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalUrlException(String message) {
		super(message);
	}

	public IllegalUrlException(Throwable cause) {
		super(cause);
	}

}
