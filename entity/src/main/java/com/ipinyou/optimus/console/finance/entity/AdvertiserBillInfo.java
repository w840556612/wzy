package com.ipinyou.optimus.console.finance.entity;

import java.math.BigDecimal;
import java.sql.Date;

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
import com.ipinyou.optimus.console.ad.entity.Advertiser;
import com.ipinyou.optimus.console.user.entity.Invoice;

/**
 * @author yaohl
 *
 */
@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AdvertiserBillInfo extends TimedEntity implements Auditable<AdvertiserBillInfo>{

    /**
     * 
     */
    private static final long serialVersionUID = -4993242234710444323L;
    
    @Override
    public String getEntityName() {
        return "广告主账单信息";
    }
    
    public AdvertiserBillInfo() {
        
    }
    
    public AdvertiserBillInfo(Long advertiserId,BigDecimal tradeAmount,java.util.Date day) {
        this.advertiserId = advertiserId;
        this.tradeAmount = tradeAmount;
        this.day = new Date(day.getTime());
        
    }
    
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "advertiser_id", nullable = false)
	private Advertiser advertiser;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserId;
	
	
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "invoice_id", nullable = true)
	private Invoice invoice;
	@Column(insertable = false, updatable = false, nullable = true)
	private Long invoiceId;

    /**
     * 充值的银行名称
     */
    @Column(length = 64)
    private String bankName;

    /**
     * 充值交易号码
     */
    @Column(length = 64)
    private String tradeNo;

    /**
     * 用户交易金额
     */
    @NotNull
    private BigDecimal tradeAmount;

    /**
     * 索取发票状态 
     */
    public static enum InvoiceStatus{
        UnDemand("未索取"),Processing("待处理"),Sended("已发出"),Received("已接收");
        private String text;

        private InvoiceStatus(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    } 
    @Column(nullable=true,length=50)
    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;
    
    /**
     * 充值类型 0-线下;1-网银;2-支付宝;3-快钱
     */
    public static enum RechargeType {
        OffLine("线下"), EBank("网银"), AliPay("支付宝"); // 顺序固定，不能调整
        
        private String text;

        private RechargeType(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
        
    }
    @Column(nullable=true,length=50)
    @Enumerated(EnumType.STRING)
    private RechargeType rechargeType;
    
    /**
     * 账单类型类型:充值,消费
     */
	public static enum OperateType {
		Recharge("充值"), ThirdMonitoringCost("第三方监测费用"), ChargeFuncCost(
				"单功能购买消费"), ChargeFuncRefund("单功能购买退款"), Cost("投放消费"), Present(
				"赠送"), Distribute("分配"), Refund("普通退款");
		private String text;

		private OperateType(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
    
    @NotNull
    @Column(nullable=false,length=50)
    @Enumerated(EnumType.STRING)
    private OperateType operateType;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 账户余额
     */
    private BigDecimal balance;
    
    /**
     * 控制是否显示
     */
    @NotNull
    @Column(nullable = false)
    private boolean confirm=false;
    
    /**
     * 是否为首充
     */
    @NotNull
    @Column(nullable = false)
    private boolean firstRecharge=false;
    
    /**
     * 操作日期
     */
    @NotNull
    private Date day;
    
    
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
    private AdvertiserBillInfo orig;
    
	@Transient
	private String userType;
}
