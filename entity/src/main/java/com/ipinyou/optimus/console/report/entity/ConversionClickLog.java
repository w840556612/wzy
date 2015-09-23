package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.ipinyou.optimus.console.report.entity.BaseLogStatus.ClickActionStatus;

/**
 * 转化日志
 * @author ganwenlong 2013-2-21 上午10:21:20
 */
@Entity
@Table(name = "conversion_click_log")
@org.hibernate.annotations.Table( indexes = {@Index(name="orderNoKey", columnNames={"order_no", "advertiser_company_id"} ) }, appliesTo = "conversion_click_log" )
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ConversionClickLog extends BaseConversionLogDetail implements ClickActionStatus {

	private static final long serialVersionUID = -4388366039186198355L;

	@Embedded
	private ClickInfo clickInfo;
	@Lob
	private String deviceIds;
	
	@Index(name = "clientConfirmPy")
	private boolean clientConfirmPy;
	private boolean matchDsp;
	@Index(name = "matchClick")
	private boolean matchClick;

}
