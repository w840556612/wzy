package com.ipinyou.optimus.console.report.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.ToString;

/**
 * 广告主网站访客访问轨迹之进入轨迹，去除参数
 * @author guodong.zhang
 */
@Entity
@Table(name = "rpt_access_path_entry_simple")
@ToString(callSuper = true)
public class AccessPathEntrySimple extends BaseAccessPath {
	
	private static final long serialVersionUID = 2376757049618018600L;
	
}
