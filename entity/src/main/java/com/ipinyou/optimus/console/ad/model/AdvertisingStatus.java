package com.ipinyou.optimus.console.ad.model;

/**
 * 投放状态枚举
 * @author xiaobo.wang
 *
 */
public enum AdvertisingStatus {
	
	Advertising("optimus.console.ad.model.AdvertisingStatus.1001"/*投放中*/), Stop("optimus.console.ad.model.AdvertisingStatus.1002"/*未投放*/), Approving("optimus.console.ad.model.AdvertisingStatus.1003"/*广告主资质待审核*/), ApproveDenial(
			"optimus.console.ad.model.AdvertisingStatus.1004"/*广告主资质审核拒绝*/), Close("optimus.console.ad.model.AdvertisingStatus.1005"/*关闭*/),Finish("optimus.console.ad.model.AdvertisingStatus.1006"/*已结束*/),Initial("optimus.console.ad.model.AdvertisingStatus.1007"/*未开启*/);

	private String text;

	public String getText() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
	}

	private AdvertisingStatus(String text) {
		this.text = text;
	}
}
