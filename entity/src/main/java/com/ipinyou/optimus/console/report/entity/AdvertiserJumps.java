package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 广告主二跳～多跳信息
 */
@Entity
@Table(name = "detail_advertiser_jumps")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdvertiserJumps extends BaseLogDetail{
	
	private static final long serialVersionUID = -2903602406275441695L;

	@Lob
	private String deviceIds;
	
	private Long labelId;

}
