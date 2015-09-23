package com.ipinyou.optimus.console.thirdmonitoring.entity;

import java.sql.Timestamp;

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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ipinyou.base.constant.DateFormat;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Advertiser;
import com.ipinyou.optimus.console.third.model.ThirdPlatform;
import com.ipinyou.optimus.console.user.entity.Pool;
import com.ipinyou.optimus.console.user.entity.User;

/**
 * 收费功能
 * @author wanglj
 *
 */

@Entity
@Table(name = "third_monitoring_account")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ThirdMonitoring extends TimedEntity implements Auditable<ThirdMonitoring> ,java.io.Serializable{
  
  private static final long serialVersionUID = -1486420064524521421L;

@Override
  public String getEntityName() {
    return "第三方监测帐号申请";
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public ThirdMonitoring getOrig() {
    return null;
  }

  @Override
  public void setOrig(ThirdMonitoring t) {

  }
  
  /**
   * 广告主
   */
  @ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
  @JoinColumn(name = "advertiser_id", nullable = true)
  private Advertiser advertiser;
  
  @Column(insertable = false, updatable = false, nullable = false)
  private Long advertiserId;
  
  /**
   * 池
   */
  @ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
  @JoinColumn(name = "pool_id", nullable = false)
  private Pool pool;

  @Column(insertable = false, updatable = false, nullable = false)
  private Long poolId;
  

  /**
   * 第三方监测平台
   */
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length=50)
  private ThirdPlatform platform;
  
  public enum AuditStatus {
    COMMIT("待审核"), APPROVED("审核通过"), NOAPPROVED("审核未通过");
    private String text;

    private AuditStatus(String text) {
      this.text = text;
    }

    public String getText() {
      return text;
    }
  }

  /**
   * 审核状态
   */
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length=50)
  private AuditStatus status;
   
  /**
   * 第三方子帐号名
   */
  @Size(min = 2, max = 255)
  @Column(length = 255)
  private String thirdAccount;
  
  /**
   * 第三方子帐号密码
   */
  @Transient
  @Size(min = 2, max = 255)
  private String thirdAccountConfirm;
  
  /**
   * 审核通过账号
   */
  @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "audit_user_id", nullable = false, unique = true)
  private User auditUser;
  
  @Column(insertable = false, updatable = false, nullable = true)
  private Long audit_user_id;
  
  /**
   * 审核时间
   */
  @JsonFormat(pattern = DateFormat.TIMESTAMP, timezone="GMT+8")
  @Column(updatable = true, nullable = true)
  protected Timestamp auditTime;
  
  /**
   * 拒绝原因
   */
  @Size(min = 2, max = 255)
  private String rejectResaon;
  
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
}
