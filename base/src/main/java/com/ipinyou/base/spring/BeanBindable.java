/**
 * 
 */
package com.ipinyou.base.spring;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注在某个controller方法的bean参数上，指明实体中哪些属性可由前端参数进行绑定<br>
 * 白名单机制，防止恶意篡改数据
 * @author zhyhang
 *
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BeanBindable {
	/** 前台参数名称（bean对应的）**/
	String name() default "";
	/** 可绑定的bean属性，例如："advertiser.name,registerName"(以逗号分割) **/
	String properties() default "";	
}
