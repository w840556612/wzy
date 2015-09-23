/**
 * 
 */
package com.ipinyou.optimus.console.ad.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;

/**
 * 广告主访客找回代码分类
 * 
 * @author lijt
 * 
 */
@Entity
@Data
@ToString(callSuper = true, exclude = { "orig", "company", "belongedAdvertiser" })
@EqualsAndHashCode(callSuper = true, exclude = { "orig", "company",
		"belongedAdvertiser" })
public class RetargetCategory extends TimedEntity implements
		Auditable<RetargetCategory> {

	private static final long serialVersionUID = 5007956700725931278L;

	@Override
	public String getEntityName() {
		return com.ipinyou.base.util.I18nResourceUtil.getResource("optimus.console.ad.entity.RetargetCategory.1001")/*访客找回分类*/;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private AdvertiserCompany company;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long companyId;
	/**
	 * 创建此代码的广告主
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "belonged_advertiser_id")
	private Advertiser belongedAdvertiser;
	/**
	 * 创建此代码的广告主id
	 */
	@Column(insertable = false, updatable = false, nullable = false)
	private Long belongedAdvertiserId;

	@NotNull
	@Size(min = 2, max = 64)
	@Column(nullable = false, length = 64)
	private String name;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 已删除
	 */
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;
	/**
	 * 分类参数的值，只能输入字母、数字、下划线[a-zA-Z_0-9]
	 */
	@Pattern(regexp = "\\w{1,6}")
	@NotNull
	@Column(nullable = false, length = 6)
	private String paraValue;

	public final static String DEFAULT_PARA_VALUE = "0";
	public final static String DYNAMIC_PARA_VALUE = "...";

	@Transient
	private RetargetCategory orig;

	/**
	 * 创建找回代码所需要参数
	 * 
	 * @author zhyhang
	 * 
	 */
	@Data
	public static class CodeCreateParas implements Serializable {
		private static final long serialVersionUID = 4975527856860465845L;
		/** 广告主公司ID **/
		long companyId;
		long retargetCategoryId;
		String paraValue;
		String fileNamePrefix;
		String statsDomain;
		String cdnDomain;
		String sslFileDomain;

		public CodeCreateParas(long companyId, String paraValue,
				String fileNamePrefix, String statsDomain, String cdnDomain,
				String sslFileDomain) {
			super();
			this.companyId = companyId;
			this.paraValue = paraValue;
			this.fileNamePrefix = fileNamePrefix;
			this.statsDomain = statsDomain;
			this.cdnDomain = cdnDomain;
			this.sslFileDomain = sslFileDomain;
		}
	}

}
