/**
 * 
 */
package com.ipinyou.optimus.console.user.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

/**
 * 权限，此模型对应的数据不在界面中管理
 * @author lijt
 * 
 */
@Entity
@Table(name = "usr_permission")
@Data
@ToString(exclude = { "roles" })
@EqualsAndHashCode(exclude = { "roles" })
public class Permission implements com.ipinyou.base.entity.Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5453354440420366090L;
	
	public static final String KB_VIEW_PREFIX = "knowledge.view.";
	
	/**
	 * 权限字符串，用于权限判断
	 */
	@Id
	@Column(length = 255, nullable = false, unique = true)
	private String perm;

	/**
	 * 菜单
	 */
	@Column(length = 45, nullable = false)
	private String menu;

	/**
	 * 权限名称
	 */
	@Column(length = 45, nullable = false, unique = true)
	private String name;
	
	/**
	 * 系统标识
	 */
	@Column(length = 45, nullable = false, unique = true)
	private String sysId;
	
	/**
	 * 已删除
	 */
	private boolean removed = false;

	/**
	 * 级别
	 */
	private byte level;

	/**
	 * 包含该权限的角色
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "permissions")
	private Set<Role> roles;
	
	/**
	 * 排序字段
	 */
	@Index(name="seqNum")
	@Column(nullable = false)
	private int seqNum;
}
