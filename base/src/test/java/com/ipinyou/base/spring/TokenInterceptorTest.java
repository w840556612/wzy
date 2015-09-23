/**
 * 
 */
package com.ipinyou.base.spring;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.ipinyou.base.exception.TokenException;
import com.ipinyou.base.token.TokenInterceptor;

/**
 * @author lijt
 * 
 */
public class TokenInterceptorTest {

	@Test
	public void test() {
		TokenInterceptor i = null;
		MockHttpServletRequest req = null;
		MockHttpServletResponse res = null;

		i = new TokenInterceptor();
		i.setIncludeUri("/user/self /user/");
		i.setExcludeUri("/user/");
		req = new MockHttpServletRequest("PUT", "/console/user/self");
		req.setAttribute(TokenInterceptor.TOKEN_NAME, "justfortest");
		res = new MockHttpServletResponse();
		req.setContextPath("/console");
		try {
			i.preHandle(req, res, null);
			fail();
		} catch (TokenException e) {
		} catch (Exception e) {
			fail();
		}

		i = new TokenInterceptor();
		i.setIncludeUri("/user/self /user/");
		i.setExcludeUri("/user/");
		req = new MockHttpServletRequest("PUT", "/console/user/");
		req.setAttribute(TokenInterceptor.TOKEN_NAME, "justfortest");
		res = new MockHttpServletResponse();
		req.setContextPath("/console");
		try {
			i.preHandle(req, res, null);
		} catch (Exception e) {
			fail();
		}

		i = new TokenInterceptor();
		i.setIncludeUri("/user/");
		i.setIncludePattern("/user/s*");
		i.setExcludePattern("/use[rst]/");
		req = new MockHttpServletRequest("PUT", "/console/self");
		req.setAttribute(TokenInterceptor.TOKEN_NAME, "justfortest");
		res = new MockHttpServletResponse();
		req.setContextPath("/console");
		try {
			i.preHandle(req, res, null);
		} catch (TokenException e) {
		} catch (Exception e) {
			fail();
		}
	}

}
