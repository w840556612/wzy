package com.ipinyou.optimus.console.media.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Index;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.constant.Regex;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.entity.listener.PinyinListener;
import com.ipinyou.optimus.console.user.entity.User;

/**
 * 媒体管理-渠道表
 * @author guodong.zhang
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@JsonIgnoreProperties(value = { "version", "lastModified", "creator" })
@EntityListeners({ PinyinListener.class })
public class Channel extends TimedEntity implements Auditable<Channel> {

	private static final long serialVersionUID = 3767964028167914426L;

	/**
	 * 渠道名称
	 */
	@Size(min = 2, max = 64)
	@Column(length = 64)
	@NotNull
	@Index(name = "name")
	private String name;
	
	/**
	 * 渠道名称
	 * 渠道名称拼音模糊查询辅助字段 add by wujun 20131107
	 */
	@Size(max = 10000)
	private String namePinyin;
	
	
	/**
	 * 创建者
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id", nullable = false, unique = true)
	private User creator;

	@Column(insertable = false, updatable = false, nullable = false)
	private Long creatorId;

	/**
	 * 联系人邮箱
	 */
	@NotNull
	@Size(min = 3, max = 50)
	@Pattern(regexp = Regex.EMAIL)
	@Column(nullable = false, length = 100)
	@Index(name = "email")
	private String email;

	/**
	 * 联系方式
	 */
	@NotNull
	@Size(min = 11, max = 32)
	@Column(nullable = false, length = 32)
	private String cellphone;
	
	/**
	 * 联系人名称
	 */
	@NotNull
	@Size(min = 2, max = 45)
	@Column(length = 45)
	private String contactName;
	
	/**
	 * 联系人名称
	 * 联系人名称拼音模糊查询辅助字段 add by wujun 20131107
	 */
	@Size(max = 10000)
	private String contactNamePinyin;
	
	
	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;
	
	@Transient
	private Channel orig;
	
	@Override
	@Transient
	public String getEntityName() {
		return "渠道";
	}

}
