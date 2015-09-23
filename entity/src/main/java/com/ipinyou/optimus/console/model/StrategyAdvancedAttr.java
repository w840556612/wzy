package com.ipinyou.optimus.console.model;

public enum StrategyAdvancedAttr {
	
	audiences("DAAT人群定向"),
	audienceWeight("人群关注度"),
//	baiduAudiences("百度人群设定"),
//	miaozhenAudiences("秒针人群设定"),
	audiencesExclude("人群排除定向"),
	mobileAudiences("移动人群定向"),
	visitorRetarget("访客找回"),
	visitorExclude("访客排除"),
	pyidCategories("人群分类定向"),
	pyids("Cookie定向"),
	areas("地域定向"),
	areasExclude("地域排除"),
	dayParting("时段定向"),
	exchangesAdLocations("平台、位置定向"),
	OSTypeVersion("操作系统定向"),
	browsers("浏览器定向"),
	blackUrl("媒体黑名单"),
	whiteUrl("媒体白名单"),
	//highQualityMedia("优质媒体定向"),
	antiCheating("防作弊"),
	dayPacing("均匀投放"),
	mobileSets("移动设备定向"),
	mobileDeciveProperty("价格尺寸定向"),
	setIdCategory("移动设备ID定向"),
	appCategories("APP类型定向"),
	mobileNetworks("移动网络定向"),
	blackAdUnit("广告位定向黑名单"),
	whiteAdUnit("广告位定向白名单"),
	clickCookieType("点击找回"),
	blackApp("APP黑名单"),
	whiteApp("APP白名单"),
	idfas("移动用户定向"),
	preferDeal("PreferDeal"),
//	classifyIds("媒体分类定向"),
//	excludeClassifyIds("媒体分类排除")
	fingerprint("指纹识别")
	;
	
	
	private StrategyAdvancedAttr(String text){
		this.text = text;
	}
	private String text;
	
	public String getText() {
		return text;
	}
}
