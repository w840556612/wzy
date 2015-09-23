/**
 * 
 */
package com.ipinyou.optimus.console.user.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.BaseEntity;

/**
 * 角色
 * @author lijt
 * 
 */
@Entity
@Table(name = "usr_role", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@Data
@ToString(callSuper = true, exclude = { "pools", "users", "permissions", "orig" })
@EqualsAndHashCode(callSuper = true, exclude = { "pools", "users",
		"permissions", "orig" })
public class Role extends BaseEntity implements Auditable<Role> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7730951189092713301L;

	/**
	 * 名称
	 */
	@Column(length = 45, nullable = false)
	private String name;

	/**
	 * 备注
	 */
	@Column(length = 45, nullable = true)
	private String remark;

	/**
	 * 若有值，该角色不能删除
	 */
	@Column(length = 20)
	@Index(name="sysId")
	private String sysId;

	public final static String SYS_ID_EXPRESS_LV4 = "EXPRESS_LV4";
	public final static String SYS_ID_LV3 = "LV3";

	public final static String SYS_ID_USER_ADMIN = "USER_ADMIN";

	public final static String SYS_ID_PRE_SALE = "PRE_SALE";

	public final static String SYS_ID_SERVICE = "SERVICE";

	public final static String SYS_ID_AFTER_SALE = "AFTER_SALE";

	public final static String SYS_ID_OPERATION_MANAGEMENT = "OPERATION_MANAGEMENT";

	public final static String SYS_ID_PRODUCT_OPERATION = "PRODUCT_OPERATION";
	
	public final static String SYS_ID_FINANCE_MANAGER = "FINANCE_MANAGER";
	
	public final static String SYS_ID_REGISTER_DEFAULT = "REGISTER_DEFAULT";
	
	public final static String SYS_ID_OPTIMUS_AUDIT = "JSJJXX";
	
	public final static String SYS_DEFAULT_POOL_ROLE_FULL_ROLENAME = "系统管理员_优驰";

	public final static String SYS_DEFAULT_POOL_ROLE_DASUANPAN_ROLENAME = "系统管理员_大算盘";

	public final static String SYS_DEFAULT_POOL_ROLE_DASUANPAN_REGISTER = "大算盘注册用户";

	/**
     * 角色类型:代理商，广告主
     */
    public static enum RoleType {
        Agency("代理商"), Advertiser("广告主"),Register("注册用户");
        private String text;

        private RoleType(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
    
	@NotNull
	@Column(nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private RoleType type;

	/**
	 * 角色等级:Standard标准角色，Interior内部角色,Individuation个性化角色
	 */
	public static enum RoleLevel {
		Standard("标准角色"), Interior("内部角色"), Individuation("个性化角色"), Na("Na");
		private String text;

		private RoleLevel(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

    @NotNull
    @Column(nullable = false, length=50)
    @Enumerated(EnumType.STRING)
	private RoleLevel roleLevel;
	
	/**
	 * 角色归属:Full版，大算盘
	 */
	public static enum RoleAscription {
		Full("Full版"), Dasuanpan("大算盘"), ChargeFunc("收费功能"), Na("Na");
		private String text;

		private RoleAscription(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}

	@NotNull
	@Column(nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private RoleAscription roleAscription;

	/**
	 * 拥有该角色的用户
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "roles")
	private Set<User> users;

	/**
	 * 该角色拥有的权限
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "usr_role_permission", inverseJoinColumns = @JoinColumn(name = "permission"), joinColumns = @JoinColumn(name = "role_id"))
	private Set<Permission> permissions;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "roles")
	private Set<Pool> pools;

	private boolean removed = false;
	
	/**
	 * 角色说明
	 */
	@Column(length = 64)
	private String roleDescription;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipinyou.base.entity.Entity#getEntityName()
	 */
	@Override
	@Transient
	public String getEntityName() {
		return "角色";
	}

	@Transient
	private Role orig;
}
