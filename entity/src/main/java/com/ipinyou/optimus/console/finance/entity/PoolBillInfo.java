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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.user.entity.Pool;
import com.ipinyou.optimus.console.user.entity.User;

@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PoolBillInfo  extends TimedEntity implements Auditable<PoolBillInfo>{/**
     * 
     */
    private static final long serialVersionUID = -3175963409629139066L;

    @Override
    public String getEntityName() {
        return "代理商账单信息";
    }

    public PoolBillInfo() {}
    
    public PoolBillInfo(Long id, BigDecimal tradeAmount) {
        this.poolId = id;
        this.tradeAmount = tradeAmount;
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
     * 用户交易金额
     */
    @NotNull
    private BigDecimal tradeAmount;
    
    /**
     * 账单类型类型:充值,消费
     */
    public static enum BillType {
		Cost("普通消费"), ChargeFuncCost("单功能购买消费"), ChargeFuncRefund("单功能购买退款"), Repayment("充值"), Refund("普通退款"), Present(
				"赠送"), ChargeFuncSold("单功能出售");
        private String text;

        private BillType(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
    
    @NotNull
    @Column(nullable = false, length=50)
    @Enumerated(EnumType.STRING)
    private BillType operateType;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 账户余额
     */
    private BigDecimal balance;
    
	/**
	 * 执行用户
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "executor_id", nullable = false)
	private User executor;

	@Column(insertable = false, updatable = false, nullable = true)
	private Long executorId;
    
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
    private PoolBillInfo orig;

    @Transient
    private String billDate;
    
    @Transient
    private String payEndDate;
    
    @Transient
    private String payPassword;
}
