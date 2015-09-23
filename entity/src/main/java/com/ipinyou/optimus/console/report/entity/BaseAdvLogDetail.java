package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 转化、广告主 日志明细 的公共字段。
 * 
 * @author ganwenlong 2013-2-22 下午2:49:11
 */
@MappedSuperclass
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class BaseAdvLogDetail extends BaseLogDetail {

	private static final long serialVersionUID = -6463408522272352352L;

	@Embedded
	private ClickInfo clickInfo;

}
