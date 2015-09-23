/**
 * 
 */
package com.ipinyou.optimus.console.report.entity;

import org.hibernate.annotations.Index;

/**
 * @author lijt
 * 
 */
public class UvCreativeUnit extends BaseUv {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2351250310211294560L;

	/**
	 * 创意id
	 */
	@Index(name = "creativeId")
	protected long creativeId;

	/**
	 * 创意单元id
	 */
	@Index(name = "creativeUnitId")
	protected long creativeUnitId;
}
