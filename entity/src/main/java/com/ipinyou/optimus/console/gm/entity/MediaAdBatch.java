package com.ipinyou.optimus.console.gm.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ipinyou.base.entity.TimedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "gm_media_ad_batch")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class MediaAdBatch extends TimedEntity {

	private static final long serialVersionUID = 7702447137632790860L;

	private String platform;
	private Long adUnitId;
	private Date schedule;
	private String relIds;
	private boolean run = false;
	private Timestamp prevRunTime;
	private int runCount = 0;
	private boolean active = true;
	private boolean removed = false;
	
}
