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
@Table(name = "mobile_audiences")
@JsonIgnoreProperties(value={"parent","parentId"})
@ToString(exclude = { "parent", "children" })
@EqualsAndHashCode(exclude = { "parent", "children" })
public class MobileAudiences implements com.ipinyou.base.entity.Entity, Comparable<MobileAudiences> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2288388942299285289L;
	
	@Id
	@Index(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 45)
	private String name;
	
	/**
	 * 序号，同一父节点下，各子节点按照seqNum从小到大顺序排列
	 */
	@Index(name="seqNum")
	private int seqNum;
	
	@Index(name="appCode")
	@Column(nullable = false, length=64)
	private String code;
	
	@ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
//	@JoinColumn(name = "parent_id")
	@JoinColumn(name = "parent_id", referencedColumnName = "code") 
	private MobileAudiences parent;	
	
	@Index(name="parentId")
	@Column(insertable = false, updatable = false, nullable = true)
	private String parentId;
	
	@OneToMany(mappedBy="parent", fetch = FetchType.LAZY)
	@Where(clause="removed = 0")
	@OrderBy("seqNum,code")
	private Set<MobileAudiences> children;
	
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;
	
	
	@Override
	public int compareTo(MobileAudiences o) {
		return seqNum > o.seqNum ? 1 : (seqNum < o.seqNum ? -1 : 0);
	}
}
