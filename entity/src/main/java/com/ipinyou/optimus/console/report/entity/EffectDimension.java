package com.ipinyou.optimus.console.report.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.dynamiccreative.entity.AdvertiserTemplatePropsRel.TemplateStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "rpt_effect_dimension")
@Data
@ToString(callSuper = true, exclude = { "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@JsonIgnoreProperties(value = { "userId", "removed", "entityName", "version", "creation", "lastModified", "orig" })
public class EffectDimension extends TimedEntity implements Auditable<EffectDimension> {

	private static final long serialVersionUID = 7914889210791697868L;

	@Override
	public String getEntityName() {
		return "广告报表维度";
	}

	/**
	 * 用户ID
	 */
	@Index(name = "userId")
	@NotNull
	@Column(nullable = false)
	private Long userId;

	/**
	 * 名称，例如广告主-计划
	 */
	@NotNull
	@Size(min = 1, max = 100)
	@Column(nullable = false, length = 100)
	private String name;
	
	/**
	 * 节点
	 */
	@NotNull
	@Size(min = 1, max = 150)
	@Column(nullable = false, length = 150)
	private String nodes;

	/**
	 * 描述，例如广告主-计划
	 */
	@NotNull
	@Size(min = 1, max = 100)
	@Column(nullable = false, length = 100)
	private String description;
	
	@JsonIgnore
	public List<String> getDescriptionAttr() {
		String[] descriptions = StringUtils.split(description, "-");
		List<String> list = new ArrayList<>();

		for (String d : descriptions) {
			list.add(d);
		}

		return list;
	}
//	
//	/**
//	 * 要查询的id
//	 */
//	@NotNull
//	@Size(min = 1, max = 200)
//	@Column(nullable = false, length = 200)
//	private String idProperties;
//	
//	/**
//	 * 要查询的name
//	 */
//	@NotNull
//	@Size(min = 0, max = 200)
//	@Column(nullable = true, length = 200)
//	private String nameProperties;
//	
//	/**
//	 * 要查询的url
//	 */
//	@Size(min = 0, max = 100)
//	@Column(length = 100)
//	private String urlProperties;
//
//	/**
//	 * 要连接的实体名，例如Advertiser advertiser,Campaign campaign
//	 */
//	@Size(min = 1, max = 300)
//	@Column(nullable = true, length = 300)
//	private String joinEntities;
//
//	/**
//	 * 连接条件，例如and r.advertiserId=advertiser.id
//	 */
//	@Size(min = 1, max = 500)
//	@Column(nullable = true, length = 500)
//	private String joinConditions;
	
	/**
	 * 是否默认
	 */
	private boolean defaulted;

	/**
	 * 是否删除
	 */
	private boolean removed;
	
	/**
	 * 区分pdb报表使用还是其它地方使用
	 */
	private String type;
	
		
	@Transient
	private EffectDimension orig;
	
}
