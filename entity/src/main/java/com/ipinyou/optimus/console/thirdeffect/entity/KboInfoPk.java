package com.ipinyou.optimus.console.thirdeffect.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Embeddable;

import lombok.Data;
@Data
@Embeddable
public class KboInfoPk implements Serializable{

    private static final long serialVersionUID = -1566726372873043862L;

	/**
	 * 所属策略
	 */
	private Long strategyId;
	
	/**
	 * 所属策略
	 */
	private Long creativeId;

	/**
	 * 商品编号
	 */
	private Date day;

	public KboInfoPk() {

	}

	public KboInfoPk(Long strategyId, Long creativeId,Date day) {
		this.strategyId = strategyId;
		this.creativeId = creativeId;
		this.day = day;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result
				+ ((strategyId == null) ? 0 : strategyId.hashCode());
		result = PRIME * result
				+ ((creativeId == null) ? 0 : creativeId.hashCode());
		result = PRIME * result
				+ ((day == null) ? 0 : day.hashCode());
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
		final KboInfoPk other = (KboInfoPk) obj;
		if (strategyId == null) {
			if (other.strategyId != null){				
				return false;
			}
		} else if (!strategyId.equals(other.strategyId))
			return false;
		if (creativeId == null) {
			if (other.creativeId != null){				
				return false;
			}
		} else if (!creativeId.equals(other.creativeId)){
			return false;
		}
		if (day == null) {
			if (other.day != null){				
				return false;
			}
		} else if (!day.equals(other.day)){
			return false;
		}
		return true;
	}

}
