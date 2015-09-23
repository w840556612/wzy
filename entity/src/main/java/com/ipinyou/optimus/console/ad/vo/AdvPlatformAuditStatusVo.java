package com.ipinyou.optimus.console.ad.vo;
import java.sql.Timestamp;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ipinyou.base.constant.DateFormat;
import com.ipinyou.optimus.console.model.AdvertiserShowStatus;
import com.ipinyou.optimus.console.model.ApprovalStatus;
import com.ipinyou.optimus.console.model.Platform;
@Data
public class AdvPlatformAuditStatusVo {
	
	private Platform platform;	
	
	private AdvertiserShowStatus status;
	
	private String reason;	
	
	private String advertiserAuditName;
	
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone="GMT+8")
	private Timestamp auditTime;
	
	public AdvPlatformAuditStatusVo(){
		
	}
	public AdvPlatformAuditStatusVo(Platform platform, 
			AdvertiserShowStatus status, String reason){
		this.platform = platform;
		this.status = status;
		this.reason = reason;
	}
	
	public AdvPlatformAuditStatusVo(Platform platform, AdvertiserShowStatus status, 
			String reason, Timestamp auditTime){
		this.platform = platform;
		this.status = status;
		this.reason = reason;
		this.auditTime = auditTime;
	}
	
	public boolean isAdvAudit() {
		return platform.isAdvAudit();
	}

	public boolean isCreativeAudit() {
		return platform.isCreativeAudit();
	} 
	
	public String getPtfName(){
		return platform.getText();
	}
	
}
