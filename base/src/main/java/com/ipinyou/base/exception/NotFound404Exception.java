package com.ipinyou.base.exception;

/**
 * 未找到相应的数据时抛出此异常
 * @author xiaobo.wang
 *
 */
public class NotFound404Exception extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5512830770736456555L;

	public NotFound404Exception() {
		super();
	}

	public NotFound404Exception(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFound404Exception(String message) {
		super(message);
	}

	public NotFound404Exception(Throwable cause) {
		super(cause);
	}
	
	

}
