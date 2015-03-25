package com.wang.spring.test;

import com.wang.spring.bean.DefinitionBean;
import com.wang.spring.factory.BeanScopeFactory;

public class ScopeFactoryTest {

	public static void main(String[] args) {
		//1.创建Bean工厂
		BeanScopeFactory bf = new BeanScopeFactory();
		//2.创建原型 Bean定义
		DefinitionBean bd = new DefinitionBean();
		bd.setId("bean");
		bd.setScope(DefinitionBean.SCOPE_PROTOTYPE);
		bd.setClassPath(User.class.getName());
		bf.registerBeanDefinition(bd);
		//对于原型Bean每次应该返回一个全新的Bean
		System.out.println(bf.getBean("bean"));
		System.out.println(bf.getBean("bean"));
		System.out.println(bf.getBean("bean") == bf.getBean("bean"));

	}
}
