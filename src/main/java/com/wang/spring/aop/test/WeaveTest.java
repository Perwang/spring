package com.wang.spring.aop.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 编织类，用来组织各个组件之间的关系
 * @author wangcanpei
 *
 */
public class WeaveTest {
	
	/**
	 * 要织入的组件
	 */
	private Object inWeave;
	
	/**
	 * 织入的方法名
	 */
	private String inWeaveMethodName;
	
	/**
	 * 被织入的组件
	 */
	private Object toWeave;
	/**
	 * 被织入的方法名
	 */
	private String toWeaveMethodName;
	
	
	/**
	 * 编织方法，用来组织各个组件之间的关系
	 * 
	 */
	public void weave(){
		/**
		 * 1、执行要编入的组件
		 * 2、具体的要被织入代码的组件
		 */
		/**
		 * 1、获取相应的class，然后执行class里的方法
		 */
		
		Class inWeaveClazz=inWeave.getClass();
		try {
			Object inWeaveO=inWeaveClazz.newInstance();
			Method inWeaveMethod=inWeaveClazz.getMethod(inWeaveMethodName);
			inWeaveMethod.invoke(inWeaveO);
			
			Class toWeaveClazz=toWeave.getClass();
			Object toWeaveO=toWeaveClazz.newInstance();
			Method toWeaveWeaveMethod=toWeaveClazz.getMethod(toWeaveMethodName);
			toWeaveWeaveMethod.invoke(toWeaveO);
		
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public Object getInWeave() {
		return inWeave;
	}


	public void setInWeave(Object inWeave) {
		this.inWeave = inWeave;
	}


	public String getInWeaveMethodName() {
		return inWeaveMethodName;
	}


	public void setInWeaveMethodName(String inWeaveMethodName) {
		this.inWeaveMethodName = inWeaveMethodName;
	}


	public Object getToWeave() {
		return toWeave;
	}


	public void setToWeave(Object toWeave) {
		this.toWeave = toWeave;
	}


	public String getToWeaveMethodName() {
		return toWeaveMethodName;
	}


	public void setToWeaveMethodName(String toWeaveMethodName) {
		this.toWeaveMethodName = toWeaveMethodName;
	}
	
	
	
}
