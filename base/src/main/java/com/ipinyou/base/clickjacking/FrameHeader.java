package com.ipinyou.base.clickjacking;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FrameHeader {
	
	FrameOption value() default FrameOption.Sameorigin;
	
	String uri() default "";
}
