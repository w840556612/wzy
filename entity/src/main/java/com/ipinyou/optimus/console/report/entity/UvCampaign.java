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
@Table(name = "rpt_uv_campaign")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UvCampaign extends BaseUv {
	
	private static final long serialVersionUID = 5605646456363988784L;
	
	/**
	 * 订单id
	 */
	private long orderId;
	
	/**
	 * 计划id
	 */
	@Index(name="campaignId")
	private long campaignId;
	
}
