package com.myjedis.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/aa")
public class BController {
	@Autowired
	private Userr uu;
	@RequestMapping 
	@ResponseBody
	public Map ett()
	{
		Map m=new HashMap<String,Object>();
		List list=new ArrayList<>();
		List list2=new ArrayList<>();
		List list3=new ArrayList<>();
		Map m2=new HashMap<String,Object>();
		Map m3=new HashMap<String,Object>();
		System.out.println("aaaaaaaaaaaaaa");
		uu.setId(10);
		uu.setName("kjkjjkjfkd");
		list3.add(0);
		list3.add(1);
		m2.put("id", 12);	
		m2.put("cell", list3);
	
		list2.add(m2);
		list2.add(m2);
		m.put("total", 1);
		m.put("page", 1);
		m.put("rows", list2);
		return m;
	}
}
