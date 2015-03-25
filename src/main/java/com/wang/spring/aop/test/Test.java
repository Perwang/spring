package com.wang.spring.aop.test;

/**
 * 
 * @author wangcanpei
 *
 */
public class Test {
	public static void main(String[] args) {
		WeaveTest test=new WeaveTest();
		LogUtils logUtils=new LogUtils();
		UserService userService=new UserService();
		test.setInWeave(logUtils);
		test.setInWeaveMethodName("sayLog");
		test.setToWeave(userService);
		test.setToWeaveMethodName("sayName");
		test.weave();
	}
}
