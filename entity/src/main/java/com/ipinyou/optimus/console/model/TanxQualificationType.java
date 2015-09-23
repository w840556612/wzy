package com.ipinyou.optimus.console.model;

public enum TanxQualificationType {
	Tanx_1(1,"营业执照"), 
	Tanx_2(2,"ICP"), 
	Tanx_3(3,"增值电信业务经营许可证"), 
	Tanx_4(4,"医疗机构执业许可证"),
	Tanx_5(5,"省级卫生行政部门、中医药管理部门核发的相关审查证明文件"),
	Tanx_6(6, "省、自治区、直辖市药品监督管理部门批准的相关审查表及批准文号资质"),
	Tanx_7(7,"互联网药品信息服务资格证书"), 
	Tanx_8(8,"互联网药品交易服务机构资格证书"), 
	Tanx_9(9,"化妆品生产企业的《生产许可证》"), 
	Tanx_10(10,"化妆品生产企业的《卫生许可证》"),
	Tanx_11(11,"特殊用途化妆品卫生许可批件"),
	Tanx_12(12, "进口报关单"),
	Tanx_13(13,"进口化妆品备案凭证"), 
	Tanx_14(14,"美容机构从业资格证"), 
	Tanx_15(15,"《网络文化经营许可证》"), 
	Tanx_16(16,"明星代言合同"),
	Tanx_17(17,"游戏版号文件"),
	Tanx_18(18, "《 经营股票承销业务资格证书》"),
	Tanx_21(21,"《期货业务经营许可证》"), 
	Tanx_22(22,"银监会从事货币经纪的金融许可证"), 
	Tanx_23(23,"小额贷款公司经营许可证"), 
	Tanx_24(24,"融资性担保公司经营许可证"),
	Tanx_25(25,"推广网站域名"),
	Tanx_26(26, "店铺营业执照"),
	Tanx_27(27,"省、自治区、直辖市药品监督管理部门批准的相关审查表及批准文号"), 
	Tanx_28(28,"其他金融资质"), 
	Tanx_29(29,"推广落地页地址"), 
	Tanx_30(30,"P2P行业相关证明文件"),
	Tanx_31(31,"基金管理/代销业务资质");

	private int code;
	private String text;

	private TanxQualificationType(int code, String text) {
		this.code = code;
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	public int getCode(){
		return code;
	}
}