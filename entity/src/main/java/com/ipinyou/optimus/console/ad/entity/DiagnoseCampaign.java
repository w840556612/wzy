/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * 计划优化诊断
 * 
 * @author shewei.deng
 * 
 */
@Entity
@Table
@Data
@ToString
@EqualsAndHashCode(of = { "id" })
@JsonIgnoreProperties(value = { "campaign" })
public class DiagnoseCampaign implements com.ipinyou.base.entity.Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 818581857971726814L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Index(name = "cpid_idx")
	@Column(insertable = false, updatable = false, nullable = false)
	private Long campaignId;

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "campaign_id", nullable = false)
	private Campaign campaign;

	@NotNull
	@Size(min = 2, max = 10)
	@Column(nullable = false, length = 10)
	private String dimensionName;
	@NotNull
	@Size(min = 2, max = 100)
	@Column(nullable = false, length = 100)
	private String dimensionValue;

	@Column(length = 1)
	@Enumerated(EnumType.ORDINAL)
	private DiagNoseType diagnoseType = DiagNoseType.Na;

	public static enum DiagNoseType {
		Na("optimus.console.ad.entity.DiagnoseCampaign.1001"/*无*/), Cpc("CPC"), ClickRate("ftl.ad.index.1017"/*点击率*/), TargetRate("optimus.console.ad.entity.DiagnoseCampaign.1002"/*到达率*/), BidSuccessRate("optimus.console.ad.entity.DiagnoseCampaign.1003"/*竞价成功率*/);

		private String text;

		private DiagNoseType(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}

	@Index(name = "date_idx")
	@Column(updatable = true, nullable = false)
	private Date day;
	@NotNull
	@Column(nullable = false)
	private Boolean recommend;
	@Column(updatable = false, nullable = false)
	private Timestamp creation;

}
