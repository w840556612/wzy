package com.ipinyou.optimus.console.chargefunc.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.user.entity.User;

/**
 * 收费功能
 * @author wanglj
 *
 */

@Entity
@Table(name = "func_chargefunc")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ChargeFunc extends TimedEntity implements Auditable<ChargeFunc> ,java.io.Serializable{
  
  private static final long serialVersionUID = -1486420064524521421L;

@Override
  public String getEntityName() {
    return "收费功能";
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public ChargeFunc getOrig() {
    return null;
  }

  @Override
  public void setOrig(ChargeFunc t) {

  }
  
  /**
   * 功能名称
   */
  @Size(min = 2, max = 64)
  @Column(length = 64)
  private String funcName;
  
  /**
   * 试用期
   */
  @NotNull
  private int probation;
  
  /**
   * 缓冲期
   */  
  @NotNull
  private int cacheDay;
  
  /**
   * 代理折扣
   */
  @Column(precision = 19, scale = 2, nullable = false)
  private BigDecimal discount;
  
  /**
   * 授权次数
   */
  @NotNull
  private int accreditNum;
  
  /**
   * 功能描述
   */
  @Size(min = 2, max = 255)
  @Column(length = 255)
  private String description;
  
  /**
   * 创建人
   */
  @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "creator_id", nullable = false, unique = true)
  private User creator;
  
  @Column(insertable = false, updatable = false, nullable = true)
  private Long creatorId;
  
  /**
   * 已删除
   */
  @NotNull
  @Column(nullable = false)
  private boolean removed = false;
  
  @OneToMany(mappedBy="chargefunc", fetch = FetchType.LAZY)
  private List<ChargeFuncPrice> pricelst = new ArrayList<ChargeFuncPrice>();
}
