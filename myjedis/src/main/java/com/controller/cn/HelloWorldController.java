package com.controller.cn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
@Controller
public class HelloWorldController {

    @RequestMapping(value="/stest", method = RequestMethod.POST)
    public String helloWorld(Model model,String aa,HttpServletRequest request) {
    	  System.out.println(request.getAttribute("aa"));
       String word0 = "Hello ";
       String word1 = "World!";
       model.addAttribute("word0",word0);
       model.addAttribute("word1",word1);
        return "index";
    }
    @RequestMapping("/fileUpload")  
    public String fileUpload(@RequestParam("hhh") MultipartFile[] file888,HttpServletRequest request,String aa) {  
       System.out.println(request.getAttribute("aa"));
    	// 判断文件是否为空  
    	for(int i=0;i<file888.length;i++){
    		if (!file888[i].isEmpty()){  
                try {  
                    // 文件保存路径  
                    String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"  
                            + file888[i].getOriginalFilename();  
                    // 转存文件  
                    file888[i].transferTo(new File(filePath));  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
    	}
        
        // 重定向  
        return "success";  
    } 
    
    @RequestMapping("/upload"   )  
    public String addUser(@RequestParam("file") CommonsMultipartFile[] file,HttpServletRequest request){  
          
        for(int i = 0;i<file.length;i++){  
            System.out.println("fileName---------->" + file[i].getOriginalFilename());  
          
            if(!file[i].isEmpty()){  
                int pre = (int) System.currentTimeMillis();  
                try {  
                    //拿到输出流，同时重命名上传的文件  
                    FileOutputStream os = new FileOutputStream("H:/" + new Date().getTime() + file[i].getOriginalFilename());  
                    //拿到上传文件的输入流  
                    FileInputStream in = (FileInputStream) file[i].getInputStream();  
                      
                    //以写字节的方式写文件  
                    int b = 0;  
                    while((b=in.read()) != -1){  
                        os.write(b);  
                    }  
                    os.flush();  
                    os.close();  
                    in.close();  
                    int finaltime = (int) System.currentTimeMillis();  
                    System.out.println(finaltime - pre);  
                      
                } catch (Exception e) {  
                    e.printStackTrace();  
                    System.out.println("上传出错");  
                }  
        }  
        }  
        return "/success";  
    }  
      
      
    @RequestMapping("/upload2"  )  
    public String upload2(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {  
        //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //记录上传过程起始时的时间，用来计算上传时间  
                int pre = (int) System.currentTimeMillis();  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        System.out.println(myFileName);  
                        //重命名上传后的文件名  
                        String fileName = "demoUpload" + file.getOriginalFilename();  
                        //定义上传路径  
                        String path = "H:/" + fileName;  
                        File localFile = new File(path);  
                        file.transferTo(localFile);  
                    }  
                }  
                //记录上传该文件后的时间  
                int finaltime = (int) System.currentTimeMillis();  
                System.out.println(finaltime - pre);  
            }  
              
        }  
        return "/success";  
    }  
      
    @RequestMapping("/toUpload" )   
    public String toUpload() {  
          
        return "/upload";  
    }  
}