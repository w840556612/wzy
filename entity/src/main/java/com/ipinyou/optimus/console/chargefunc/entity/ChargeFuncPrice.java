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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.chargefunc.entity.ChargeFuncPoolOrder.PoolOrderStatus;
import com.ipinyou.optimus.console.user.entity.Pool;
import com.ipinyou.optimus.console.user.entity.User;

/**
 * 价格表
 * @author wanglj
 *
 */
@Entity
@Table(name = "func_price")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ChargeFuncPrice extends TimedEntity implements Auditable<ChargeFuncPrice> ,java.io.Serializable{ 
  
  private static final long serialVersionUID = 3908250623577326492L;

@Override
  public String getEntityName() {
    return "价格表";
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public ChargeFuncPrice getOrig() {
    return null;
  }

  @Override
  public void setOrig(ChargeFuncPrice t) {
    
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
   * 价格
   */
  @Column(precision = 19, scale = 2)
  private BigDecimal price;
  
  /**
   * 天数
   */
  private int day;
  
  /**
   * 价格串
   */
  @Size(min = 2, max = 64)
  @Column(length = 64)
  private String priceStr;
  
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
