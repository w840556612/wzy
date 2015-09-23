package com.ipinyou.base.spring;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import com.ipinyou.base.entity.UserDefineType;


public final class UserDefineTypeConverter implements
ConditionalGenericConverter {
	
	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		Set<ConvertiblePair> set=new HashSet<>();
		set.add(new ConvertiblePair(String.class,UserDefineType.class));
		set.add(new ConvertiblePair(UserDefineType.class,String.class));
		return Collections.unmodifiableSet(set);
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType,
			TypeDescriptor targetType) {
		Class<?> sourceClazz = sourceType.getType();
		if(UserDefineType.class.isAssignableFrom(sourceClazz)){
			return source==null?null:((UserDefineType)source).getStrValue();
		}
		Class<?> targetClazz = targetType.getType();
		UserDefineType target = (UserDefineType) BeanUtils.instantiate(targetClazz);
		target.setStrValue((String) source);
		return target;
	}

	@Override
	public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
		return true;
	}

}
