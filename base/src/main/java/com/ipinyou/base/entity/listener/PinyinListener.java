package com.ipinyou.base.entity.listener;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipinyou.base.entity.Entity;
import com.ipinyou.base.util.PinyinUtils;

/**
 * 自动处理辅助拼音模糊查询字段属性的JPA（更新及保存数据酷前）侦听器类
 * @author wujun
 * @version 1.0 2013/11/07
 */
public class PinyinListener {
	
	/**
	 * 日志记录实例
	 */
	protected final static transient Logger logger = LoggerFactory
			.getLogger(PinyinListener.class);
	
	@PrePersist
	public void onPrePersist(Entity e) {

		 makeHanyupinyin(e);
			
	}

	@PreUpdate
	public void onPreUpdate(Entity e) {

		 makeHanyupinyin(e);
	}
	
	/**
	 * 对Entity e 扫描查找"Pinyin"的属性，并进行拼音字串装换并赋值
	 * add by wujun 20131105
	 */
	private void makeHanyupinyin(Entity e){	
		
		Method[] methods = e.getClass().getMethods();
		
		for( Method m : methods ){

			if( m.getName().endsWith("Pinyin")){
				    
					String methodMame = m.getName();
					if(methodMame.indexOf("Pinyin")==3) continue;//如果只有getPinyin()或setPinyin()，忽略 add by wujun 20131113
					if(methodMame.startsWith("get")) continue;
					if(methodMame.startsWith("set")){
					try {	
						String mName = methodMame.substring(3,methodMame.indexOf("Pinyin"));//扫描以"Pinyin"结尾的set 方法名				
						String FieldNameFirst = mName.substring(0, 1).toLowerCase();	
						String FieldNameHanyupinyin = FieldNameFirst+methodMame.substring(4);
						
						String getMethodMame = "get"+mName;
						Method getM = e.getClass().getMethod(getMethodMame);						
						Object srcForHanyupingyin = getM.invoke(e);						
						if( srcForHanyupingyin ==null ) continue;//非空检查
						
						//得到pinyin属性对象，并获取@max注解值
						//处理拼音字段的最大长度，避免因为该辅助字段导致数据无法存库
					    Field fieldHanyupinyin ;
					    int max = -1;
						try {
							fieldHanyupinyin = e.getClass().getDeclaredField(FieldNameHanyupinyin);
							javax.validation.constraints.Size size = fieldHanyupinyin.getAnnotation(javax.validation.constraints.Size.class);
						    max = size.max();
						} catch (Exception e1) {
							logger.error(ExceptionUtils.getStackTrace(e1));
						} 						
						m.invoke(e,PinyinUtils.getPinYin(srcForHanyupingyin.toString(), max));//对拼音属性赋值
						
					} catch (IllegalAccessException e1) {
						logger.error(ExceptionUtils.getStackTrace(e1));
					} catch (IllegalArgumentException e1) {
						logger.error(ExceptionUtils.getStackTrace(e1));
					} catch (InvocationTargetException e1) {
						logger.error(ExceptionUtils.getStackTrace(e1));
					} catch (NoSuchMethodException e1) {
						logger.error(ExceptionUtils.getStackTrace(e1));
					} catch (SecurityException e1) {
						logger.error(ExceptionUtils.getStackTrace(e1));
					} catch (Exception e1) {
						logger.error(ExceptionUtils.getStackTrace(e1));
					} 		
					
				}
				
				
				
			}
			
		}
			
		
		
		
	}
	
	
	
	
}
