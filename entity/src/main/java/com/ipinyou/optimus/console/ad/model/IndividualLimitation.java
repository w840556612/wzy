/**
 * 
 */
package com.ipinyou.optimus.console.ad.model;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;

import com.ipinyou.base.entity.Component;

import lombok.Data;

/**
 * 单人频次控制组件
 * @author lijt
 * 
 */
@Embeddable
@Data
public class IndividualLimitation implements Component{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4371711674002442960L;

	public static enum LimitationPeriod {
		Day("optimus.console.ad.model.IndividualLimitation.1001"/*天*/), Week("optimus.console.ad.model.IndividualLimitation.1002"/*周*/), AdvertisingPeriod("ftl.import.spring.1073"/*投放周期*/);
		
		private String text;
		
		private LimitationPeriod(String text) {
			this.text = text;
		}

		public String getText() {
			return com.ipinyou.base.util.I18nResourceUtil.getResource(text);
		}
	}

	/**
	 * 曝光上限
	 */
	@Max(32767)
	private Short impLimit;
	
	/**
	 * 曝光上限控制周期
	 */
	@Enumerated(EnumType.STRING)
	private LimitationPeriod impLimitPeriod;

	/**
	 * 点击上限
	 */
	@Max(32767)
	private Short clickLimit;
	
	/**
	 * 点击上限控制周期
	 */
	@Enumerated(EnumType.STRING)
	private LimitationPeriod clickLimitPeriod;
}
