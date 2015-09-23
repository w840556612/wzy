/**
 * 
 */
package com.ipinyou.optimus.console.sys.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.BaseEntity;
import com.ipinyou.base.model.ClientInfo;

/**
 * 操作日志
 * @author lijt
 * 
 */
@Entity
@Table(name = "sys_operation_log")
@Data
@ToString(callSuper = true, exclude = { "fieldLogs" })
@EqualsAndHashCode(callSuper = true, exclude = { "fieldLogs" })
public class OperationLog extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3009860309424544066L;

	public static enum LogType {
		Login("登录"), Register("注册"), Create("创建"), Update("修改"), Remove("删除"), ViewReport(
				"查看报表"), Visitor("我的访客");// 我的访客
		private String text;

        private LogType(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
	}

	public static enum Result {
		Success, Failure
	}

	@Column(length = 40, nullable = true)
	private String pyid;

	/**
	 * ipinyou.com广告网络中产生的会话id
	 */
	@Column(length = 40, nullable = true)
	private String sessionId;

	@Column(length = 200, nullable = true)
	private String ip;

	@Column(length = 2000, nullable = true)
	private String userAgent;
	
	@Column(length = 2000, nullable = true)
	private String referrerUrl;
	
	/**
	 * 来源，__utmz或者到达站点第一个页面的refer url，为空表示直接输入地址
	 */
	@Column(length = 2000, nullable = true)
	private String source;
	
	/**
	 * 操作者姓名
	 */
	@Column(length = 45, nullable = true)
	private String operatorName;

	/**
	 *  操作者id/对应用户表的主键
	 */
	@Column(length = 40, nullable = true)
	private String operatorId;

	/**
	 * 登录id，一般为JSESSIONID
	 */
	@Column(length = 255, nullable = true)
	private String loginId;

	/**
	 * 操作发生时刻 
	 */
	@Column(nullable = false)
	private Timestamp timestamp = new Timestamp(new Date().getTime());

	/**
	 * 日志类型/或者操作类型
	 */
	@Column(length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private LogType type;

	@Lob
	@Column(name = "description", nullable = true)
	private String desc;

	/**
	 * 操作结果，默认为操作成功
	 */
	@Column(length = 50, nullable = true)
	@Enumerated(EnumType.STRING)
	private Result result = Result.Success;

	/**
	 * 操作的实体类名
	 */
	@Column(length = 500, nullable = true)
	private String entityCls;

	/**
	 * 操作目标，对应 Auditable.getEntityName
	 */
	@Column(length = 500, nullable = true)
	private String target;

	/**
	 * 操作目标id，对应 Auditable.getPrimaryKey
	 */
	@Column(length = 200, nullable = true)
	private String targetId;

	/**
	 * 操作目标记录的名称，对应 Auditable.getName
	 */
	@Column(length = 500, nullable = true)
	private String targetName;

	/**
	 * 字段详细变更记录
	 */
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REMOVE }, mappedBy = "log")
	private Set<FieldLog> fieldLogs = new HashSet<FieldLog>();

	public OperationLog() {
		super();
	}

	public OperationLog(ClientInfo client) {
		super();
		this.pyid = client.getPyid();
		this.sessionId = client.getSessionId();
		this.ip = client.getIp();
		setUserAgent(client.getUserAgent());
		setReferrerUrl(client.getReferrerUrl());
		setSource(client.getSource());
		this.operatorName = client.getName();
		this.operatorId = client.getUserId();
		this.loginId = client.getLoginId();
	}

	public void setAuditInfo(Auditable<?> a) {
		this.entityCls = a.getClass().getName();
		this.targetId = a.getPrimaryKey();
		this.targetName = a.getName();
		this.target = a.getEntityName();
	}
	
	private String limitStr(String str){
		if(str==null){
			return null;
		}
		if(str.length()<=2000){
			return str;
		}else{
			return str.substring(0,2000);
		}
	}
	
	public void setUserAgent(String userAgent) {
		this.userAgent = limitStr(userAgent);
	}

	public void setReferrerUrl(String referrerUrl) {
		this.referrerUrl = limitStr(referrerUrl);
	}
	
	public void setSource(String source){
		this.source = limitStr(source);
	}
}
