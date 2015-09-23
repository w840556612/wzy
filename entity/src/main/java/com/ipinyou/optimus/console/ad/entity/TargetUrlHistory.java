/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.model.ClientInfo;

/**
 * 到达地址变更历史
 * 
 * @author lijt
 * 
 */
@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TargetUrlHistory extends TimedEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1887099213386503586L;

	/**
	 * 操作用户id
	 */
	@Column(length = 40, nullable = false)
	private String operatorId;

	/**
	 * 操作用户姓名
	 */
	@Column(nullable = false, length = 64)
	private String operatorName;

	/**
	 * 操作目标,对应策略创意关联(StrategyCreativeRel)的id
	 */
	private Long relId;
	/**
	 * 操作目标所对应的策略Id
	 */
	private Long strategyId;
	/**
	 * 操作目标所对应的创意Id
	 */
	private Long creativeId;
	/**
	 * 旧到达地址
	 */
	@Column(length = 2083)
	private String oldUrl;
	/**
	 * 新到达地址
	 */
	@Column(length = 2083)
	private String newUrl;

	// @Temporal(TemporalType.TIMESTAMP)
	/**
	 * 新地址生效时间 
	 */
	@Column(nullable = true)
	private Timestamp begin;
	/**
	 * 新地址失效时间 
	 */
	@Column(nullable = true)
	private Timestamp end;
	/**
	 * 已删除，如果因为到达地址错误而变更，需将相应的变更记录标记为删除
	 */
	private boolean removed = false;

	public void setClientInfo(ClientInfo info) {
		this.operatorId = info.getUserId();
		this.operatorName = info.getName();
	}

}
