package com.ipinyou.optimus.console.dynamiccreative.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.optimus.console.ad.entity.Advertiser;
import com.ipinyou.optimus.console.dynamiccreative.entity.TemplateElement.MaterialType;

/**
 * 动态创意素材
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
@ToString(callSuper = true, exclude = { "orig" })
public class DynamicMaterial extends TimedEntity implements Auditable<DynamicMaterial> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5873549878483123819L;

	/**
	 * 所属广告主
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "advertiser_id", nullable = false)
	private Advertiser advertiser;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long advertiserId;
	/**
	 * 类型
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 50)
	private MaterialType type;	

	/**
	 * 宽度，类型为图片才有值，文字为空
	 */
	private Integer width;

	/**
	 * 高度，类型为图片才有值，文字为空
	 */
	private Integer height;

	/**
	 * 路径，类型为图片才有值，文字为空
	 */
	@Size(max = 512)
	@Column(nullable = true, length = 512)
	private String path;

	/**
	 * 内容，类型为文字才有值，图片为空
	 */
	@Size(max = 512)
	@Column(nullable = true, length = 512)
	private String content;

	/**
	 * 到达地址，作为主要部分时到达地址必须
	 */
	@Size(max = 2083)
	@Column(nullable = true, length = 2083)
	private String targetUrl;

	/**
	 * 分类标签，逗号分隔
	 */
	@Size(max = 128)
	@Column(nullable = true, length = 128)
	private String categoryTags;

	/**
	 * 分组标签，逗号分隔
	 */
	@Size(max = 128)
	@Index(name="groupTags")
	@Column(nullable = true, length = 128)
	private String groupTags;

	/**
	 * 属性标签，逗号分隔
	 */
	@Size(max = 128)
	@Column(nullable = true, length = 128)
	private String propertyTags;

	/**
	 * 开启／关闭
	 */
	@Column(nullable = false)
	private boolean active = true;

	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;

	/**
	 * 所关联的模板元素
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "dynamic_element_material", inverseJoinColumns = @JoinColumn(name = "element_id"), joinColumns = @JoinColumn(name = "material_id"))
	private Set<TemplateElement> relElements;

	@Override
	public String getEntityName() {
		return "动态创意素材";
	}

	@Override
	public String getName() {
		return getEntityName() + getId();
	}

	@Transient
	private DynamicMaterial orig;	

}
