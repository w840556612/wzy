/**
 * 
 */
package com.ipinyou.optimus.console.report.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ipinyou.base.constant.DateFormat;
import com.ipinyou.optimus.console.model.Platform;

/**
 * @author lijt
 * 
 */
@Data
@MappedSuperclass
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class BaseEffect extends BaseEffectMeasure {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6353513548584836970L;

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
	 * 池id
	 */
	@Index(name = "poolId")
	protected long poolId;

	/**
	 * 广告主公司id
	 */
	@Index(name = "advertiserCompanyId")
	protected long advertiserCompanyId;

	/**
	 * 广告主id
	 */
	@Index(name = "advertiserId")
	protected long advertiserId;

	/**
	 * 订单id
	 */
	@Index(name = "orderId")
	protected long orderId;

	/**
	 * 计划id
	 */
	@Index(name = "campaignId")
	protected long campaignId;

	/**
	 * 策略id
	 */
	@Index(name = "strategyId")
	protected long strategyId;

	/**
	 * 创意id
	 */
	@Index(name = "creativeId")
	protected long creativeId;

	/**
	 * 创意单元id
	 */
	@Index(name = "creativeUnitId")
	protected long creativeUnitId;

	/**
	 * 平台
	 */
	@Index(name = "platform")
	@Column(length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	protected Platform platform;

	/**
	 * 影子平台
	 */
	@Index(name = "platformMapping")
	@Column(length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	protected Platform platformMapping;

	/**
	 * 渠道id
	 */
	@Index(name = "channelId")
	protected long channelId;

	/**
	 * 网站id
	 */
	@Index(name = "websiteId")
	protected long websiteId;

	/**
	 * 栏目id
	 */
	@Index(name = "labelId")
	protected long labelId;

	/**
	 * 广告位id
	 */
	@Index(name = "adUnitId")
	protected long adUnitId;

	/**
	 * 日期，年-月-日
	 */
	@Index(name = "day")
	@Column(nullable = false)
	protected Date day;

	/**
	 * 创意尺寸，格式：宽x高，例如: 300x250
	 */
	@Index(name = "adSize")
	@Column(nullable = false, length = 10)
	protected String adSize;

	/**
	 * 转换目标Id，默认为0
	 */
	@Index(name = "conversionTargetId")
	protected long conversionTargetId;
	
	/**
	 * 汇总级别
	 */
	protected int sumLevel = 0;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone = "GMT+8")
	@Column(updatable = false, nullable = false)
	protected Timestamp creation = new Timestamp(System.currentTimeMillis());

	/**
	 * 隐藏服务费和成本(对广告主)
	 */
	protected boolean hiddenSrvCost = false;

	/**
	 * 已删除
	 */
	private boolean removed = false;

}
