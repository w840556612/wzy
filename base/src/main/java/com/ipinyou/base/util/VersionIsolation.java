/**
 * 
 */
package com.ipinyou.base.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识代码的服务版本（有些代码Ｆ版和Ｅ版共用，有些只有Ｆ版或Ｅ版使用）
 * @author zhyhang
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface VersionIsolation {
	
	OptimusVersion useFor() default OptimusVersion.ALL;
	
	public static enum OptimusVersion{
		ALL,FULL,EXPRESS;
	}
		
}
