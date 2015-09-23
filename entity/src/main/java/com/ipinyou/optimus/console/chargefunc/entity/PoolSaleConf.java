package com.ipinyou.optimus.console.chargefunc.entity;

import java.math.BigDecimal;

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
import com.ipinyou.optimus.console.user.entity.Pool;
import com.ipinyou.optimus.console.user.entity.User;

/**
 * 代理商售卖设置
 * @author wanglj
 *
 */
@Entity
@Table(name = "func_pool_sale_conf")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PoolSaleConf extends TimedEntity implements Auditable<PoolSaleConf> ,java.io.Serializable{ 
  
  private static final long serialVersionUID = 3908250623577326492L;

@Override
  public String getEntityName() {
    return "代理商售卖设置";
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public PoolSaleConf getOrig() {
    return null;
  }

  @Override
  public void setOrig(PoolSaleConf t) {
    
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
   * 是否开启售卖
   */
  private boolean active = false;
  
  public enum DiscountType {
    GENERAL("通用折扣"), SPECIAL("单独折扣");
    private String text;

    private DiscountType(String text) {
      this.text = text;
    }

    public String getText() {
      return text;
    }
  }

  /**
   * 折扣类型
   */
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length=50)
  private DiscountType discountType;
  
  /**
   * 代理折扣
   */
  @Column(precision = 19, scale = 2, nullable = true)
  private BigDecimal discount;
  
  /**
   * 创建人
   */
  @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "creator_id", nullable = false, unique = true)
  private User creator;
  
  @Column(insertable = false, updatable = false, nullable = false)
  private Long creatorId;
  
  
  /**
   * 是否有申请中的订单，不保存
   */
  @Transient
  private boolean hasapplyingorder;
  
  /**
   * 已删除
   */
  @NotNull
  @Column(nullable = false)
  private boolean removed = false;
}
