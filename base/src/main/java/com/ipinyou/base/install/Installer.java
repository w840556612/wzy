/**
 * 
 */
package com.ipinyou.base.install;


/**
 * 安装器接口
 * @author lijt
 *
 */
public interface Installer{
	/** 执行顺序，数值小的先执行，大的后执行
	 * @return
	 */
	public int getOrder();
	/**
	 * 执行安装逻辑
	 */
	public void run();
}
