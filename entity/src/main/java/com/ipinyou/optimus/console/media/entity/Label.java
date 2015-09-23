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
import javax.validation.constraints.Size;

import org.hibernate.annotations.Index;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.entity.listener.PinyinListener;
import com.ipinyou.base.entity.listener.TimedEntityListener;

/**
 * 媒体管理-栏目表
 * @author guodong.zhang
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@JsonIgnoreProperties(value = { "version", "lastModified", "creator" })
@EntityListeners({ PinyinListener.class })
public class Label extends TimedEntity implements Auditable<Label> {

	private static final long serialVersionUID = 772828421466273596L;

	/**
	 * 名称
	 */
	@Size(min = 2, max = 64)
	@Column(length = 64)
	@NotNull
	@Index(name = "name")
	private String name;
	
	/**
	 * 名称
	 * 名称拼音模糊查询辅助字段 add by wujun 20131107
	 */
	@Size(max = 1000)
	private String namePinyin;
	
	/**
	 * 所属网站ID
	 */
	@Column(insertable = false, updatable = false, nullable = false)
	private Long websiteId;
	
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "website_id", nullable = false)
	private Website website;
	
	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;
	
	@Transient
	private Label orig;
	
	@Override
	@Transient
	public String getEntityName() {
		return "栏目";
	}

}
