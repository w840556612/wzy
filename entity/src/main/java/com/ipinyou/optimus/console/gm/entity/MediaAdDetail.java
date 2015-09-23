package com.ipinyou.optimus.console.gm.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Strategy;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "gm_media_ad_detail")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class MediaAdDetail extends TimedEntity {

	private static final long serialVersionUID = -4590550968578629454L;

	private Long batchId;
	private String platform;
	private Long adUnitId;
	private Date schedule;
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "strategy_id", nullable = false, insertable = false, updatable = false)
	private Strategy strategy;
	
	private Long strategyId;
	private String strategyName;
	private Long creativeId;
	private String creativeTheme;
	private int creativeIndex;
	private Long relId;
	private String path;
	private String path2;
	private String content;
	private String clickUrl;
	private Date beginDate;
	private Date endDate;
	private String weeklySchedule;
	private int flowRate;
	private boolean active = true;
	private boolean removed = false;

}
