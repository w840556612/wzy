/**
 * 
 */
package com.ipinyou.base.exception;

/**
 * 事务回滚异常，若抛出此异常，事务将回滚
 * @author lijt
 * 
 */
public class TransactionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5963898898163325736L;

	/**
	 * 
	 */

	public TransactionException() {
		super();
	}

	public TransactionException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransactionException(String message) {
		super(message);
	}

	public TransactionException(Throwable cause) {
		super(cause);
	}

}
