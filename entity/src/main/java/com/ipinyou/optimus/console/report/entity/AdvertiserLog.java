package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.ipinyou.optimus.console.report.entity.BaseLogStatus.ClickActionStatus;

/**
 * 广告主日志
 * @author ganwenlong
 * 2013-2-21 上午10:21:20
 */
@Entity
@Table(name = "detail_advertiser_log")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdvertiserLog extends BaseAdvLogDetail implements ClickActionStatus {

	
	private static final long serialVersionUID = -2472497095322892547L;

	private Long labelId;
	
	@Lob
	private String deviceIds;

	
	@Index(name = "clientConfirmPy")
	private boolean clientConfirmPy;
	private boolean matchDsp;
	@Index(name = "matchClick")
	private boolean matchClick;
	
}
