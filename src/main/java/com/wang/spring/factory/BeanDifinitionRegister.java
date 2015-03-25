package com.wang.spring.factory;

import java.util.HashMap;
import java.util.Map;

import com.wang.spring.bean.DefinitionBean;

public class BeanDifinitionRegister {

	// bean定义缓存，此处不考虑并发问题
	private final Map<String, DefinitionBean> DEFINITIONS = new HashMap<String, DefinitionBean>();

	public void registerBeanDefinition(String beanName, DefinitionBean bd) {
		// 1.本实现不允许覆盖Bean定义
		if (DEFINITIONS.containsKey(bd.getId())) {
			throw new RuntimeException("已存在Bean定义，此实现不允许覆盖");
		}
		// 2.将Bean定义放入Bean定义缓存池
		DEFINITIONS.put(bd.getId(), bd);
	}

	public DefinitionBean getBeanDefinition(String beanName) {
		return DEFINITIONS.get(beanName);
	}

	public boolean containsBeanDefinition(String beanName) {
		return DEFINITIONS.containsKey(beanName);
	}
}
