package com.ipinyou.optimus.console.ad.vo;
import java.sql.Timestamp;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ipinyou.base.constant.DateFormat;
import com.ipinyou.optimus.console.model.ApprovalStatus;
import com.ipinyou.optimus.console.model.CreativeShowStatus;
import com.ipinyou.optimus.console.model.Platform;
@Data
public class CreativePlatformAuditStatusVo {
	
	private String platform;	
	
	private String platformName;	
	
	private CreativeShowStatus status;
	
	private String reason;	
	
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone="GMT+8")
	private Timestamp auditTime;
	
}
