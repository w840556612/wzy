/**
 * 
 */
package com.ipinyou.base.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BigIntegerConverter;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.junit.Test;
import org.springframework.beans.BeanUtils;



/**
 * @author lijt
 *
 */
public class CopyPropertiesTest {
	@Data
	public static class Obj{
		private int i;
		
		private Integer integer;
		
		private long l;
		
		private Long longValue;
		
		private String str;
		
		private Date date;
		
		private BigDecimal bigDecimal;
		
		private BigInteger bigInteger;		
	}
	@Test
	public void test(){
		//date 
		ConvertUtils.register(new DateConverter(null),java.util.Date.class);
        ConvertUtils.register(new SqlDateConverter(null),java.sql.Date.class);
		ConvertUtils.register(new SqlTimeConverter(null),Time.class);
		ConvertUtils.register(new SqlTimestampConverter(null),Timestamp.class);
		//number
		ConvertUtils.register(new BooleanConverter(null), Boolean.class);
		ConvertUtils.register(new ShortConverter(null), Short.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new LongConverter(null), Long.class);
		ConvertUtils.register(new FloatConverter(null), Float.class);
		ConvertUtils.register(new DoubleConverter(null), Double.class);
		ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class); 
		ConvertUtils.register(new BigIntegerConverter(null), BigInteger.class);	
		
		Obj o = new Obj();
		Obj dest = new Obj();
		BeanUtils.copyProperties(o, dest);
		assertNull(dest.getBigDecimal());
		assertNull(dest.getBigInteger());
		assertNull(dest.getDate());
		assertNull(dest.getStr());
		assertNull(dest.getLongValue());
		assertNull(dest.getInteger());
		assertEquals(0l,dest.getL());
		assertEquals(0,dest.getI());
		o = new Obj();
		o.setBigInteger(new BigInteger("100"));
		o.setDate(new Date());
		o.setBigDecimal(new BigDecimal("100.99"));
		dest = new Obj();
		BeanUtils.copyProperties(o, dest);
		assertEquals(new BigDecimal("100.99"),dest.getBigDecimal());
		assertEquals(new BigInteger("100"),dest.getBigInteger());
		assertNotNull(dest.getDate());
		
		
		
	}
}
