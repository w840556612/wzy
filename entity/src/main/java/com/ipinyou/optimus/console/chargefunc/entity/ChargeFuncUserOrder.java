package com.ipinyou.optimus.console.chargefunc.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Advertiser;
import com.ipinyou.optimus.console.chargefunc.entity.PoolSaleConf.DiscountType;
import com.ipinyou.optimus.console.user.entity.Pool;
import com.ipinyou.optimus.console.user.entity.User;

/**
 * 用户功能订单
 * @author wanglj
 *
 */
@Entity
@Table(name = "func_user_order")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ChargeFuncUserOrder extends TimedEntity implements Auditable<ChargeFuncUserOrder> ,java.io.Serializable{ 
  
  private static final long serialVersionUID = 2629030484107002008L;

  @Override
  public String getEntityName() {
    return "用户功能订单";
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public ChargeFuncUserOrder getOrig() {
    return null;
  }

  @Override
  public void setOrig(ChargeFuncUserOrder t) {
    
  }
  
  /**
   * 收费功能
   */
  @ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
  @JoinColumn(name = "func_id", nullable = false)
  private ChargeFunc chargefunc;
  
  @Column(insertable = false, updatable = false, nullable = false)
  private Long funcId;
  
  /**
   * 池
   */
  @ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
  @JoinColumn(name = "pool_id", nullable = false)
  private Pool pool;

  @Column(insertable = false, updatable = false, nullable = false)
  private Long poolId;
  
  /**
   * 广告主
   */
  @ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
  @JoinColumn(name = "advertiser_id", nullable = true)
  private Advertiser advertiser;

  @Column(insertable = false, updatable = false, nullable = true)
  private Long advertiserId;
  
  /**
   * 用户
   */
  @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, unique = true)
  private User user;
  
  @Column(insertable = false, updatable = false, nullable = false)
  private Long userId;
  
  /**
   * 购买日期
   */
  private Date purchaseDate;
  
  /**
   * 价格
   */
  @Column(precision = 19, scale = 2)
  private BigDecimal price;
  
  /**
   * 天数
   */
  private int day;
  
  /**
   * 花费金额
   */
  @Column(precision = 19, scale = 2)
  private BigDecimal cost;
  
  public enum UserOrderStatus {
    APPLYING("申请中"),APPLYED("申请通过"),PROBATION("正试用"), PURCHASED("已购买"), EXPIRED("已过期"), NOPURCHASED(
        "未购买"),REBACKED("已退货");
    private String text;

    private UserOrderStatus(String text) {
      this.text = text;
    }

    public String getText() {
      return text;
    }
  }
  
  /**
   * 广告主折扣方式
   */
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length=50)
  private DiscountType discountType;
  
  /**
   * 申请折扣
   */
  @Column(precision = 19, scale = 2, nullable = true)
  private BigDecimal discount;

  /**
   * 状态
   */
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length=50)
  private UserOrderStatus status;
  
 
  /**
   * 购买时的试用期
   */
  private int probation;
  
  /**
   * 购买时的缓冲期
   */
  private int cacheDay;
  
  /**
   * 剩余天数，不保存
   */
  @Transient
  private int remainDay;
  
  /**
   * 剩余缓冲期天数，不保存
   */
  @Transient
  private int remainCacheDay=0;
  
  /**
   * 已删除
   */
  @NotNull
  @Column(nullable = false)
  private boolean removed = false;
  
}
