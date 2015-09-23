package com.ipinyou.optimus.console.ad.vo;
import java.sql.Timestamp;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ipinyou.base.constant.DateFormat;
import com.ipinyou.optimus.console.model.ApprovalStatus;
import com.ipinyou.optimus.console.model.Platform;
@Data
public class PlatformAuditStatusVo {
	
	private Platform platform;	
	
	private ApprovalStatus status;
	
	private String reason;	
	
	@JsonFormat(pattern = DateFormat.TIMESTAMP, timezone="GMT+8")
	private Timestamp auditTime;
	
	public PlatformAuditStatusVo(){
		
	}
	public PlatformAuditStatusVo(Platform platform, 
			ApprovalStatus status, String reason){
		this.platform = platform;
		this.status = status;
		this.reason = reason;
	}
	
	public PlatformAuditStatusVo(Platform platform, ApprovalStatus status, 
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
