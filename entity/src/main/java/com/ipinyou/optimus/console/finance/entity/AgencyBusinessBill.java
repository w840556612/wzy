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
import com.ipinyou.optimus.console.user.entity.Pool;

/**
 * @author yaohl
 *
 */
@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AgencyBusinessBill  extends TimedEntity implements Auditable<AgencyBusinessBill>{/**
	 * 
	 */
	private static final long serialVersionUID = 7444513697578497451L;

	@Override
	public String getEntityName() {
		return "代理商商务订单";
	}

	/**
     * 池
     */
    @ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "pool_id", nullable = false)
    private Pool pool;

    @Column(insertable = false, updatable = false, nullable = false)
    private Long poolId;
    
    /**
     * 交易金额
     */
    @NotNull
    private BigDecimal tradeAmount;
    
    /**
     * 账单类型类型:广告主充值,代理商分配
     */
    public static enum BusinessBillType {
        AdvertiserRecharge("广告主充值"), AgencyDistribute("代理商分配");
        private String text;

        private BusinessBillType(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
    
    @NotNull
    @Column(nullable = false, length=50)
    @Enumerated(EnumType.STRING)
    private BusinessBillType billType;
    
    
    /**
     * 操作类型:线上充值,线下充值,退款,赠送,分配
     */
    public static enum RechargeType {
        OnlinePay("线上充值"), OfflinePay("线下充值");
        private String text;

        private RechargeType(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
    
    @Column(nullable = true, length=50)
    @Enumerated(EnumType.STRING)
    private RechargeType rechargeType;
    
    @Column(nullable = false, length=50)
    @Enumerated(EnumType.STRING)
    private OperateType operateType;
	
    /**
	 * 分配目标广告主
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.LAZY)
    @JoinColumn(name = "advertiser_id", nullable = false)
	private Advertiser advertiser;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserId;
	
	/**
	 * 消耗信用额度
	 */
	private BigDecimal costQuota;

	/**
	 * 订单编号
	 */
	@Size(max=30)
	@Column(length = 64)
	private String billNo;
	
	/*
     * (non-Javadoc)
     * 
     * @see com.ipinyou.base.entity.Auditable#getName()
     */
    @Override
    public String getName() {
        return pool.getName();
    }

    @Transient
    private AgencyBusinessBill orig;

}
