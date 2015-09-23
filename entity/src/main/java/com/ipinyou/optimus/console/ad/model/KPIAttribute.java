package com.ipinyou.optimus.console.ad.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;

import com.ipinyou.base.entity.Component;

/**
 * 订单、计划的KPI目标属性
 * @author xiaobo.wang
 *
 */
@Embeddable
@Data
public class KPIAttribute implements Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6177911189623070795L;
	
	
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private KPIType kpiType1;
	
	@Column(scale=2)	
	private BigDecimal price1 = new BigDecimal(0.00);
	
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private KPIType kpiType2;
	
	@Column(scale=2)	
	private BigDecimal price2 = new BigDecimal(0.00);
	
	public static enum KPIType {
		ClickPrice("optimus.console.ad.model.KPIAttribute.1001"/*点击单价*/),
		ReachPrice("optimus.console.ad.model.KPIAttribute.1002"/*到达单价*/),
		ConvertPrice("optimus.console.ad.model.KPIAttribute.1003"/*转化单价*/),
		ImpConvertPrice("optimus.console.ad.model.KPIAttribute.1004"/*曝光转化单价*/),
		ClickConvertPrice("optimus.console.ad.model.KPIAttribute.1005"/*点击转化单价*/),
		TwoJumpPrice("optimus.console.ad.model.KPIAttribute.1006"/*二跳单价*/),
		ThreeJumpPrice("optimus.console.ad.model.KPIAttribute.1007"/*三跳单价*/);
		
		private String text;
		
		private KPIType(String text){
			this.text = text;
		}
		
		public String getText(){
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
		
	}

}
