/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author lijt
 * 
 */
@Entity
@Table(name="ad_order_stats")
@Data
@ToString(callSuper = true, exclude = { "order" })
@EqualsAndHashCode(callSuper = true, exclude = { "order" })
public class OrderStats extends BaseStats {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2691903981483649263L;

	@MapsId
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Order order;
}
