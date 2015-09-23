package com.ipinyou.optimus.console.anticheating.entity;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CheatingDomain extends BaseCheating {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7151304406810425357L;

}
