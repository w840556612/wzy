/**
 * 
 */
package com.ipinyou.optimus.console.ad.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.optimus.console.ad.entity.Strategy;
import com.ipinyou.optimus.console.ad.model.StrategyBusinessError;

/**
 * 操作返回结果VO（关闭、打开状态更新）
 * @author zhyhang
 *
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StrategyUpdateStatusResultVo extends OperateResultVo{
	
	private static final long serialVersionUID = 6842833621979186373L;
	private List<Strategy> updatedStrategies;
	private List<StrategyAdvertisingStatusVo> advertisingStatusVos;
	private StrategyBusinessError errorCode;
	private List<CampaignAdvertisingStatusVo>campaignAdvertisingStatusVos;
	
}
