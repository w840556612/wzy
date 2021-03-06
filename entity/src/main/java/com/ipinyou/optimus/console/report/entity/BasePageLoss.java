package com.ipinyou.optimus.console.report.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

import com.ipinyou.base.constant.DateFormat;

/**
 * 广告主网站访客访问轨迹报表
 * @author yousheng.zhao
 */
@Data
@MappedSuperclass
public abstract class BasePageLoss implements com.ipinyou.base.entity.Entity {
	
	private static final long serialVersionUID = 1048402303705729976L;

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
	@Index(name="day")
	private Date day;
	
	@NotNull
	@Column(length = 2083)
	private String url;
	
	/**
	 * 访问次数
	 */
	@NotNull
	private long visitPv;
	
	/**
	 * 流失次数
	 */
	@NotNull
	private long lossPv;
	
	/**
	 * 访问人数
	 */
	@NotNull
	private long visitUv;
	
	/**
	 * 流失人数
	 */
	@NotNull
	private long lossUv;
	
}
