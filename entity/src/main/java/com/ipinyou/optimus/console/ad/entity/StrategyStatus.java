package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.ipinyou.base.entity.NoIdTimedEntity;

/**
 * 
 * 策略运行状态（此表数据由console创建，由投放系统修改维护）
 * 
 * @author xiaobo.wang
 * 
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "strategy" })
@EqualsAndHashCode(callSuper = true, exclude = { "strategy" })
public class StrategyStatus extends NoIdTimedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1633793776417198004L;

	public static enum Status {
		STOP("optimus.console.ad.model.AdvertisingStatus.1002"/*未投放*/), ADVERTISING("optimus.console.ad.model.AdvertisingStatus.1001"/*投放中*/);

		private String text;

		private Status(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}

	public static enum Reason {
		BNE("optimus.console.ad.entity.StrategyStatus.1001"/*余额不足*/), OTB("optimus.console.ad.entity.StrategyStatus.1002"/*到达订单总预算*/), CTB("optimus.console.ad.entity.StrategyStatus.1003"/*到达计划总预算*/), CDB("optimus.console.ad.entity.StrategyStatus.1004"/*到达计划每日预算*/), OCD(
				"optimus.console.ad.entity.StrategyStatus.1005"/*当前日期不在计划投放日期范围内*/), MCC("optimus.console.ad.entity.StrategyStatus.1006"/*所属计划未开启*/), CTE("optimus.console.ad.entity.StrategyStatus.1007"/*到达计划总曝光上限*/), CTC(
				"optimus.console.ad.entity.StrategyStatus.1008"/*到达计划总点击上限*/), CDE("optimus.console.ad.entity.StrategyStatus.1009"/*到达计划日曝光上限*/), CDC("optimus.console.ad.entity.StrategyStatus.1010"/*到达计划日点击上限*/), OSD(
				"optimus.console.ad.entity.StrategyStatus.1011"/*当前日期不在策略投放日期范围内*/), OST("optimus.console.ad.entity.StrategyStatus.1012"/*当前时间不在策略投放时段*/), NCA("optimus.console.ad.entity.StrategyStatus.1013"/*没有创意可以投放*/), CNP(
				"optimus.console.ad.entity.StrategyStatus.1014"/*没有审核通过创意*/), TEM("optimus.console.ad.entity.StrategyStatus.1015"/*到达策略总曝光阀值*/), TCM("optimus.console.ad.entity.StrategyStatus.1016"/*到达策略总点击阀值*/), DEM(
				"optimus.console.ad.entity.StrategyStatus.1017"/*到达策略日曝光阀值*/), DCM("optimus.console.ad.entity.StrategyStatus.1018"/*到达策略日点击阀值*/), STB("optimus.console.ad.entity.StrategyStatus.1019"/*到达策略总预算*/), SDB("optimus.console.ad.entity.StrategyStatus.1020"/*到达策略每日预算*/), MSC(
				"optimus.console.ad.entity.StrategyStatus.1021"/*策略未开启*/), WNS("optimus.console.ad.entity.StrategyStatus.1022"/*天气定向的条件全部不满足*/), ULE("optimus.console.ad.entity.StrategyStatus.1023"/*联合频控设置错误*/),

		NoReason("");

		private String text;

		private Reason(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}

	}

	@Id
	private Long id;

	// @OneToOne(optional = false, fetch=FetchType.LAZY)
	// @JoinColumn(name = "strategy_id", nullable = false, unique = true)
	@MapsId
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	// (mappedBy = "runStatus")
	@JoinColumn(name = "id")
	private Strategy strategy;

	@Index(name = "reason")
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private Reason reason;

	@Column(length = 500)
	private String reasonDesc;

}
