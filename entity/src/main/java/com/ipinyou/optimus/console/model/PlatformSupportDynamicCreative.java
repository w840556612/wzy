 /**
 * 
 */
package com.ipinyou.optimus.console.model;



/**
 * @author lijt
 * 
 */
public enum PlatformSupportDynamicCreative {
	
	Optimus("品友人群网络投放", true, true, false, false),  
	Adx("谷歌", false, true, false, false), 
	Tanx("淘宝", false, false, false, false),  
	Sina("新浪", true, true, false, true),
	Youku("优酷", false, true, false, false),
	Amx("谷歌Adx移动", false, true, false, false),
	Baidu("百度", true, true, false, false), 	
	Miaozhen("秒针", false, true, false, false);
	
	
	private String text;

	/**
	 * 广告主是否需要审核
	 */
	private boolean advAudit;
	
	/**
	 * 创意是否需要审核
	 */
	private boolean creativeAudit;
	
	/**
	 * 投放是否使用平台返回的id
	 */
	private boolean usePlatformId;
	
	/**
	 * 投放是否使用平台返回的创意url
	 */
	private boolean usePlatformUrl;
		
	
	private PlatformSupportDynamicCreative(String text, boolean advAudit, boolean creativeAudit, 
			boolean usePlatformId, boolean usePlatformUrl) {	
		this.text = text;
		this.advAudit = advAudit;
		this.creativeAudit = creativeAudit;
		this.usePlatformId = usePlatformId;
		this.usePlatformUrl = usePlatformUrl;
	}

	public String getText() {
		return text;
	}

	
	public boolean isAdvAudit() {
		return advAudit;
	}

	public boolean isCreativeAudit() {
		return creativeAudit;
	}

	public boolean isUsePlatformId() {
		return usePlatformId;
	}

	public boolean isUsePlatformUrl() {
		return usePlatformUrl;
	}	
	
}
