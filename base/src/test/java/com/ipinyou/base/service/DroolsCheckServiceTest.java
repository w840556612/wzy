package com.ipinyou.base.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ipinyou.base.drools.DroolsCheckService;

public class DroolsCheckServiceTest {
	
	@Test
	public void testLogin(){
		List<String> pathes = new ArrayList<String>(); 
		pathes.add("classpath*:spring/spring-test.xml");
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext(pathes.toArray(new String[pathes.size()]));
	   	DroolsCheckService ruleCheckService= (DroolsCheckService) context.getBean( "ruleCheck" );
	   	TestCheckeeModel tcm = new TestCheckeeModel();
	   	ruleCheckService.check(tcm);
	   	assertTrue( tcm.getCheckResult() );
	   	tcm.setCheckResult(false);
	   	tcm.age= 9;
	   	ruleCheckService.check(tcm);
	   	assertFalse( tcm.getCheckResult() );
	   	
	   	try {
			TimeUnit.MINUTES.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	   	
	   	ruleCheckService.check(tcm);
	   	assertTrue( tcm.getCheckResult() );
	}
	
	public static class TestCheckeeModel{
		private boolean checkResult = false;
		private int age = 18;
		
		public int getAge(){
			return age;
		}

		public boolean getCheckResult() {
			return checkResult;
		}

		public void setCheckResult(boolean checkResult) {
			this.checkResult = checkResult;
		}

		public String getRuleName() {
			
			return "1000/ad/test";
		}
		
	}
}
