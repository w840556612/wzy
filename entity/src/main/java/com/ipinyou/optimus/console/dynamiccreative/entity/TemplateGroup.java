package com.ipinyou.optimus.console.dynamiccreative.entity;

import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;
import com.ipinyou.base.entity.UserDefineList;

/**
 * 创意模板
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig","groups" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig" })
public class TemplateGroup extends TimedEntity implements Auditable<TemplateGroup> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5873549878483123819L;

	public TemplateGroup() {

	}

	public TemplateGroup(String name) {
		this.name = name;
	}

	/**
	 * 名称
	 */
	@NotNull
	@Size(min = 2, max = 64)
	@Column(nullable = false, unique = true, length = 64)
	private String name;

	/**
	 * 开启/关闭
	 */
	@NotNull
	@Column(nullable = false)
	private boolean active = false;
	
	@OneToMany(mappedBy = "group")
	@OrderBy("lastModified")
	@Where(clause="removed = 0")
	private Set<CreativeTemplate> templates;
	
	/**
	 * 描述
	 */
	private String resume;
	
	/**
	 * 类型
	 */
	private Boolean isPublic;
	
	/**
	 * 所属广告主
	 */
	@AttributeOverride(name = "strValue", column = @Column(name = "owners", length = 1000))
	private OwnerList owners;
	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class OwnerList extends UserDefineList<Long> {

		private static final long serialVersionUID = 616205292479880576L;

		public OwnerList() {
			super(",");
		}

		@Override
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}

	}
	
	/**
	 * 图片预览路径
	 */
	private String perviewPic;
	
	/**
	 * 默认配置
	 */
	@AttributeOverride(name = "strValue", column = @Column(name = "default_configs", length = 2000))
	private DefaultConfigList defaultConfigs;

	/**
	 * 使用行业
	 */
	private Long applicableScope;

	/**
	 * 预览代码
	 */
	private String viewCode;

	/**
	 * 删除标记
	 */
	@NotNull
	@Column(nullable = false)
	private Boolean removed = false;

	@Override
	public String getEntityName() {
		return "创意模板分组";
	}

	@Transient
	private TemplateGroup orig;
	
	@Embeddable
	@Access(AccessType.PROPERTY)
	public static class DefaultConfigList extends UserDefineList<String> {
		private static final long serialVersionUID = 6184614428401805340L;

		public DefaultConfigList() {
			super("\n");
		}

		@Override
		@Lob
		public String getStrValue() {
			return super.getStrValue();
		}

		@Override
		public void setStrValue(String value) {
			super.setStrValue(value);
		}
	}


}
