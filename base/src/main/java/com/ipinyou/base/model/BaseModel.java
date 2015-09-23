package com.ipinyou.base.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 模型基类，主要实现toString, hashCode, equals方法
 *  需要注意双向关联时可能出现循环调用进而出现死循环的问题
 * @author lijt
 *
 */
public abstract class BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6479341524949060190L;
	/**
	 * Logger for this class
	 */
	protected transient final Logger logger = LoggerFactory.getLogger(this
			.getClass());

	/**
	 * 用于输出对象
	 * 
	 * @see java.lang.Object#toString()
	 * @return String
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * 判断两个对象是否相等
	 * 
	 * @see java.lang.Object#equals
	 * @param o
	 * @return boolean
	 */
	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	/**
	 * 返回该对象的散列码值
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
