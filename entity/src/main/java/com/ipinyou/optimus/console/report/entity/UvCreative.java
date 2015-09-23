/**
 * 
 */
package com.ipinyou.optimus.console.report.entity;

import org.hibernate.annotations.Index;

/**
 * @author lijt
 * 
 */
public class UvCreative extends BaseUv {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8974276433064617219L;
	/**
	 * 创意id
	 */
	@Index(name = "creativeId")
	protected long creativeId;

}
