/**
 * 
 */
package com.ipinyou.base.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 带有长整型主键和乐观锁的抽象实体类
 * @author lijt
 * 
 */
@MappedSuperclass
public abstract class BaseEntity implements Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1884829048899575754L;

	@Transient
	protected final transient Logger logger = LoggerFactory
			.getLogger(getClass());

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	/**
	 * 乐观锁
	 */
	@Version
	protected long version = -1;
	
	@Transient
	public String getPrimaryKey() {
		return id==null?null:String.valueOf(id);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}
