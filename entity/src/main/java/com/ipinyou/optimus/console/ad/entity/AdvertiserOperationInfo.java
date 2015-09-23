/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import com.ipinyou.optimus.console.user.entity.User;

/**
 * 广告主
 * 
 * @author lijt
 * 
 */
@Entity
@Data
@Table(name = "advertiser_operation_info")
@ToString(callSuper = true, exclude = { "preSale", "serviceIds", "afterSale", "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "preSale", "serviceIds", "afterSale", "orig" })
@JsonIgnoreProperties(value = { "version", "lastModified", "preSale", "serviceIds", "afterSale", "orig" })
public class AdvertiserOperationInfo extends TimedEntity implements Auditable<AdvertiserOperationInfo> {

	private static final long serialVersionUID = -8434403672512559697L;

	@MapsId
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Advertiser advertiser;
	
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
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.AdvertiserOperationInfo.1001")/*广告主运营信息*/;
	}

	@Override
	public String getName() {
		return "advertiser" + getId();
	}

	@Transient
	private AdvertiserOperationInfo orig;

	@Override
	public void setOrig(AdvertiserOperationInfo orig) {
		this.orig = orig;
	}

	@Override
	public AdvertiserOperationInfo getOrig() {
		return orig;
	}

	
	/**
	 * 返回活动的销售人员
	 * @return
	 */
	public User getActivePreSale(){
		if(this.preSale.isActive()){
			return this.afterSale;
		}else{
			return null;
		}
	}

}
