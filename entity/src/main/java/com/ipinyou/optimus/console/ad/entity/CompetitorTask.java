package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.StrategyBlackWhiteUrl.UrlList;

@Entity
@Table(name = "competitor_task")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CompetitorTask  extends TimedEntity implements Auditable<CompetitorTask>{
	
	
	private String name;
	
	/**
	 * 全局域名Urls
	 */
	@Embedded
	@Lob
	@AttributeOverride(name="strValue",column=@Column(name="urls",length=16777215))
	private UrlList urls;
	
	/**
	 * 前置动作Urls
	 */
	@Embedded
	@Lob
	@AttributeOverride(name="strValue",column=@Column(name="before_urls",length=16777215))
	private UrlList beforeUrls;
	
	/*
	 * 开启标记
	 */
	private boolean active;
	
	/*
	 * 删除标记
	 */
	private boolean removed = false;
	
	/*
	 * 扫描次数
	 */
	private int scanNum = 1;
	
	/*
	 * 完成抓取domain数量
	 */
	private int fulfiledDomain = 0;
	
	/*
	 * 完成次数
	 */
	private int fulfiledNum =0;
	

	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.CompetitorTask.1001")/*获取竞争对手信息任务*/;
	}

	@Override
	public CompetitorTask getOrig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOrig(CompetitorTask t) {
		// TODO Auto-generated method stub
	}
}
