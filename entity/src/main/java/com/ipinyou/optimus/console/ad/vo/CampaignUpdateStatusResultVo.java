/**
 * 
 */
package com.ipinyou.optimus.console.ad.vo;

import java.util.List;

import com.ipinyou.optimus.console.ad.entity.Campaign;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 操作返回结果VO（关闭、打开状态更新）
 * @author zhyhang
 *
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CampaignUpdateStatusResultVo extends OperateResultVo{
	
	private static final long serialVersionUID = 7616786713614391603L;
	private List<Campaign> updatedCampaigns;
	private List<CampaignAdvertisingStatusVo> advertisingStatusVos;
	
}
