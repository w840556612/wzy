package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.ToString;

/**
 * 广告主网站访客访问轨迹之进入轨迹，不去除参数
 * @author guodong.zhang
 */
@Entity
@Table(name = "rpt_access_path_entry_full")
@ToString(callSuper = true)
public class AccessPathEntryFull extends BaseAccessPath {
	
	private static final long serialVersionUID = -222909262267012399L;
	
}
