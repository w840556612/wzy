package com.ipinyou.base.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 客户端信息
 * @author lijt
 *
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ClientInfo extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6115093182725840231L;

	private String pyid;

	private String sessionId;

	private String ip;

	private String userAgent;

	private String referrerUrl;
	
	private String source;
	
	private String userId;

	private String name;

	/**
	 * 在SNA架构中，使用cookie实现登录时，标识一个登录的id
	 */
	private String loginId;

	private Date loginTime;

	public ClientInfo() {
		super();
	}

	public ClientInfo(String pyid, String sessionId, String ip, String userAgent, String referrerUrl, String source) {
		super();
		this.pyid = pyid;
		this.sessionId = sessionId;
		this.ip = ip;
		this.userAgent = userAgent;
		this.referrerUrl = referrerUrl;
		this.source = source;
	}

	public ClientInfo(String pyid, String sessionId, String ip,
			String userAgent, String referrerUrl, String source, String userId, String name, String loginId,
			Date loginTime) {
		super();
		this.pyid = pyid;
		this.sessionId = sessionId;
		this.ip = ip;
		this.userAgent = userAgent;
		this.referrerUrl = referrerUrl;
		this.userId = userId;
		this.name = name;
		this.loginId = loginId;
		this.loginTime = loginTime;
		this.source = source;
	}

}
