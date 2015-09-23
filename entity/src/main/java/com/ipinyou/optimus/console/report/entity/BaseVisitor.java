/**
 * 
 */
package com.ipinyou.optimus.console.report.entity;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import lombok.Data;

import org.hibernate.annotations.Index;
import org.springframework.format.annotation.DateTimeFormat;

import com.ipinyou.base.constant.DateFormat;

/**
 * @author lijt
 *
 */
@Data
@MappedSuperclass
public abstract class BaseVisitor implements com.ipinyou.base.entity.Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5722385851667631923L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/**
	 * 广告主公司ID
	 */
	@NotNull
	@Index(name="companyId")
	private long companyId;

	/**
	 * 日期
	 */
	@NotNull
	@DateTimeFormat(pattern = DateFormat.DATE)
	@Index(name="logDate")
	private Date logDate;
	
}
