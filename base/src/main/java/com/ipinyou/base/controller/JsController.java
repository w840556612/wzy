package com.ipinyou.base.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * js相关的controller
 * 
 * @author lijt
 * 
 */
@Controller
public class JsController {

	private void setCacheHeader(HttpServletResponse res){
		res.setHeader("Cache-Control", "public");
		res.setHeader("Pragma", "");
		// 1 hour 3600000L
		// 1 day 86400000L
		// 30 day 2592000000L
		// 365 day 31536000000L
		res.setDateHeader("Expires",
				new Date().getTime() + 7776000000l);
	}
	
	/**
	 * 返回当前发布的contextPath
	 * 
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/context_path.js", method = RequestMethod.GET, produces = {
			"application/*", "text/*" })
	public String contextPath(HttpServletResponse res) {
		res.setContentType("application/javascript");
		res.setCharacterEncoding("UTF-8");
		setCacheHeader(res);
		return "js_context_path";
	}

	/**
	 * 为js提供国际化的message
	 * 
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/i18n.js", method = RequestMethod.GET, produces = {
			"application/*", "text/*" })
	public String i18n(HttpServletResponse res) {
		res.setContentType("application/javascript");
		res.setCharacterEncoding("UTF-8");
		setCacheHeader(res);
		return "js_i18n";
	}
}
