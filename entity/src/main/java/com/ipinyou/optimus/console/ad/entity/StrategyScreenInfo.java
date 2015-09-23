package com.ipinyou.optimus.console.ad.entity;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;


/**
 * 策略截图信息
 * @author root
 *
 */

@Entity
@Table(name = "strategy_screen_info")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StrategyScreenInfo extends TimedEntity implements Auditable<StrategyScreenInfo>{
	
	private static final long serialVersionUID = 1L;


@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.StrategyScreenInfo.1001")/*策略截图信息*/;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.StrategyScreenInfo.1001")/*策略截图信息*/;
	}
	@Override
	public StrategyScreenInfo getOrig() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setOrig(StrategyScreenInfo t) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * 所属的策略
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch=FetchType.LAZY)
	@JoinColumn(name = "strategy_id", nullable = false)
	private Strategy strategy;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long strategyId;
	
	/*
	 * 文件名
	 */
	@NotNull
	private String fileName;
	
	/**
	 * 路径 
	 */
	@NotNull
	private String path;
	
	
	/**
	 * 宽
	 */
	@NotNull
	private int width;
	
	/**
	 * 高
	 */
	@NotNull
	private int  height;
	
	
	/**
	 * 删除状态
	 */
	@NotNull
	private boolean removed = false;

	/**
	 * 截图站点
	 */
	@NotNull
	private String site;
	
	/**
	 * PYID
	 */
	@NotNull
	private String pyid;

}
