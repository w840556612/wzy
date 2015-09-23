package com.ipinyou.optimus.console.user.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.ipinyou.base.entity.Auditable;
import com.ipinyou.base.entity.TimedEntity;


/**
 * 用户信息反馈
 * @author root
 *
 */

@Entity
@Table(name = "usr_feedback")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Feedback extends TimedEntity implements Auditable<Feedback> ,java.io.Serializable{
	
	private static final long serialVersionUID = 1L;


@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return "反馈信息";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Feedback getOrig() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setOrig(Feedback t) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * 所属的用户
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, optional = false,fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	@Column(insertable = false, updatable = false, nullable = false)
	private Long userId;
	
	/*
	 * 反馈账号
	 */
	@Size(min = 2, max = 50)
	@Column(length = 50)
	private String handlerAccount;
	
	/**
	 * 标题
	 */
	@NotNull
	@Size(max = 30)
	@Column(nullable = false, length = 30)
	private String title;
	
	
	/**
	 * 提交问题
	 */
	@NotNull
	@Size(min = 5,max = 400)
	@Column(nullable = false, length = 400)
	private String content;
	
	/**
	 * 反馈状态
	 */
	@NotNull
	@Column(nullable = false)
	private boolean status = false;
	
	/**
	 * 反馈内容
	 */
	@Size(max = 400)
	@Column(length = 400)
	private String feedback;
	
	
	/**
	 * 联系方式
	 */
	@NotNull
	@Size(max = 30)
	@Column(nullable = false, length = 30)
	private String contact;

}
