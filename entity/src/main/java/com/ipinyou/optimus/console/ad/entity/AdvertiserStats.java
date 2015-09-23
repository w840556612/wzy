/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author lijt
 *
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "advertiser" })
@EqualsAndHashCode(callSuper = true, exclude = { "advertiser" })
public class AdvertiserStats extends BaseStats {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7330103073235770709L;

	@MapsId
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Advertiser advertiser;

}
