/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.NoIdTimedEntity;

/**
 * @author lijt
 *
 */
@MappedSuperclass
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BaseStats extends NoIdTimedEntity {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6496837817123259649L;
	@Id
	protected Long id;
	/**
	 * 展示、曝光数
	 */
	protected long impressions;

	/**
	 * 点击数
	 */
	protected long clicks;
	
	/**
	 *  广告交易平台成本，媒体采购费，等于成交价+平台损耗+税务消耗，税务消耗只有Goole Adx才有，目前为6%,单位元，8位小数
	 */
	protected BigDecimal cost = new BigDecimal(0);

	/**
	 * 品友收取的代理商服务费，单位元，8位小数
	 */
	protected BigDecimal agencySrvCost = new BigDecimal(0);
	
	/**
	 * 代理商总费用，单位元，8位小数， agencyCost=cost+agencySrvCost
	 */
	protected BigDecimal agencyCost = new BigDecimal(0);
	
	/**
	 * 代理商应该收取的广告主服务费，单位元，8位小数
	 */
	protected BigDecimal advSrvCost = new BigDecimal(0);

	/**
	 * 额外显示的广告主服务费，注意是服务费，单位元，8位小数，注意：这一部分仅仅在报表中显示，不算入余额控制、阈值控制、账单
	 */
	protected BigDecimal extraShowAdvCost = new BigDecimal(0);
	
	/**
	 * 广告主总费用，单位元，8位小数，advCost=agencyCost+advSrvCost, 不包括extraShowAdvCost
	 */
	private BigDecimal advCost = new BigDecimal(0);

	/**
	 * 每千次展示费用，根据每1000个广告展示量收费
	 */
	@Transient
	public BigDecimal getCpmPrice() {
		if (impressions == 0) {
			return null; // 没有发生曝光，无法计算CPM价格
		} else {
			return getAdvCost().divide(new BigDecimal(impressions * 100), 2,
					RoundingMode.HALF_UP);
		}
	}

	/**
	 * 每次点击的费用，根据广告点击量收费
	 */
	@Transient
	public BigDecimal getCpcPrice() {
		if (clicks == 0) {
			return null; // 没有发生点击，无法计算CPM价格
		} else {
			return getAdvCost().divide(new BigDecimal(clicks), 2,
					RoundingMode.HALF_UP);
		}
	}

	/**
	 * @return 总消耗，单位: 元
	 */
	@Transient
	public BigDecimal getCostTotal() {
		return getAdvCost().add(getExtraShowAdvCost()).divide(new BigDecimal(1), 2, RoundingMode.HALF_UP);
	}
	//无意义的方法
	@Transient
	public void setCostTotal(BigDecimal costTotal){}
}
