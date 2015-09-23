package com.ipinyou.optimus.console.ad.vo;

import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.model.BaseVo;
import com.ipinyou.optimus.console.ad.entity.Creative.CreativeType;
import com.ipinyou.optimus.console.ad.entity.Strategy.AdvertisingMode;
import com.ipinyou.optimus.console.ad.entity.Strategy.PlatformList;
import com.ipinyou.optimus.console.ad.entity.StrategyCreativeRel.ApprovalInfoMap;
import com.ipinyou.optimus.console.ad.entity.StrategyCreativeRel.OrderedUrlList;
import com.ipinyou.optimus.console.model.ApprovalStatus;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CreativeAuditVo extends BaseVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 323562702616538155L;
	
	public CreativeAuditVo(BigInteger relId,
			String clickUrl,
			String targetUrl,
			String trackingUrls,
			Timestamp creation,
			String approvalStatus,
			String approvedInfo,
			String disapprovedInfo,
			Timestamp lastApproved,
			String creativeTheme,
			int creativeWidth,
			int creativeHeight,
			String creativePath,
			String advertiserName,
			String strategyExchanges,
			String strategyAdvertisingMode,
			boolean advertiserDasuanpan){
		this.relId = relId;
		this.clickUrl=clickUrl;
		this.targetUrl = targetUrl;
		OrderedUrlList tuls = new OrderedUrlList();
		tuls.setStrValue(trackingUrls);
		this.trackingUrls = tuls;
		this.creativeCreation = creation;
		this.approvalStatus = ApprovalStatus.valueOf(approvalStatus);
		
		this.approvedInfo = new ApprovalInfoMap(approvedInfo);
		
		this.disapprovedInfo=new ApprovalInfoMap(disapprovedInfo);
		this.lastApproved= lastApproved;
		this.creativeTheme = creativeTheme;
		this.creativeWidth= creativeWidth;
		this.creativeHeight=creativeHeight;
		this.creativePath=creativePath;
		this.advertiserName=advertiserName;
		PlatformList pl = new PlatformList();
		pl.setStrValue(strategyExchanges);
		this.strategyExchanges = pl;
		this.strategyAdvertisingMode= AdvertisingMode.valueOf(strategyAdvertisingMode);
		this.advertiserDasuanpan=advertiserDasuanpan;
	}
	public CreativeAuditVo(){
	}
	private BigInteger relId;
	
	private String creativeTheme;
	
	private String advertiserName;
	
	private int creativeWidth;
	
	private int creativeHeight;
	
	private String creativePath;
	
	private String clickUrl;
	
	private String targetUrl;
	
	@Embedded
	@Lob
	private OrderedUrlList trackingUrls;
	
	private Timestamp creativeCreation;
	
	@Enumerated(EnumType.STRING)
	private ApprovalStatus approvalStatus;
	
	private Timestamp lastApproved;
	
	@Embedded
	private ApprovalInfoMap approvedInfo;
	
	@Embedded
	private ApprovalInfoMap disapprovedInfo;
	
	@Embedded
	private PlatformList strategyExchanges;
	
	@Enumerated(EnumType.STRING)
	private AdvertisingMode strategyAdvertisingMode;
	
	private boolean advertiserDasuanpan;
	
	@Enumerated(EnumType.STRING)
	private CreativeType creativeType;
	
	public void setCreativeType(String type){
		this.creativeType = (type==null?null:CreativeType.valueOf(type));
	}
	
	private BigInteger creativeId;
	
	private String showAudit;
	
	private String strategyName;
	
	private String campaignName;
	
	private String orderName;
	
	private String poolName;
	
	private String disapprovalDesc;
	
	private boolean poolPyAudit;
	
	private String thirdPlatform;
}
