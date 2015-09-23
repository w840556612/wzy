/**
 * 
 */
package com.ipinyou.optimus.console.ad.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

import com.ipinyou.base.entity.BaseEntity;

/**
 * 操作返回结果VO（用于前台Ajax等调用，返回标识结果：状态等）
 * @author zhyhang
 *
 */
@Data
@ToString(callSuper = true)
public class OperateResultVo implements Serializable {
	
	private static final long serialVersionUID = -2121079003541202934L;

	/**
	 * 标识操作是否成功
	 */
	private boolean success;
	
	/**
	 * 返回到前台展示的实体
	 */
	private BaseEntity entity;
	
	/**
	 * 操作返回的提示信息
	 */
	private String msg;
	
	/**
	 * 各个操作的扩展属性
	 */
	private Object data;
}
