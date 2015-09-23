package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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

/**
 * 广告主公司报表参数
 * @author guodong.zhang
 */
@Entity
@Table(name = "rpt_advertiser_company_parameter")
@Data
@ToString(callSuper = true, exclude = { "orig"})
@EqualsAndHashCode(callSuper = true, exclude = { "orig"})
@JsonIgnoreProperties(value = { "version", "lastModified", "creator", "orig"})
public class AdvertiserCompanyParameter extends TimedEntity implements Auditable<AdvertiserCompanyParameter>{

	private static final long serialVersionUID = 5309873407880781169L;
	
	@Override
	public String getEntityName() {
		return "广告主报表参数";
	}
	
	/**
	 * 广告主公司ID
	 */
	@NotNull
	@Index(name="companyId")
	private long companyId;
	
	/**
	 * 报表名称
	 */
	@NotNull
	@Size(min = 1, max = 50)
	@Column(nullable = false, length = 50)
	@Index(name="reportName")
	private String reportName;
	
	/**
	 * 参数名称
	 */
	@NotNull
	@Size(min = 1, max = 50)
	@Column(nullable = false, length = 50)
	private String name;
	
	/**
	 * 参数值
	 */
	@NotNull
	@Size(min = 1, max = 20)
	@Column(nullable = false, length = 10)
	private long value;
	
	@NotNull
	@Column(nullable = false)
	private boolean removed = false;
	
	@Transient
	private AdvertiserCompanyParameter orig;
	
}
