/**
 * 
 */
package com.ipinyou.base.entity;

/**
 * 审计日志实体接口，实现了此接口表示可自动记录操作日志
 * 
 * @author lijt
 * 
 */
public interface Auditable<T extends Entity> extends Entity {

	/** 获取实体名称，一般是表名
	 * @return
	 */
	String getEntityName();

	/** 获取主键值，一般是记录中id字段
	 * @return
	 */
	String getPrimaryKey();

	/** 获取名称，一般是记录中name字段
	 * @return
	 */
	String getName();

	/** 获取实体原始值，一般从数据库查出后即保存到实体的orig属性中
	 * @return
	 */
	T getOrig();
	
	/** 设置实体原始值，一般从数据库查出后即保存到实体的orig属性中
	 * @param t
	 */
	void setOrig(T t);
}
