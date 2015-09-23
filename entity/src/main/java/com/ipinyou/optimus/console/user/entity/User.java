/**
 * 
 */
package com.ipinyou.optimus.console.user.entity;

import java.sql.Timestamp;
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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ipinyou.base.constant.Regex;
import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;

/**
 * 用户
 * @author lijt
 * 
 */
@Entity
@Table(name = "usr_user")
@Data
@ToString(callSuper = true, exclude = { "pool", "orig",  "roles" })
@EqualsAndHashCode(callSuper = true, exclude = { "pool", "orig", "roles" })
public class User extends TimedEntity implements Auditable<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8925992854633938275L;

	public static enum UserType {
		Agency, Advertiser, Register//, Media  顺序固定，不能调整
	}

	@NotNull()
	@Size(min = 2, max = 50)
	@Column(length = 50, unique = true)
	@Pattern(regexp = "[a-zA-Z0-9_\u4e00-\u9fa5.@-]{2,50}")
	private String account;

	// @NotNull
	// @Size(min = 36, max = 150)
	@Column(length = 150, nullable = false)
	private String password;

	/**
	 * 用户输入的原始密码，注册、新增用户时使用
	 */
	@Transient
	@NotNull(groups=passwordChecks.class)
	@Size(min=6,max=200)
	private String plainPassword;

	/**
	 * 用户输入的确认密码，注册、新增用户时使用
	 */
	@Transient
	@NotNull(groups=passwordChecks.class)
	@Size(min=6,max=200)
//	@EqualTo(equalTo="$('input[id=\\\"user.plainPassword\\\"]')", message="确认密码和原始密码不等")
	private String confirmPassword;

	@Column(length = 20, nullable = false)
	private String salt;
	/**
	 * 真实姓名
	 */
	@NotNull()
	@Size(min = 2, max = 45)
	@Column(length = 45)
	private String name;
	
	/**
	 * 联系方式
	 */
	@NotNull()
	@Size(min = 11, max = 32)
	@Column(nullable = false, length = 32)
	private String cellphone;
	

	/**
	 * 电子邮箱
	 */
	//@NotNull()
	@Size(min = 3, max = 100)
	@Pattern(regexp = Regex.EMAIL)
	@Email()
	@Column(length = 100, unique = true)
	private String email;

//	/**
//	 * 固定电话
//	 */
//	@Size(min = 3, max = 40)
//	@Column(length = 40)
//	private String telephone;
	/**
	 * 注册来源
	 */
	@Column(length = 40)
	private String source;
	
	/**
	 * 状态：true 启用 false 关闭
	 */
	private boolean active = true;
	/**
	 * 审核状态，express版本需要审核
	 */
	private boolean audited;
	/**
	 * 是否删除
	 */
	private boolean removed = false;

	@NotNull
	@Column(length=50)
	@Enumerated(EnumType.STRING)
	private UserType type = UserType.Register;

	/**
	 * 忘记密码 邮件发送时间
	 */
//	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp sendTime;
	
	/**
   * 密码修改时间
   */
	private Timestamp lastPwdmodified;

	/**
	 * 审核文档更新时间
	 */
	private Timestamp lastAuditDocDownload;
	
	/**
	 * 忘记密码 随机字符串
	 */
	// @Size(min = 6, max = 10)
	@Column(length = 10)
	private String random;


	/**
	 * 池
	 */
	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "pool_id", nullable = false)
	private Pool pool;
	
	@Column(insertable = false, updatable = false, nullable = false)
	private Long poolId;
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "usr_user_role", inverseJoinColumns = @JoinColumn(name = "role_id"), joinColumns = @JoinColumn(name = "user_id"))
	private Set<Role> roles;
	
	/**
	 * 若有值，该用户不能删除
	 */
	@Column(length = 20)
	@Index(name="sysId")
	private String sysId;
	
	private boolean selfRegister = false;
	
	/*
	 * 是否显示操作向导
	 */
	private boolean showGuide = true;

	
	public final static String SYS_ID_ADMIN = "ADMIN";
	
	public static enum UserCategory {
		NotSet("未分类"), Test("测试用户"), Internal("内部用户"), Invalid("无效用户"), Valid("有效用户"), Cooperation("合作客户"), StrongIntention(
				"强意向客户"), Intention("意向客户"), WeakIntention("弱意向客户"), NotSupported("不支持客户"), NoneIntention("无意向客户");

		private String text;

		private UserCategory(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}

	}
	
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private UserCategory category = UserCategory.NotSet;
	
	
	@Column(length = 64)
	private String partnerId;
	
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private Partner partnerName;	
	
	
	public static enum Partner {
		Acxiom("安客诚");
		
		private String text;
		private Partner(String text){
			this.text = text;
		}
		public String getText(){
			return text;
		}
	}
	
	@Column(length = 45)
	@Enumerated(EnumType.STRING)
	private UserFunction userFunction = UserFunction.none;
	
	public static enum UserFunction{
		none("未分配"),preSale("销售员"),service("优化师"),afterSale("续费");

		private String text;

		private UserFunction(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ipinyou.base.entity.Entity#getEntityName()
	 */
	@Override
	@Transient
	public String getEntityName() {
		return "用户";
	}

	@Transient
	private User orig;

	public interface passwordChecks {
	};
}
