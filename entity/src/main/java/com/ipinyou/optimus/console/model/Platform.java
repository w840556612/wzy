 /**
 * 
 */
package com.ipinyou.optimus.console.model;



/**
 * @author lijt
 * 
 */
public enum Platform {
	
	Na("optimus.console.model.Platform.1000"/*未知*/, false, false, false, false, false), 
	YkSem("optimus.console.model.Platform.1024"/*优酷SEM*/, false, true, false, false, true),
	Stats("optimus.console.model.Platform.1001"/*第三方统计*/, false, false, false, false, false),  
	Optimus("optimus.console.model.Platform.1002"/*品友人群网络投放*/, true, true, false, false, false),  
	Adx("optimus.console.model.Platform.1003"/*谷歌*/, false, true, false, false, false), 
	Tanx("optimus.console.model.Platform.1004"/*淘宝*/, false, false, false, false, false),  
	Tencent("optimus.console.model.Platform.1005"/*腾讯*/, true, true, false, false, false),
	Sina("optimus.console.model.Platform.1006"/*新浪*/, true, true, false, true, false),
	Amx("optimus.console.model.Platform.1008"/*谷歌Adx移动*/, false, true, false, false, false),
	Baidu("optimus.console.model.Platform.1009"/*百度*/, true, true, false, false, false), 	
	Miaozhen("optimus.console.model.Platform.1010"/*秒针*/, false, true, false, false, false),
	BaoFeng("optimus.console.model.Platform.1011"/*暴风*/,false, false, false, true, false),
	Allyes("optimus.console.model.Platform.1012"/*好耶*/,false, true, true, false, false),
	Smaato("smaato",false,false, false, false, false),
	Iqiyi("optimus.console.model.Platform.1013"/*爱奇艺*/,false,true, true, false, false),
	Inmobi("inmobi", false, false, false, false, false),
    Mogo("optimus.console.model.Platform.1014"/*芒果*/, false, false, false, false, false), 
    Shanda("optimus.console.model.Platform.1015"/*酷六*/, false, false, false, false, false),
    Xunlei("optimus.console.model.Platform.1016"/*迅雷*/,false, false, false, false, false),
    Sohu("optimus.console.model.Platform.1017"/*搜狐*/,true, true, false, false, false),
    Unicom("optimus.console.model.Platform.1018"/*联通*/, false, true, false, false, false),
    Funadx("optimus.console.model.Platform.1019"/*风行*/,false, true, false, false, false),
	AdView("adView",false,true,false,false, false),
	TouTiao("optimus.console.model.Platform.1020"/*今日头条*/,false,false,false,false, false),
	Adin("optimus.console.model.Platform.1021"/*互众*/, true, true, false, false, false),
	Letv("optimus.console.model.Platform.1022"/*乐视*/, false, true, false, false, false),
	GDT("optimus.console.model.Platform.1023"/*广点通*/, true, true, false, false, false),
	Youku("optimus.console.model.Platform.1007"/*优酷*/, false, true, false, false, false); 
	
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
	
	/** 是否为中心平台特有 */
	private boolean dasuanpan;
		
	
	private Platform(String text, boolean advAudit, boolean creativeAudit, 
			boolean usePlatformId, boolean usePlatformUrl, boolean dasuanpan) {	
		this.text = text;
		this.advAudit = advAudit;
		this.creativeAudit = creativeAudit;
		this.usePlatformId = usePlatformId;
		this.usePlatformUrl = usePlatformUrl;
		this.dasuanpan = dasuanpan;
	}

	public String getText() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
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
	
	public boolean isDasuanpan() {
		return dasuanpan;
	}
	
}
