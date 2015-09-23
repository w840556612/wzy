/**
 * 
 */
package com.ipinyou.base.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Data;

import com.ipinyou.base.entity.listener.NoIdTimedEntityListener;

/**
 * @author lijt
 * 
 */
@MappedSuperclass
@Data
@EntityListeners({ NoIdTimedEntityListener.class })
public abstract class NoIdTimedEntity implements Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3333546025232120499L;

	/**
	 * 乐观锁
	 */
	@Version
	protected long version = -1;

	/**
	 * 创建时间
	 */
	@Column(updatable = false, nullable = false)
	protected Timestamp creation;

	/**
	 * 最后修改时间
	 */

	@Column(nullable = false)
	protected Timestamp lastModified;
}
