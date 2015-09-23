package com.ipinyou.optimus.console.report.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.springframework.format.annotation.DateTimeFormat;

import com.ipinyou.base.constant.DateFormat;

import lombok.Data;
import lombok.ToString;

/**
 * 我的访客-访客来源-主域名
 * 
 * @author guodong.zhang
 */
@Entity
@Table(name = "rpt_visitor_source_domain")
@Data
@ToString(callSuper = true)
public class VisitorSourceDomain implements com.ipinyou.base.entity.Entity {

	private static final long serialVersionUID = 1205037282825687610L;

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
	
	/**
	 * 主域名，例如sina.com.cn
	 */
	@NotNull
	@Column(length=100)
	private String domain;

	/**
	 * 访问次数
	 */
	@NotNull
	private long pv;

	/**
	 * 访问人数
	 */
	@NotNull
	private long uv;
	
	/**
	 * 截止day，companyId的domain的累计来源次数
	 */
	@NotNull
	private long allPv;

	/**
	 * 截止day，companyId的domain的累计来源人数
	 */
	@NotNull
	private long allUv;

}
