/**
 * 
 */
package com.ipinyou.optimus.console.ad.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.model.BaseVo;
import com.ipinyou.optimus.console.ad.entity.Advertiser.AdvertiserStatus;
import com.ipinyou.optimus.console.ad.entity.StrategyStatus.Reason;
import com.ipinyou.optimus.console.ad.entity.StrategyStatus.Status;
import com.ipinyou.optimus.console.ad.model.AdvertisingStatus;

/**
 * 策略投放状态VO
 * 
 * @author zhyhang
 * 
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StrategyAdvertisingStatusVo extends BaseVo {
	private static final long serialVersionUID = -4928481480043594249L;

	public StrategyAdvertisingStatusVo(Long id,AdvertiserStatus adverStatus,Status strategyStatus,
			Reason reason,Boolean active,Boolean regulateStatus) {
		this.id = id;
		this.reason=reason;
		this.strategyStatus = strategyStatus;
		this.regulateStatus = regulateStatus;
		setAdvingStatus(adverStatus,strategyStatus, active);
	}

	/**
	 * 策略ID
	 */
	private Long id;
	
	/**
	 * 未投放状态原因
	 */
	private Reason reason;
	
	
	private Status strategyStatus;

	/**
	 * 投放状态
	 */
	private AdvertisingStatus status = AdvertisingStatus.Close;
	
	/**
	 * full版投放状态
	 */
	private String runStatusText = Status.STOP.getText();
	
	/**
	 * 自动优化开启状态
	 */
	private boolean regulateStatus;
	

	/**
	 * 得到状态提示信息的文本描述
	 * @return
	 */
	public String getToolTipText(){
		if(AdvertisingStatus.Approving==getStatus()){
			return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.vo.CampaignAdvertisingStatusVo.1001")/*请尽快去个人中心完善您的广告主资质，审核通过后才可进行广告投放。*/;
		}else if(AdvertisingStatus.ApproveDenial==getStatus()){
			return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.vo.CampaignAdvertisingStatusVo.1002")/*您的广告主资质审核被拒，暂不能投放广告。如有疑问，请联系客服。*/;
		}else if(AdvertisingStatus.Close == getStatus()){
			return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.vo.StrategyAdvertisingStatusVo.1001")/*策略已关闭*/;
		}else if(AdvertisingStatus.Stop==getStatus()){
			return getReason().getText();
		}else{
			return null;
		}
	}
	

	/**
	 * 得到状态的文本描述
	 * @return
	 */
	public String getStatusText(){
		return getStatus().getText();
	}
	
	private void setAdvingStatus(AdvertiserStatus advserStatus,
			Status strategyStatus, Boolean active) {
		// 策略可投放标志是close，则投放状态改为Close
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
//			// 未投放
//		} 
		else if (Status.STOP == strategyStatus) {
			this.setStatus(AdvertisingStatus.Stop);
			// 投放中
		} else if (Status.ADVERTISING == strategyStatus) {
			this.setStatus(AdvertisingStatus.Advertising);
		}
		this.runStatusText = strategyStatus.getText();		
	}

}
