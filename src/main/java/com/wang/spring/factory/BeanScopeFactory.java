package com.wang.spring.factory;

import java.lang.reflect.InvocationTargetException;

import com.wang.spring.bean.DefinitionBean;

public class BeanScopeFactory {
	// Bean定义注册表
	private BeanDifinitionRegister DEFINITIONS = new BeanDifinitionRegister();

	// 单例注册表
	private final SingletonBeanRegister SINGLETONS = new SingletonBeanRegister();

	public Object getBean(String beanName) {
		DefinitionBean bd = DEFINITIONS.getBeanDefinition(beanName);
		if (DefinitionBean.SCOPE_PROTOTYPE == bd.getScope()) {
			return createBean(bd);
		} else {
			if (!SINGLETONS.containsSingleton(bd.getId())) {
				Object bean=createBean(bd);
				SINGLETONS.registerSingleton(bd.getId(), bean);
			}
			return SINGLETONS.getSingleton(beanName);
		}

	}

	public void registerBeanDefinition(DefinitionBean bd) {
		DEFINITIONS.registerBeanDefinition(bd.getId(), bd);
	}

	private Object createBean(DefinitionBean bd) {
		// 根据Bean定义创建Bean
		Object obj = null;
		try {
			Class clazz = Class.forName(bd.getClassPath());
			// 通过反射使用无参数构造器创建Bean
			try {
				obj = clazz.getConstructor().newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("没有找到Bean[" + bd.getId() + "]类");
		}
		return obj;
	}
}
