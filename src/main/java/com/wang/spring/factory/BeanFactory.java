package com.wang.spring.factory;

/**
 * bean的工厂类，用来生成bean
 * @author wangcanpei
 *
 */
public interface BeanFactory {

	/**
	 * 根据name获取bean
	 * @param name
	 * @return
	 */
	Object getBean(String name);
}
