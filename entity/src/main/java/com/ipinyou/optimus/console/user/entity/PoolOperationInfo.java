/**
 * 
 */
package com.ipinyou.optimus.console.user.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;

/**
 * 广告主
 * 
 * @author lijt
 * 
 */
@Entity
@Data
@Table(name = "pool_operation_info")
@ToString(callSuper = true, exclude = { "preSale", "serviceIds", "afterSale", "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "preSale", "serviceIds", "afterSale", "orig" })
@JsonIgnoreProperties(value = { "version", "lastModified", "preSale", "serviceIds", "afterSale", "orig" })
public class PoolOperationInfo extends TimedEntity implements Auditable<PoolOperationInfo> {

	private static final long serialVersionUID = -8434403672512559697L;

	@MapsId
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Pool pool;

	public static enum DasuanpanPoolType {
		Gold("指定-金牌"), Silver("指定-银牌"), Core("普通-重点"), Normal("普通-一般");

		private String text;

		private DasuanpanPoolType(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

	/**
	 * 大算盘代理商类型
	 */
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private DasuanpanPoolType type;
	/**
	 * 归属销售
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "pre_sale_id", nullable = true, unique = false)
	private User preSale;

	/**
	 * 所属各优化师的帐号id,逗号隔开
	 */
	@Column(length = 500)
	private String serviceIds;

	/**
	 * 所属各优化师的姓名,逗号隔开
	 */
	@Transient
	private String serviceNames;

	/**
	 * 归属续费
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "after_sale_id", nullable = true, unique = false)
	private User afterSale;


	@Override
	public String getEntityName() {
		return "大算盘运营信息";
	}

	@Override
	public String getName() {
		return "pool" + getId();
	}

	@Transient
	private PoolOperationInfo orig;

	@Override
	public void setOrig(PoolOperationInfo orig) {
		this.orig = orig;
	}

	@Override
	public PoolOperationInfo getOrig() {
		return orig;
	}

}
