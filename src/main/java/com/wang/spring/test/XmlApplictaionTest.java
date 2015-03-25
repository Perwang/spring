package com.wang.spring.test;

import com.wang.spring.factory.XmlApplictaionContext;

public class XmlApplictaionTest {
	
	public static void main(String[] args) {
		XmlApplictaionContext context=null;
		try{
			context=new XmlApplictaionContext("beans.xml");
		}catch(Exception e){
			
		}
	
		
		UserService userService=(UserService)context.getBean("userService");
		System.out.println(userService);
	}
}
