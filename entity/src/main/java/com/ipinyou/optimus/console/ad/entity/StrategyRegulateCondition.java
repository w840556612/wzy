package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.TimedEntity;

@Entity
@Table(name = "strategy_regulate_condition")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StrategyRegulateCondition extends TimedEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 已删除
	 */
	private boolean removed = false;

	
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "regulate_id", nullable = false)
	private StrategyRegulate strategyRegulate;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long regulateId;
	
	
	public static enum ConditionType{
		Biddings("optimus.console.ad.entity.StrategyRegulateCondition.1001"/*竞价数(千次)*/),Impression("optimus.console.ad.entity.StrategyRegulateCondition.1002"/*曝光数(千次)*/),Click("optimus.console.ad.entity.StrategyRegulateCondition.1003"/*点击数*/),ThroughNum("optimus.console.ad.entity.StrategyRegulateCondition.1004"/*到达数*/),
		ClickConversionNum("optimus.console.ad.entity.StrategyRegulateCondition.1005"/*点击转化数*/),Consumption("optimus.console.ad.entity.StrategyRegulateCondition.1006"/*消耗量*/),Order("optimus.console.ad.entity.StrategyRegulateCondition.1007"/*订单金额*/),CTR("ftl.ad.index.1017"/*点击率*/),
		ThroughRate("optimus.console.ad.entity.DiagnoseCampaign.1002"/*到达率*/),ClickConversionRate("optimus.console.ad.entity.StrategyRegulateCondition.1008"/*点击转化率*/),CPM("optimus.console.ad.entity.StrategyRegulateCondition.1009"/*cpm价格*/),
		CPC("optimus.console.ad.entity.Strategy.1038"/*cpc价格*/),ThroughPrice("optimus.console.ad.model.KPIAttribute.1002"/*到达单价*/),ClickThroughPrice("optimus.console.ad.model.KPIAttribute.1005"/*点击转化单价*/),
		ImpressionPriceAVG("optimus.console.ad.entity.StrategyRegulateCondition.1010"/*平均曝光出价*/),ROI("roi"),BiddingSuccessRate("optimus.console.ad.entity.DiagnoseCampaign.1003"/*竞价成功率*/),
		ImpressionConversionNum("optimus.console.ad.entity.StrategyRegulateCondition.1011"/*曝光转化数*/),ImpressionConversionRate("optimus.console.ad.entity.Strategy.1037"/*曝光转化率*/),ImpressionConversionPrice("optimus.console.ad.model.KPIAttribute.1004"/*曝光转化单价*/);
		
		private String text;
		
		private ConditionType(String text){
			this.text = text;
		}
		
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}
	
	public static enum ConditionDirect{
		gt("optimus.console.ad.entity.StrategyRegulateCondition.1012"/*大于*/),lte("optimus.console.ad.entity.StrategyRegulateCondition.1013"/*小于等于*/);
		
		private String text;
		
		private ConditionDirect(String text){
			this.text = text;
		}
		
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}
	
	//出价方式
	@Enumerated(EnumType.STRING)
	private ConditionType conditionType;
	//操作方向
	@Enumerated(EnumType.STRING)
	private ConditionDirect conditionDirect;
	//操作参数
	private Double conditionParam;
}
