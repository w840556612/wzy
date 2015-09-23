package com.ipinyou.optimus.console.finance.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Advertiser;
import com.ipinyou.optimus.console.finance.entity.AdvertiserBillInfo.OperateType;
import com.ipinyou.optimus.console.finance.entity.AgencyBusinessBill.RechargeType;

/**
 * @author yaohl
 *
 */
@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdvertiserBusinessBill extends TimedEntity implements Auditable<AdvertiserBusinessBill>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -313012508169010622L;

	/**
     * 交易金额
     */
    @NotNull
    private BigDecimal tradeAmount;
    
    /**
	 * 分配目标广告主
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.LAZY)
    @JoinColumn(name = "advertiser_id", nullable = false)
	private Advertiser advertiser;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserId;

	/**
	 * 订单编号
	 */
	@Size(max=30)
	@Column(length = 64)
	private String billNo;
	
	/**
	 * 订单编号前缀
	 */
	@Size(max=30)
	@Column(length = 64)
	private String billNoPrefix;
	
	/**
	 * 订单类型
	 */
	@Column(nullable=true,length=50)
    @Enumerated(EnumType.STRING)
	private OperateType operateType;
	
	@Column(nullable=true,length=50)
    @Enumerated(EnumType.STRING)
	private RechargeType rechargeType;
	/*
     * (non-Javadoc)
     * 
     * @see com.ipinyou.base.entity.Auditable#getName()
     */
    @Override
    public String getName() {
        return advertiser.getName();
    }

    @Transient
    private AdvertiserBusinessBill orig;

	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return "广告主商务订单";
	}
}
