package com.wang.spring.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来表示bean的结构
 * @author wangcanpei
 *
 */
public class DefinitionBean {
	
	/**
	 * bean的id
	 */
	private String id;
	
	/**
	 * bean的类型
	 */
	private String type;
	
	/**
	 * bean的名称
	 */
	private String name;
	
	/**
	 * class的地址
	 */
	private String classPath;
	
	/**
	 * prototype
	 */
	public final static int  SCOPE_PROTOTYPE=1;
	
	/**
	 * singleton
	 */
	public final static int   SCOPE_SINGLETON=0;
	
	
	/**
	 * bean里面的属性结构
	 */
	private Map<String,Object> properties=new HashMap<String,Object>();
	
	
	private int scope;
	
	

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
	
	
}
