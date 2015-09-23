package com.ipinyou.optimus.console.finance.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

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
import com.ipinyou.optimus.console.finance.entity.AdvertiserBillInfo.RechargeType;

/**
 * 充值
 * 
 * @author yhl
 * 
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
public class PayOrder extends TimedEntity implements Auditable<PayOrder> {
	/**
     * 
     */
	private static final long serialVersionUID = 7480918850357491784L;

	@Override
	public String getEntityName() {
		return "支付订单";
	}

//	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id", nullable = false)
//	private User user;
//
//	@Column(insertable = false, updatable = false, nullable = false)
//	private Long userId;

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch=FetchType.LAZY)
	@JoinColumn(name = "advertiser_id", nullable = false)
	private Advertiser advertiser;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserId;
	
	/**
	 * 充值的银行名称
	 */
	@Column(length = 64)
	private String bankName;

	/**
	 * 外部订单号
	 */
	@Column(length = 64)
	private String outTradeNo;

	/**
	 * 订单号
	 */
	@Column(length = 64)
	private String tradeNo;

	/**
	 * 用户交易金额
	 */
	@NotNull
	@Column(nullable = false)
	private BigDecimal tradeAmount;

	@NotNull
	@Column(nullable = false, length=50)
	@Enumerated(EnumType.STRING)
	private RechargeType rechargeType = RechargeType.AliPay;

	/**
	 * 备注
	 */
	private String remark;

	public enum TradeStatus {
		TRADE_SUCCESS("付款成功"), TRADE_FAILD("付款失败"), TRADE_CLOSED("付款关闭"), WAIT_BUYER_PAY(
				"等待付款");
		private String text;

		private TradeStatus(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

	/**
	 * 充值状态
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length=50)
	private TradeStatus status = TradeStatus.WAIT_BUYER_PAY;

	/**
	 * 操作日期
	 */
	@Column(nullable = false)
	private Timestamp day = new Timestamp(new Date().getTime());

	@Transient
	private PayOrder orig;

	@Override
	@Transient
	public String getName() {
		return tradeNo;
	}
	
	@Transient
	private Boolean presentFlag=false;
	
	@Transient
	private BigDecimal presentRate = new BigDecimal("0.00"); 
	
	@Transient
	private String remarkTemp;


}
