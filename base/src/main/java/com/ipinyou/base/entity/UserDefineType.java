package com.ipinyou.base.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * 用户自定义类型支持（应用中表示为类对象，存储在数据库中以字符串形式存储）
 * 
 * @author zhyhang
 * 
 */
public abstract class UserDefineType implements Component {

	private static final long serialVersionUID = -7916114649252671716L;

	protected String splitToken;

	private String value;

	/**
	 * 分割符
	 * 
	 * @param splitToken
	 */
	public UserDefineType(String splitToken) {
		this.splitToken = splitToken;
	}

	public String getStrValue() {
		return this.value;
	}

	public void setStrValue(String value) {
		if("".equals(value)){
			this.value=null;
		}else{
			this.value = value;
		}
	}

/*	protected String[] getStrValueArray() {
		if (StringUtils.isNotBlank(getStrValue())) {
			return getStrValue().trim().split(splitToken);
		}
		return new String[0];
	}*/
	
	//去重
	protected String[] getStrValueArray() {
		if (StringUtils.isNotBlank(getStrValue())) {
//			Set<String> set = new HashSet<String>();
			List<String> list = new ArrayList<String>();
			for(String s:getStrValue().trim().split(splitToken)){
//				set.add(s);
				if(!list.contains(s)){
					list.add(s);
				}
			}
//			return (String[]) set.toArray(new String[set.size()]);
			return (String[]) list.toArray(new String[list.size()]);
		}
		return new String[0];
	}

	protected String asString(Object obj) {
		if (obj instanceof Enum) {
			return ((Enum<?>) obj).name();
		} else {
			return obj.toString();
		}
	}

	/**
	 * 是否将元素有序存储串，要求元素实现Comparable接口<br>
	 * list是对元素，map是对key，排序
	 * 
	 * @return ordered
	 */
	protected boolean needOdered() {
		return false;
	}

	@Override
	public int hashCode() {
		return null == getStrValue() ? super.hashCode() : getStrValue()
				.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean equal = obj != null && obj.getClass().equals(this.getClass());
		if (equal) {
			String thisValue = getStrValue();
			String objValue = ((UserDefineType) obj).getStrValue();
			equal = (thisValue == null && objValue == null ? obj == this
					: (thisValue != null ? thisValue.equals(objValue) : false));

		}
		return equal;
	}

	@Override
	public String toString() {
		return null == getStrValue() ? "" : getStrValue();
	}

}
