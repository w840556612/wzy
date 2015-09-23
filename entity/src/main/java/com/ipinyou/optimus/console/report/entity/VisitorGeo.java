package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

/**
 * 我的访客-地域分布
 * @author guodong.zhang
 */
@Entity
@Table(name = "rpt_visitor_geo")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class VisitorGeo extends BaseVisitor {
	
	private static final long serialVersionUID = 252606307597622955L;

	/**
	 * 地域ID，引用geo
	 */
	@NotNull
	@Index(name="geoId")
	private short geoId;
	
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
	
}
