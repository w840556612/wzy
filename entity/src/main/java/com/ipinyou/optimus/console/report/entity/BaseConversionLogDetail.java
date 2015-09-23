package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

/**
 * 转化日志详细信息基类
 * 
 * @author guodong.zhang
 */
@MappedSuperclass
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class BaseConversionLogDetail extends BaseLogDetail {

	private static final long serialVersionUID = -2806227279030257777L;

	/**
	 * 转化目标ID
	 */
	@Index(name = "targetId")
	private Long targetId;

	/**
	 * 订单金额
	 */
	@Column(length = 100)
	private String money;

	/**
	 * 订单号
	 */
	@Column(length = 100)
	private String orderNo;

	/**
	 * 订单商品列表
	 */
	@Lob
	private String productList;

	/**
	 * 订单商品详细信息
	 */
	@Lob
	private String productDetail;

}
