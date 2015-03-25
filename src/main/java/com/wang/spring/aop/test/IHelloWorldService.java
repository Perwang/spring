package com.wang.spring.aop.test;

public interface IHelloWorldService {
	public void sayHello();
	
	public void sayAdvisorBefore(String param);

	public void sayBefore(String param,String name);
	public boolean sayAfterReturning();
	
	public void sayAfterThrowing();
	
	public String sayAround(String param);
}
