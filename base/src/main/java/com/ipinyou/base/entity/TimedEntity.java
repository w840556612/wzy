/**
 * 
 */
package com.ipinyou.base.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ipinyou.base.constant.DateFormat;
import com.ipinyou.base.entity.listener.TimedEntityListener;

/**
 * 在BaseEntity基础上增加了创建时间和修改时间
 * 
 * @author lijt
 * 
 */
@MappedSuperclass
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners({ TimedEntityListener.class })
public abstract class TimedEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5968742044806210654L;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone="GMT+8")
	@Column(updatable = false, nullable = false)
	protected Timestamp creation;

	/**
	 * 最后修改时间
	 */
	@Index(name="lastModified")
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone="GMT+8")
	@Column(nullable = false)
	protected Timestamp lastModified;

}
