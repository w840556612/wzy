package com.myjedis.cn;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Component
public class Userr{

    private long id;
    private String name;
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }


/*
    public static void main(String[] args) {
//        ApplicationContext ac =  new ClassPathXmlApplicationContext("src\\main\\java\\spring-context.xml");
//        UserDAOImpl userDAO = (UserDAOImpl)ac.getBean("userDAO");
//        User user1 = new User();
//        user1.setId(1);
//        user1.setName("obama");
//        userDAO.saveUser(user1);
//        User user2 = userDAO.getUser(1);
//        System.out.println(user2.getName());
    	
    }*/
    }