/**
 * 
 */
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
 * 计划运行状态（此表数据由console创建，由投放系统修改维护）
 * 
 * @author lijt
 * 
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "campaign" })
@EqualsAndHashCode(callSuper = true, exclude = { "campaign" })
public class CampaignStatus extends NoIdTimedEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7535517978457184757L;

	public static enum Status {
		STOP("optimus.console.ad.entity.CampaignStatus.1001"/*未投放*/), ADVERTISING("optimus.console.ad.entity.CampaignStatus.1002"/*投放中*/);

		private String text;

		private Status(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}

	public static enum Reason {
		BNE("optimus.console.ad.entity.CampaignStatus.1003"/*余额不足*/), OTB("optimus.console.ad.entity.CampaignStatus.1004"/*到达订单总预算*/), CTB("optimus.console.ad.entity.CampaignStatus.1005"/*到达计划总预算*/), CDB("optimus.console.ad.entity.CampaignStatus.1006"/*到达计划每日预算*/), OCD(
				"optimus.console.ad.entity.CampaignStatus.1007"/*当前日期不在计划投放日期范围内*/), MCC("optimus.console.ad.entity.CampaignStatus.1008"/*计划未开启*/), CTE("optimus.console.ad.entity.CampaignStatus.1009"/*到达计划总曝光上限*/), CTC(
				"optimus.console.ad.entity.CampaignStatus.1010"/*到达计划总点击上限*/), CDE("optimus.console.ad.entity.CampaignStatus.1011"/*到达计划日曝光上限*/), CDC("optimus.console.ad.entity.CampaignStatus.1012"/*到达计划日点击上限*/), ULE(
				"optimus.console.ad.entity.CampaignStatus.1013"/*联合频控设置错误*/),

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
	private Campaign campaign;

	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private Status status;

	@Index(name = "reason")
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private Reason reason;

	@Column(length = 500)
	private String reasonDesc;
}
