/**
 * 
 */
package com.ipinyou.optimus.console.sys.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.BaseEntity;

/**
 * 字段变更日志
 * 
 * @author lijt
 * 
 */
@Entity
@Table(name = "sys_field_log")
@Data
@ToString(callSuper = true, exclude = { "log" })
@JsonIgnoreProperties(value={"log"})
@EqualsAndHashCode(callSuper = true, exclude = { "log" })
public class FieldLog extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2726908181256002494L;

	/**
	 * 对应的操作日志对象
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "LOG_ID", nullable = false)
	private OperationLog log;

	/**
	 * 对应的操作日志id
	 */
	@Column(insertable = false, updatable = false, nullable = false)
	private Long logId;
	/**
	 * 被修改的字段(属性)名称
	 */
	@Column(length = 64, nullable = false)
	private String name;
	
	/** 原值 */
	@Lob
	@Column(nullable = true)
	private String oldVal;


	/** 新值 */
	@Lob
	@Column(nullable = true)
	private String newVal;

}
