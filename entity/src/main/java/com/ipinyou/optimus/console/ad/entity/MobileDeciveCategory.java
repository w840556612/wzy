package com.ipinyou.optimus.console.ad.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@Table(name = "mobile_decive")
@JsonIgnoreProperties(value={"parent","parentId"})
@ToString(exclude = { "parent", "children" })
@EqualsAndHashCode(exclude = { "parent", "children" })
public class MobileDeciveCategory implements com.ipinyou.base.entity.Entity, Comparable<MobileDeciveCategory> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2288388942299285289L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 45)
	private String name;
	
	private String nameEn;
	
	public String getName() {
		return com.ipinyou.base.util.I18nResourceUtil.getContent(this.getClass(), this, "name",name);
	}
	
	/**
	 * 序号，同一父节点下，各子节点按照seqNum从小到大顺序排列
	 */
	private int seqNum;
	
	@Column(nullable = false, length=20)
	private String categoryId;
	
	@ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id")
	private MobileDeciveCategory parent;	
	
	@Index(name="parentId")
	@Column(insertable = false, updatable = false, nullable = true)
	private String parentId;
	
	@OneToMany(mappedBy="parent",fetch=FetchType.EAGER)
	@Where(clause="removed = 0")
	@OrderBy("seqNum,categoryId")
	private Set<MobileDeciveCategory> children;
	
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;
	
	private String path;
	
	
	private boolean father;
	
	
	/**
	 * 节点名称路径
	 */
	@Column(length = 500)
	private String rawData;
	
	private String rawDataEn;
	
	public String getRawData() {
		return com.ipinyou.base.util.I18nResourceUtil.getContent(this.getClass(), this, "rawData",rawData);
	}
	
	
	@Override
	public int compareTo(MobileDeciveCategory o) {
		return seqNum > o.seqNum ? 1 : (seqNum < o.seqNum ? -1 : 0);
	}
}
