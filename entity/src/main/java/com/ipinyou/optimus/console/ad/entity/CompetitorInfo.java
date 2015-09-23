package com.ipinyou.optimus.console.ad.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 策略截图信息
 * @author root
 *
 */

@Entity
@Table(name = "competitor_info")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CompetitorInfo extends TimedEntity implements Auditable<CompetitorInfo>{
	

	/*
	 * 任务ID
	 */
	private Long taskId;
	
	/*
	 * 扫描批次
	 */
	private Long label;
	
	/*
	 * 页面URL
	 */
	private String url;
	
	/*
	 * 域名
	 */
	private String domain;
	
	/*
	 * 广告尺寸
	 */
	private String size;
	
	/*
	 * 广告位置
	 */
	private String local;
	
	/*
	 * 投放平台
	 */
	private String exchange;
	
	/*
	 * dsp
	 */
	private String dsp;
	
	/*
	 * 点击地址
	 */
	private String clickUrl;
	
	/*
	 * 到达地址
	 */
	private String reachUrl;
	
	/*
	 * 删除标记
	 */
	private boolean removed = false;
	
	/*
	 * 广告主
	 */
	private String advertiser;
	
	/*
	 * 同一iframe中的被识别的标签数量
	 */
	private int adNumber;
	
	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.CompetitorInfo.1001")/*竞争对手信息*/;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.CompetitorInfo.1001")/*竞争对手信息*/;
	}

	@Override
	public CompetitorInfo getOrig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOrig(CompetitorInfo t) {
		// TODO Auto-generated method stub
		
	}

	

}
