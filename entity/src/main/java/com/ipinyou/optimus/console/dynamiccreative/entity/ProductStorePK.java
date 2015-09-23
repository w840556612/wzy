package com.ipinyou.optimus.console.dynamiccreative.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import org.hibernate.annotations.Index;

/**
 * 联合主键
 * 
 * 1、必须实现Serializable序列化 2、必须提示无参的构造方法 3、必须重写hashCode和equals方法
 * 
 * @Embeddable 表示该类中所有属性在应用该联合主键的类中作为它的属性（字段）
 * 
 */

@Data
@Embeddable
public class ProductStorePK implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 所属广告主
	 */
	private Long advertiserId;

	/**
	 * 商品编号
	 */
	@NotNull
	@Size(min = 1, max = 64)
	@Index(name = "productNo")
	@Column(length = 64, nullable = false)
	private String productNo;

	@NotNull
	private Long storeId;
	
	public ProductStorePK() {

	}

	public ProductStorePK(Long advertiserId, String productNo) {
		this.advertiserId = advertiserId;
		this.productNo = productNo;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((advertiserId == null) ? 0 : advertiserId.hashCode());
		result = PRIME * result + ((productNo == null) ? 0 : productNo.hashCode());
		result = PRIME * result + ((storeId == null) ? 0 : storeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ProductStorePK other = (ProductStorePK) obj;
		if (advertiserId == null) {
			if (other.advertiserId != null) {
				return false;
			}
		} else if (!advertiserId.equals(other.advertiserId)) {
			return false;
		}
		if (productNo == null) {
			if (other.productNo != null) {
				return false;
			}
		} else if (!productNo.equals(other.productNo)) {
			return false;
		}
		if (storeId == null) {
			if (other.storeId != null) {
				return false;
			}
		} else if (!storeId.equals(other.storeId)) {
			return false;
		}
		return true;
	}

	public String toString() {
		return String.valueOf(advertiserId).concat(":").concat(productNo).concat(":").concat(String.valueOf(storeId));
	}

}