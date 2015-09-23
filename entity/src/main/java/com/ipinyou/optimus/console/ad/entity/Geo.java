/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ipinyou.base.entity.listener.PinyinListener;

/**
 * 区域,支持多个级别，例如国家、省、地级市
 * 
 * @author lijt
 * 
 */
@Entity
@Data
@JsonIgnoreProperties(value={"parent","parentId","levelName","pinyin","removed","namePinyin","hibernateLazyInitializer", "handler"})
@ToString(exclude = { "parent", "children" })
@EqualsAndHashCode(exclude = { "parent", "children" })
@EntityListeners({ PinyinListener.class })
public class Geo implements com.ipinyou.base.entity.Entity, Comparable<Geo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 818581857971726814L;

	/**
	 * 主键
	 */
	@Id
	@Index(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Short id;

	@Index(name="name")
	@Column(nullable = false, length = 45)
	private String name;
	
	private String nameEn;
	
	public String getName() {
		return com.ipinyou.base.util.I18nResourceUtil.getContent(this.getClass(), this, "name",name);
	}
	
	

	@Size(max = 1000)
	private String namePinyin;
	/**
	 * 序号，同一父节点下，各子节点按照seqNum从小到大顺序排列
	 */
	@Index(name="seqNum")
	private int seqNum;

	@Column(length = 64)
	private String pinyin;

	@NotNull
	@Index(name="zone_id")
	private int zoneId;
	
//	/**
//	 * 级别名称，1为国家，2为省/州，3为自治区
//	 */
	//private int level;

	/**
	 * 级别名称，例如省、直辖市、自治区
	 */
	@Column(nullable = false, length = 45)
	private String levelName;
	
	private String levelNameEn;
	
	public String getLevelName() {
		return com.ipinyou.base.util.I18nResourceUtil.getContent(this.getClass(), this, "levelName",levelName);
	}
	
	@ManyToOne(cascade = { CascadeType.REFRESH },fetch=FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Geo parent;

	@Index(name="parentId")
	@Column(insertable = false, updatable = false, nullable = true)
	private Short parentId;
	
	@OneToMany(mappedBy = "parent",fetch=FetchType.LAZY)
	@OrderBy("seqNum,name")
	private Set<Geo> children;

	@NotNull
	@Column(nullable = false)
	@Index(name="removed")
	private boolean removed = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Geo o) {
		return seqNum > o.seqNum ? 1 : (seqNum < o.seqNum) ? -1 : 0;
	}

}
