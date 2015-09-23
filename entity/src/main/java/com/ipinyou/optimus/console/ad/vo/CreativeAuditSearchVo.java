package com.ipinyou.optimus.console.ad.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class CreativeAuditSearchVo {
	private String name;
	private String auditId;
	private boolean search;
	private String platform;
	private String advertiserName;
	private String status;
}
