package com.ipinyou.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.ipinyou.base.util.PinyinUtils;

@Data
@EqualsAndHashCode(of = { "id" })
public class PinyinEntity implements Comparable<PinyinEntity> {
	private String id;
	private String name;
	private String pinyin;

	public PinyinEntity(Object id, Object name) {
		this.id = String.valueOf(id);
		this.name = name != null ? String.valueOf(name) : null;
		this.pinyin = PinyinUtils.getPinYin(this.name, 1000);
	}

	@Override
	public int compareTo(PinyinEntity o) {
		return this.id.compareTo(o.getId());
	}

}