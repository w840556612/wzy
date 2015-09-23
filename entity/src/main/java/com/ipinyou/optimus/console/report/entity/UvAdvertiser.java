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
@Table(name = "rpt_uv_advertiser")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UvAdvertiser extends BaseUv {

	private static final long serialVersionUID = 7579177051232210938L;

}
