package com.wang.spring.aop.test;

public class HelloWorldService implements IHelloWorldService {

	public void sayHello() {
		// TODO Auto-generated method stub
		  System.out.println("============Hello World!");
	}



	public void sayBefore(String param, String name) {
		System.out.println("============say " + param+" name= "+name);
	}



	public boolean sayAfterReturning() {
		  System.out.println("============after returning");
		  return true;
	}

	
	public void sayAfterThrowing() {
	    System.out.println("============before throwing");
	    throw new RuntimeException();
	}
	
	public String sayAround(String param) {
		   System.out.println("============around param:" + param);
		   return param;
	}


	public void sayAdvisorBefore(String param) {
		// TODO Auto-generated method stub
		System.out.println("============say before");
	}


}
