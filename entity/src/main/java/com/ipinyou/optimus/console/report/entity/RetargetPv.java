package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 我的访客-访客pv
 * 
 * @author dengsw
 */
@Entity
@Table(name = "rpt_retarget_pv")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RetargetPv extends BaseVisitor {

	private static final long serialVersionUID = 8229289441908767762L;

	/**
	 * logDate当天访客pv
	 */
	@NotNull
	private long retargetPv;

	/**
	 * logDate当天总bid pv
	 */
	@NotNull
	private long totalBidPv;

}
