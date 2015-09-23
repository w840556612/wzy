/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
@Table(name = "advertiser_property")
@ToString(callSuper = true, exclude = { "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
public class AdvertiserProperty extends TimedEntity implements Auditable<AdvertiserProperty> {

	private static final long serialVersionUID = -8434403672512559697L;

	@MapsId
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Advertiser advertiser;

	/**
	 * 人群列表
	 */
	@Column(name = "auIds", length = 2000)
	private String auIds;

	/**
	 * 人群列表,带父节点
	 */
	@Column(name = "audienceIds", length = 2000)
	private String audienceIds;

	@Transient
	private List<String> audienceNames;

	@Override
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.AdvertiserProperty.1001")/*广告主类目信息*/;
	}

	@Override
	public String getName() {
		return "advertiser" + getId();
	}

	@Transient
	private AdvertiserProperty orig;

	@Override
	public void setOrig(AdvertiserProperty orig) {
		this.orig = orig;
	}

	@Override
	public AdvertiserProperty getOrig() {
		return orig;
	}

}
