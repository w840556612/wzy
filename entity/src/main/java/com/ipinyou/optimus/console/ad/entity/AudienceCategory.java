/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 人群属性标签
 * 
 * @author lijt
 * 
 */
@Entity
@Data
@ToString(exclude={"parent","children"})
@EqualsAndHashCode(exclude = { "children", "parent" })
@JsonIgnoreProperties(value={"parent","removed","level","seqNum","path","type","rawData","curVersion","lastVersion","hibernateLazyInitializer","handler"})
public class AudienceCategory implements com.ipinyou.base.entity.Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4033409685534749695L;
	/**
	 * 主键
	 */
	@Id
	@Index(name="id")	
	protected Integer id;
	/**
	 * 节点名称
	 */
	@Column(nullable = false, length = 64)
	private String name;
	
	private String nameEn;
	
	public String getName() {
		return com.ipinyou.base.util.I18nResourceUtil.getContent(this.getClass(), this, "name",name);
	}

	@ManyToOne(cascade = { CascadeType.REFRESH },fetch=FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private AudienceCategory parent;
	/**
	 * 父级节点Id
	 */
	@Index(name="parentId")	
	@Column(insertable = false, updatable = false, nullable = true)
	private Integer parentId;

	@OneToMany(mappedBy = "parent")
	@Where(clause="removed = 0 and visible = 1")
	@OrderBy("seqNum,name")
	private Set<AudienceCategory> children;

	/**
	 * 是否父级标签
	 */
	private boolean father;

	/**
	 * 节点名称路径,例如: 个人关注/资讯、新闻/各行业资讯/法律
	 */
	@Column(length = 500)
	private String rawData;
	
	private String rawDataEn;
	
	public String getRawData() {
		return com.ipinyou.base.util.I18nResourceUtil.getContent(this.getClass(), this, "rawData",rawData);
	}

	/**
	 * 删除状态标示
	 */
	@Index(name="removed")
	private boolean removed = false;

	/**
	 * 节点Id路径,例如: 10005|10006|10016|10018
	 */
	@Column(length = 100)
	private String path;

	/**
	 * 级别,根路径为1,往下依次递增
	 */
	private int level;
	/**
	 * 类型字段,保留字段
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private AudienceType type;

	/**
	 * 排序字段
	 */

	@Index(name="seqNum")
	private int seqNum;

	/**
	 * 是否可选择
	 */
	@Column(nullable = false)
	private boolean choosable = true;

	/**
	 * 正则化系数
	 */
	private BigDecimal ratio = new BigDecimal(1.0);
	
	/**
	 * 保留字段，用于记录版本信息
	 */
	@Column(length = 64)
	private String curVersion;
	
	/**
	 * 保留字段，用于记录版本信息
	 */
	@Column(length = 64)
	private String lastVersion;
	
	
	public static enum AudienceType {
		Optimus,
		Baidu,
		Miaozhen,
		Mobile;
	}
	
	/**
	 * 是否可见
	 */
	private boolean visible;

	public AudienceCategory(){}
	public AudienceCategory(Integer id,Integer parentId,String name){
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.visible = true;
		this.children = new LinkedHashSet<AudienceCategory>();
	}
	
}
