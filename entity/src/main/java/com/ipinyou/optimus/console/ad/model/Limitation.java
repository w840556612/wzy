/**
 * 
 */
package com.ipinyou.optimus.console.ad.model;
  
import java.math.BigDecimal;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import lombok.Data;
import com.ipinyou.base.entity.Component;

/**
 * 总阈值控制组件
 * @author lijt
 *
 */ 
@Embeddable
@Data
public class Limitation implements Component{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2153528319298769054L;

	/**
	 * 总预算，单位：元  
	 */
	@NotNull(groups=SaveChecksC.class)
	@Max(value=1000000000,message="超过系统允许的最大数值{value}")
	private BigDecimal totalBudget;

	/**
	 * 每日预算，单位：元
	 */
	@Max(value=1000000000,message="超过系统允许的最大数值{value}")
	private BigDecimal dailyBudget;
	
	/**
	 * 总曝光总阈值，单位： 千次曝光
	 */
	@Max(value=10000000,message="超过系统允许的最大数值{value}")
	private Long impTotalLimit;
	
	/**
	 * 总点击总阈值，单位： 次
	 */
	@Max(value=10000000,message="超过系统允许的最大数值{value}")
	private Long clickTotalLimit;
	
	/**
	 * 日曝光总阈值，单位： 千次曝光
	 */
	@Max(value=10000000,message="超过系统允许的最大数值{value}")
	private Long impDailyLimit;
	
	/**
	 * 日点击总阈值，单位： 次
	 */
	@Max(value=10000000,message="超过系统允许的最大数值{value}")
	private Long clickDailyLimit;
	
	public static interface SaveChecksC{}
}
