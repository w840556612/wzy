package com.ipinyou.optimus.console.ad.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author lijt
 * @see http 
 *      ://stackoverflow.com/questions/6833370/jpa-onetoone-with-shared-id-can
 *      -i-do-this-better?answertab=oldest#tab-top
 * @see http 
 *      ://stackoverflow.com/questions/787698/jpa-hibernate-one-to-one-relationship
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "campaign" })
@EqualsAndHashCode(callSuper = true, exclude = { "campaign" })
public class CampaignStats extends BaseStats {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9094172837115670964L;

	@MapsId
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Campaign campaign;

	@Transient
	public BigDecimal getBalance() {
		if (campaign.getLimit()==null || campaign.getLimit().getTotalBudget() == null) {
			return null;
		}
		return campaign.getLimit().getTotalBudget().subtract(getAdvCost());
	}
}
