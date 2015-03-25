package com.wang.spring.test;

import java.util.Map;

public class User {
	
	private String name;
	
	private Map<String,String> adrresMap;
	
	
	

	
	public Map<String, String> getAdrresMap() {
		return adrresMap;
	}

	public void setAdrresMap(Map<String, String> adrresMap) {
		this.adrresMap = adrresMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
