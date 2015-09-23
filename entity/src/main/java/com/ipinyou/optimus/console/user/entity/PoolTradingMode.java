package com.ipinyou.optimus.console.user.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import lombok.Data;

import org.hibernate.annotations.Where;
import org.springframework.format.annotation.NumberFormat;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.entity.UserDefineList;
import com.ipinyou.optimus.console.ad.entity.AgencyProtectHistory;
import com.ipinyou.optimus.console.ad.entity.Advertiser.ConsumerPattern;

@Entity
@Table(name = "usr_pool_trading_mode")
@Data
public class PoolTradingMode extends TimedEntity implements Auditable<PoolTradingMode>{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7606352428708991289L;

	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return "代理商交易模式";
	}

	@Override
	public PoolTradingMode getOrig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOrig(PoolTradingMode t) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * 模式名称
	 */
	@Column(length = 64)
	@Size(min=2,max=64)
	private String name;
	
	/*
	 * 代理商类型
	 */
	@Column(length = 64)
	@Size(min=2,max=64)
	private String poolType;
	
	/*
	 * 代理商级别
	 */
	@Column(length = 64)
	@Size(min=2,max=64)
	private String poolHierarchy;
	
	/*
	 * 代理商业务模式
	 */
	@Column(length = 64)
	@Size(min=2,max=64)
	private String poolMode;
	
	/*
	 * 展示名称
	 */
	@Column(length = 64)
	@Size(min=2,max=64)
	private String title;
	
	/*
	 * 默认交易模式
	 */
	@Enumerated(EnumType.STRING)
	private ConsumerPattern defaultConsumerPattern;
	
	/*
	 * 默认费率
	 */
	@DecimalMin("0")
	@Column(nullable = false)
	@NumberFormat(pattern = "##.####%")
	private BigDecimal defaultFeeRate = new BigDecimal(0);
	
	/*
	 * 可选交易类型
	 */
	@OneToMany(mappedBy = "poolTradingMode")
	@OrderBy("lastModified")
	@Where(clause="removed = 0")
	private List<PoolFeeRateInfo> feeRateInfos;
	
	
	/*
	 * 逻辑删除标记
	 */
	private boolean removed=false;
	
	
	/*
	 * 可继承容差系数
	 */
	@DecimalMin("0")
	@Column(nullable = false)
	private BigDecimal inheritableServiceFeeRate = new BigDecimal(1);
	
	/*
	 * 可继承交易模式
	 */
	@Column(nullable = false)
	@AttributeOverride(name = "strValue", column = @Column(name = "inheritable_trading_mode", length = 200))
	private InheritableTradingModeList inheritableTradingMode;
	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class InheritableTradingModeList extends UserDefineList<Long> {
		private static final long serialVersionUID = 6184614428401805340L;

		public InheritableTradingModeList() {
			super(",");
		}

		@Override
		@Lob
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}
	}
	
	@OneToMany(mappedBy="poolTradingMode", fetch = FetchType.LAZY)
	private Set<Pool> pools;
	
	
	
	

}
