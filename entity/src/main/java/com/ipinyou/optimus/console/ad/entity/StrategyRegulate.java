package com.ipinyou.optimus.console.ad.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.StrategyRegulateCondition.ConditionType;

@Entity
@Table(name = "strategy_regulate")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true,exclude = { "conditions"})
public class StrategyRegulate extends TimedEntity implements Auditable<StrategyRegulate>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String REFNAME = "regulates.regulate";

	@NotNull
	private String name;
	
	/**
	 * 已删除
	 */
	private boolean removed = false;

	//关联投放策略
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch = FetchType.LAZY)
	@JoinColumn(name = "strategy_id", nullable = false)
	private Strategy strategy;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long strategyId;
	
	
	
	
	@OneToMany(mappedBy = "strategyRegulate")
	@OrderBy("lastModified")
	private Set<StrategyRegulateCondition> conditions;
	/**
	 * 执行等级
	 */
	@NotNull
	private int level;
	
	/**
	 * 立即执行
	 */
	private boolean immediateStart = false;
	
	public static enum FrequencyScope{
		H1("optimus.console.ad.entity.StrategyRegulate.1001"/*1小时*/),H3("optimus.console.ad.entity.StrategyRegulate.1002"/*3小时*/),H5("optimus.console.ad.entity.StrategyRegulate.1003"/*5小时*/),H10("optimus.console.ad.entity.StrategyRegulate.1004"/*10小时*/),
		D1("optimus.console.ad.entity.StrategyRegulate.1005"/*1天*/),D2("optimus.console.ad.entity.StrategyRegulate.1006"/*2天*/),D3("optimus.console.ad.entity.StrategyRegulate.1007"/*3天*/),D4("optimus.console.ad.entity.StrategyRegulate.1008"/*4天*/),D5("optimus.console.ad.entity.StrategyRegulate.1009"/*5天*/),D6("optimus.console.ad.entity.StrategyRegulate.1010"/*6天*/),D7("optimus.console.ad.entity.StrategyRegulate.1011"/*7天*/);
		private String text;
		
		private FrequencyScope(String text){
			this.text = text;
		}
		
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}
	//这个枚举类暂时没用，可以删掉
	public static enum DataScope{
		H1("optimus.console.ad.entity.StrategyRegulate.1001"/*1小时*/),H3("optimus.console.ad.entity.StrategyRegulate.1002"/*3小时*/),H5("optimus.console.ad.entity.StrategyRegulate.1003"/*5小时*/),H10("optimus.console.ad.entity.StrategyRegulate.1004"/*10小时*/),
		D1("optimus.console.ad.entity.StrategyRegulate.1005"/*1天*/),D2("optimus.console.ad.entity.StrategyRegulate.1006"/*2天*/),D3("optimus.console.ad.entity.StrategyRegulate.1007"/*3天*/),D4("optimus.console.ad.entity.StrategyRegulate.1008"/*4天*/),D5("optimus.console.ad.entity.StrategyRegulate.1009"/*5天*/),D6("optimus.console.ad.entity.StrategyRegulate.1010"/*6天*/),D7("optimus.console.ad.entity.StrategyRegulate.1011"/*7天*/);
		private String text;
		
		private DataScope(String text){
			this.text = text;
		}
		
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}
	
	public static enum OperateType{
		Proportion("optimus.console.ad.entity.StrategyRegulate.1012"/*按比例调价*/),Numerical("optimus.console.ad.entity.StrategyRegulate.1013"/*按数值调价*/),Keep("optimus.console.ad.entity.StrategyRegulate.1014"/*保持原有出价*/),
		Fixed("optimus.console.ad.entity.StrategyRegulate.1015"/*固定出价*/),Stop("optimus.console.ad.entity.StrategyRegulate.1016"/*不出价*/);
		
		private String text;
		
		private OperateType(String text){
			this.text = text;
		}
		
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}
	
	public static enum OperateDirect{
		Promote("optimus.console.ad.entity.StrategyRegulate.1017"/*提升*/),Reduce("optimus.console.ad.entity.StrategyRegulate.1018"/*降低*/);
		
		private String text;
		
		private OperateDirect(String text){
			this.text = text;
		}
		
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}
	
	public static enum OrderDirection{
		DESC("optimus.console.ad.entity.StrategyRegulate.1019"/*降序*/),ASC("optimus.console.ad.entity.StrategyRegulate.1020"/*升序*/);
		
		private String text;
		
		private OrderDirection(String text){
			this.text = text;
		}
		
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}
	
	
	/**
	 * 频率
	 */
	@Enumerated(EnumType.STRING)
	private FrequencyScope frequency;
	
	/**
	 * 数据时间跨度
	 */
	@Enumerated(EnumType.STRING)
	private FrequencyScope timeScope;
	
	
	/**
	 * 数据范围
	 */
	private String dataScope;
	
	//域名排序条件
	@Enumerated(EnumType.STRING)
	private ConditionType domainOrder;
	//域名排序方向(正序，倒序)
	@Enumerated(EnumType.STRING)
	private OrderDirection domainOrderDirection;
	//出价方式
	@Enumerated(EnumType.STRING)
	private OperateType operationType;
	//操作方向
	@Enumerated(EnumType.STRING)
	private OperateDirect operationDirect;
	//操作参数
	private Double operationParam;
	
	//域名列表预留1
	private String blackList;
		
	//域名列表预留2
	private String whiteList;
	
	private Date customDate;
	
	/*
	 * 自定义数据范围，广告主
	 */
	private Long dataScopeAdvertiser; 
	
	/*
	 * 自定义数据范围，订单
	 */
	private Long dataScopeOrder;
	
	/*
	 * 自定义数据范围，计划
	 */
	private Long dataScopeCampaign;
	/*
	 * 自定义数据范围，策略
	 */
	private Long dataScopeStrategy;
	@Override
    public String getEntityName() {
	    return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.StrategyRegulate.1021")/*策略自动优化设置*/;
    }
	@Transient
	private StrategyRegulate orig;
	
}
