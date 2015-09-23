/**
 * 
 */
package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

/**
 * @author lijt
 *
 */
@Entity
@Table(name = "rpt_uv_order")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UvOrder extends BaseUv {

	private static final long serialVersionUID = 3543253599349509122L;
	
	/**
	 * 订单id
	 */
	@Index(name="orderId")
	private long orderId;
	
}
