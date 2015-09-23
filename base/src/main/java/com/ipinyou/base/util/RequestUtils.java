/**
 * 
 */
package com.ipinyou.base.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.ipinyou.base.constant.Presentation;
import com.ipinyou.base.constant.RequestKey;

/**
 * @author lijt
 * 
 */
public abstract class RequestUtils {
	/**
	 * 日志记录实例
	 */
	protected final static transient Logger logger = LoggerFactory
			.getLogger(RequestUtils.class);

	private final static String[] query_str_exclude_params = new String[] {
			Presentation.KEY_SUCCESS, Presentation.KEY_FAILURE };

	/**
	 * 获取queryStr，系统中使用该参数记录列表页面的参数，执行修改或者删除之后将回到原参数页面，注意排除了参数success,fail
	 * 
	 * @param req
	 *            HttpServletRequest object
	 * @return 码之后的查询字符串，排除了success,fail
	 */
	public static String getQueryStr(HttpServletRequest req) {
		return getQueryStr(req, "");
	}

	/**
	 * 获取queryStr，系统中使用该参数记录列表页面的参数，执行修改或者删除之后将回到原参数页面，注意排除了参数success,fail
	 * 
	 * @param req
	 *            HttpServletRequest object
	 * @param prefix
	 *            前缀
	 * @return 编码之后的查询字符串，排除了success,fail，还可以添加前缀
	 */
	public static String getQueryStr(HttpServletRequest req, String prefix) {
		String queryStr = getQueryString(req, query_str_exclude_params);
		if (queryStr.length() > 0) {
			try {
				return URLEncoder.encode(prefix + "?" + queryStr, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.warn(ExceptionUtils.getStackTrace(e));
				throw new RuntimeException(e);
			}
		} else {
			return prefix;
		}
	}

	/**
	 * 获取排除了指定参数之后的查询字符串
	 * 
	 * @param req
	 *            HttpServletRequest object
	 * @param excludeParams
	 *            被排除的参数名称
	 * @return 查询字符串 queryString，例如status=1&test=2
	 */
	public static String getQueryString(HttpServletRequest req,
			String[] excludeParams) {
		String queryString = req.getQueryString();
		if (queryString == null || queryString.length() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder(queryString.length());
		OUTER: for (String s : StringUtils.split(queryString, '&')) {
			for (String exclude : excludeParams) {
				if (StringUtils.startsWith(s, exclude + "=")) {
					continue OUTER;
				}
			}
			sb.append('&');
			sb.append(s);
		}
		return sb.length() == 0 ? "" : sb.substring(1);
	}

	public static void inlinePic(HttpServletResponse res, String path) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		if (path != null) {
			if (path.toLowerCase().endsWith(".jpg")
					|| path.toLowerCase().endsWith(".jpeg")) {
				res.setContentType("image/jpeg");
				res.setHeader("Content-disposition",
						"inline; filename=\"license.jpg\";");
			} else if (path.toLowerCase().endsWith(".gif")) {
				res.setContentType("image/gif");
				res.setHeader("Content-disposition",
						"inline; filename=\"license.gif\";");
			} else if (path.toLowerCase().endsWith(".png")) {
				res.setContentType("image/png");
				res.setHeader("Content-disposition",
						"inline; filename=\"license.png\";");
			}
			res.setHeader("Content-Length",
					String.valueOf(new File(path).length()));
			try {
				bis = new BufferedInputStream(new FileInputStream(path));
				bos = new BufferedOutputStream(res.getOutputStream());
				IOUtils.copy(bis, bos);
			} catch (IOException e) {
				logger.error(ExceptionUtils.getStackTrace(e));
				throw new RuntimeException(e);
			} finally {
				IOUtils.closeQuietly(bis);
				IOUtils.closeQuietly(bos);
			}
		}
	}
	
	
	
	/**
	 * 将content内容（文本内容）向客户端发送
	 * @param response
	 * @param clientFileName 客户端默认保存文件名称
	 * @param content 以迭代器的形式提供的文件内容
	 */
	public static void sendClientTxtFile(HttpServletResponse response,String clientFileName,Iterator<String> content){
		//保证IE在https下能够访问下载文件
		response.setHeader("Content-Transfer-Encoding","binary"); 
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");  
		response.setHeader("Pragma", "public");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + clientFileName);
		OutputStream os=null;
		try {
			os=response.getOutputStream();
			while(content.hasNext()){
				String element=content.next();
				if(null==element)
					continue;
				os.write(element.getBytes("UTF-8"));
			}
			
		} catch (IOException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(e);
		}finally{
			IOUtils.closeQuietly(os);			
		}
		
	}
	
	/**
	 * 判断是否应该从https到http重定向
	 * @param request
	 * @return
	 */
	public static boolean isRedirectHttp(HttpServletRequest request){
		String baseUrl=request.getParameter(RequestKey.PARA_CLIENT_BASE_URL.key());
		String actionBaseUrl=request.getParameter(RequestKey.PARA_CLIENT_ACTION_BASE_URL.key());
		if(StringUtils.isNotBlank(baseUrl) && !baseUrl.equals(actionBaseUrl)){//&& redirectHttpFlag){//TODO 全程SSL支持只在这里处理即可,可放到shiro的session中
			return true;
		}
		return false;	
	}
	
	/**
	 * 执行post之后的重定向(主要用于https转为http)
	 * @param request
	 * @param redirectAttributes
	 * @param result 使用spring freemarker错误结果
	 * @param bindingEntity
	 * @param bindingEntityName
	 * @param origPage
	 * @return 重定向后的URL地址
	 */
	public static String issueRedirectHttp(HttpServletRequest request,RedirectAttributes redirectAttributes,BindingResult result,
			Object bindingEntity,String bindingEntityName,String origPage){
		if(result!=null){
			redirectAttributes.addFlashAttribute(bindingEntityName,bindingEntity);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX+bindingEntityName, result);
		}
		if(!isRedirectHttp(request)){
			return origPage;
		}
		String baseUrl=request.getParameter(RequestKey.PARA_CLIENT_BASE_URL.key());
		if(origPage.startsWith(UrlBasedViewResolver.REDIRECT_URL_PREFIX)){
			return origPage.replaceFirst(UrlBasedViewResolver.REDIRECT_URL_PREFIX,
					UrlBasedViewResolver.REDIRECT_URL_PREFIX+baseUrl+request.getContextPath());
		}else{
			return baseUrl+request.getContextPath()+origPage;
		}
	}
	
	/**
	 * 先从从前台隐藏字段取；取不到，再从request取(当使用nginx等进行https->http代理转发时，直接使用request.getRequestUrl()可能取不到客户想要的地址<br>
	 * 尤其在需求将url以明文提供给用户使用，如密码修改链接等场景）
	 * @param request
	 * @return
	 */
	public static String getRequestUrl(HttpServletRequest request){
		String baseUrl=request.getParameter(RequestKey.PARA_CLIENT_BASE_URL.key());
		if(StringUtils.isNotBlank(baseUrl)){//从前台隐藏字段取，取不到再从request取(在使用nignx等进行https->http代理转发时可能取不到客户想要的地址）
			return baseUrl+request.getRequestURI();
		}
		return request.getRequestURL().toString();
	}
	
}
