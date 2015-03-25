package com.wang.spring.factory;

import java.util.HashMap;
import java.util.Map;

public class SingletonBeanRegister {

	// 单例Bean缓存池，此处不考虑并发
	private final Map<String, Object> BEANS = new HashMap<String, Object>();

	public boolean containsSingleton(String beanName) {
		return BEANS.containsKey(beanName);
	}

	public Object getSingleton(String beanName) {
		return BEANS.get(beanName);
	}

	public int getSingletonCount() {
		return BEANS.size();
	}

	public String[] getSingletonNames() {
		return BEANS.keySet().toArray(new String[0]);
	}

	public void registerSingleton(String beanName, Object bean) {
		if (BEANS.containsKey(beanName)) {
			throw new RuntimeException("[" + beanName + "] 已存在");
		}
		BEANS.put(beanName, bean);
	}

}
