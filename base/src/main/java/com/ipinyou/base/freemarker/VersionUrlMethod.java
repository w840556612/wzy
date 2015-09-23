package com.ipinyou.base.freemarker;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 给静态文件url增加版本号后缀
 * 根据文件最后修改时间自动加后缀，例如/s/icon/favicon.ico?31070963.ico
 * 使用示例：
 * ${versionUrl("/s/pngfix.js")}
 * @author lijt
 *
 */
public class VersionUrlMethod implements TemplateMethodModel,ServletContextAware{

	private ServletContext servletContext;
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public Object exec(@SuppressWarnings("rawtypes") List args) throws TemplateModelException {
		if (args.size() != 1&&args.size() != 2) {
			throw new TemplateModelException("Wrong	arguments!");
		}
		String str = (String) args.get(0);
		String file = str;
		if(args.size()==2){
			file = (String) args.get(1);
		}
		File f = new File(servletContext.getRealPath(file));
		if(!f.exists()||!f.isFile()){
			throw new TemplateModelException("File "+file+" not found!");
		}
		//1325347200000l =  new SimpleDateFormat("yyyyMMddHHmmssSSS").parse("20120101000000000").getTime()
		long version  = (f.lastModified()-1325347200000l)/1000;
		int pos = str.lastIndexOf('.');
		if(pos!=-1&&pos!=str.length()-1){
			return str+'?'+version+'.'+str.substring(pos+1);
		}else{
			return str+'?'+version;
		}
	}
//	public static void main(String[] args) {
//		long t;
//		long start = System.currentTimeMillis();
//		for(int i=0;i<10000000;i++){
//			t = new File("/home/lijt/tmp/t/1.png").lastModified();
//		}
//		System.out.println(System.currentTimeMillis()-start);
//	}
}
	