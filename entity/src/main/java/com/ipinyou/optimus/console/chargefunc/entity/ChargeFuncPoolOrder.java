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
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.user.entity.Pool;
import com.ipinyou.optimus.console.user.entity.User;

/**
 * 代理商功能订单
 * @author wanglj
 *
 */
@Entity
@Table(name = "func_pool_order")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ChargeFuncPoolOrder extends TimedEntity implements Auditable<ChargeFuncPoolOrder> ,java.io.Serializable{ 
  
  private static final long serialVersionUID = -7284562368388531678L;

  @Override
  public String getEntityName() {
    return "代理商功能订单";
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public ChargeFuncPoolOrder getOrig() {
    return null;
  }

  @Override
  public void setOrig(ChargeFuncPoolOrder t) {
    
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
  
  public enum PoolOrderStatus {
    PROBATION("正试用"), PURCHASED("已购买"), EXPIRED("已过期"), NOPURCHASED(
        "未购买"),REBACKED("已退货");
    private String text;

    private PoolOrderStatus(String text) {
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
  private PoolOrderStatus status;
  
  /**
   * 内部使用帐号
   */
  @Size(min = 0, max = 255)
  @Column(length = 255)
  private String innerAccount;
  
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
  private int remainCacheDay;
  
  /**
   * 剩余授权次数，不保存
   */
  @Transient
  private int remainAccreditNum=0;
  
  /**
   * 购买时的授权次数
   */
  private int accreditNum;
 
  /**
   * 创建人
   */
  @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "creator_id", nullable = false, unique = true)
  private User creator;
  
  @Column(insertable = false, updatable = false, nullable = false)
  private Long creatorId;
  
  /**
   * 已删除
   */
  @NotNull
  @Column(nullable = false)
  private boolean removed = false;
  
}
