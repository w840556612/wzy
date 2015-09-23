/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@JsonIgnoreProperties(value={"parent","parentId","removed"})
@ToString(exclude = { "parent", "children" })
@EqualsAndHashCode(exclude = { "parent", "children" })
public class VideoChannel implements com.ipinyou.base.entity.Entity, Comparable<VideoChannel> {

	private static final long serialVersionUID = -4705068549162194923L;

	/**
	 * 主键
	 */
	@Id
	@Index(name="id")
	@Column(length=32)
	protected String id;

	@Index(name="name")
	@Column(nullable = false, length = 45)
	private String name;
	
	private String nameEn;
	
	public String getName() {
		return com.ipinyou.base.util.I18nResourceUtil.getContent(this.getClass(), this, "name",name);
	}

	/**
	 * 序号，同一父节点下，各子节点按照seqNum从小到大顺序排列
	 */
	@Index(name="seqNum")
	private int seqNum;

	@ManyToOne(cascade = { CascadeType.REFRESH },fetch=FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private VideoChannel parent;

	@Index(name="parentId")
	@Column(length=32,insertable = false, updatable = false, nullable = true)
	private String parentId;
	
	@OneToMany(mappedBy = "parent",fetch=FetchType.LAZY)
	@OrderBy("seqNum,name")
	private Set<VideoChannel> children;

	@NotNull
	@Column(nullable = false)
	@Index(name="removed")
	private boolean removed = false;

	@Override
	public int compareTo(VideoChannel o) {
		return seqNum > o.seqNum ? 1 : (seqNum < o.seqNum) ? -1 : 0;
	}

}
