/**
 * 
 */
package com.ipinyou.optimus.console.ad.vo;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.model.BaseVo;
import com.ipinyou.optimus.console.ad.entity.Advertiser.AdvertiserStatus;
import com.ipinyou.optimus.console.ad.entity.CampaignStatus;
import com.ipinyou.optimus.console.ad.entity.CampaignStatus.Reason;
import com.ipinyou.optimus.console.ad.entity.CampaignStatus.Status;
import com.ipinyou.optimus.console.ad.model.AdvertisingStatus;

/**
 * 计划投放状态VO
 * 
 * @author zhyhang
 * 
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CampaignAdvertisingStatusVo extends BaseVo {
	private static final long serialVersionUID = -4928481480043594249L;

	public CampaignAdvertisingStatusVo(Long id, Long strategyCount,
			Long advertisingStrategyCount, AdvertiserStatus adverStatus,
			Status campaignStatus, Reason reason,Boolean active) {
		this.id = id;
		this.strategyCount = strategyCount;
		this.advertisingStrategyCount = advertisingStrategyCount;
		this.reason = reason;
		this.runStatus=campaignStatus;
		setAdvingStatus(campaignStatus, adverStatus, active);
	}

	private Long id;

	/**
	 * 总策略数
	 */
	private Long strategyCount;

	/**
	 * 投放中策略数
	 */
	private Long advertisingStrategyCount;

	/**
	 * 显示E版的投放状态
	 */
	@Enumerated(EnumType.STRING)
	private AdvertisingStatus status = AdvertisingStatus.Close;

	/**
	 * 计划的真是运行状态
	 */
	@Enumerated(EnumType.STRING)
	private Status runStatus = Status.STOP;
	
	/**
	 * 计划未投放原因
	 */
	private Reason reason;
	
	/**
	 * E版翻译过来的显示字符
	 */
	public String getStatusText(){
		return (AdvertisingStatus.Advertising == getStatus() || (AdvertisingStatus.Stop == getStatus() && (getReason()==null || Reason.NoReason==getReason())))? getStatus()
				.getText()
				+ "("
				+ getAdvertisingStrategyCount()
				+ "/"
				+ getStrategyCount() + ")" : getStatus().getText();
	}
	
	/**
	 * E版得到状态提示信息的文本描述
	 * @return
	 */
	public String getToolTipText(){
		if(AdvertisingStatus.Approving==getStatus()){
			return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.vo.CampaignAdvertisingStatusVo.1001")/*请尽快去个人中心完善您的广告主资质，审核通过后才可进行广告投放。*/;
		}else if(AdvertisingStatus.ApproveDenial==getStatus()){
			return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.vo.CampaignAdvertisingStatusVo.1002")/*您的广告主资质审核被拒，暂不能投放广告。如有疑问，请联系客服。*/;
		}else if(AdvertisingStatus.Stop == getStatus() && getReason()!=null && Reason.NoReason!=getReason()){
			return getReason().getText();
		}else{
			return null;
		}
	}
	
	/**
	 * E版状态转换
	 * @return
	 */
	private void setAdvingStatus(Status campaignStatus, AdvertiserStatus advserStatus, Boolean active) {
		// 计划可投放标志是close，则投放状态改为Close
		if (!active.booleanValue()) {
			this.setStatus(AdvertisingStatus.Close);
			// 广告主资质待审核（当广告主资质在审核中时显示此状态）
		}
//		else if (AdvertiserStatus.CAN_NOT_AUDIT == advserStatus
//				|| AdvertiserStatus.NOT_CHECKED == advserStatus) {
//			this.setStatus(AdvertisingStatus.Approving);
//			// 广告主资质审核拒绝
//		} else if (AdvertiserStatus.DISAPPROVED == advserStatus) {
//			this.setStatus(AdvertisingStatus.ApproveDenial);
//			// 计划处于未投放
//		} 
		else if (campaignStatus.equals(CampaignStatus.Status.STOP)){
			this.setStatus(AdvertisingStatus.Stop);
			//计划是投放中，且策略没有投放中的，则状态为未投放（0个策略的状态是“投放中”）
		}else if (campaignStatus.equals(CampaignStatus.Status.ADVERTISING) && this.getAdvertisingStrategyCount() == 0) {
			this.setStatus(AdvertisingStatus.Stop);
			// 计划是投放中，且策略有投放中的，则状态为投放中(5/9)
		} else if (campaignStatus.equals(CampaignStatus.Status.ADVERTISING) && this.getAdvertisingStrategyCount() > 0) {
			this.setStatus(AdvertisingStatus.Advertising);
		}
	}

	/**
	 * F版状态转换
	 * @return
	 */
	public Status getFullShowStatus(){
		if (Status.ADVERTISING==this.runStatus && this.getAdvertisingStrategyCount() > 0){
			return Status.ADVERTISING;
		}
		return Status.STOP;
	}
	/**
	 * F版得到状态提示信息的文本描述
	 * @return
	 */
	public String getFullToolTipText(){
		if (Status.STOP==getFullShowStatus() && (getReason()!=null && Reason.NoReason!=getReason()) ){
			return getReason().getText();
		}
		return null;
	}
	
	/**
	 * F版翻译过来的显示字符
	 * @return
	 */
	public String getFullStatusText(){
		StringBuffer stringStatus = new StringBuffer("");
		if (Status.ADVERTISING==this.runStatus && this.getAdvertisingStrategyCount() > 0){
			stringStatus.append(AdvertisingStatus.Advertising.getText());
		}else if (Status.ADVERTISING==this.runStatus && this.getAdvertisingStrategyCount() == 0){
			stringStatus.append(AdvertisingStatus.Stop.getText());
		}else{
			return AdvertisingStatus.Stop.getText();
		}
		stringStatus.append("(");
		stringStatus.append(getAdvertisingStrategyCount());
		stringStatus.append("/");
		stringStatus.append(getStrategyCount());
		stringStatus.append(")");
		return stringStatus.toString();
	}
}
