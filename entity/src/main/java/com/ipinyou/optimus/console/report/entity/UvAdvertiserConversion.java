/**
 * 
 */
package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author lijt
 *
 */
@Entity
@Table(name = "rpt_uv_advertiser_conversion")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UvAdvertiserConversion extends BaseUvConversion {
	
	private static final long serialVersionUID = 8885759873867697089L;
	
}
