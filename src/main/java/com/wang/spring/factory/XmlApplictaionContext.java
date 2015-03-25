package com.wang.spring.factory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.wang.spring.bean.DefinitionBean;
import com.wang.spring.util.XmlUtil;

/**
 * 用来加载xml里的信息，并且把里面的配置加载到内存中
 * @author wangcanpei
 *
 */
public class XmlApplictaionContext implements BeanFactory{
	
	private Map<String,Object> beanMap=new HashMap<String,Object>();

	public Object getBean(String name) {
		if(beanMap.isEmpty()){
			return null;
		}
		return beanMap.get(name);
	}

	/**
	 * 读取配置文件，并且把
	 * @param xmlPath
	 */
	public XmlApplictaionContext(String xmlPath) {
		super();
		Map<String,DefinitionBean> definitionBeanMap=XmlUtil.fromXml("beans.xml");
		//对definitionBeanMap进行解析，然后通过反射来加载里面的信息
		try {
			generateBeanMap(definitionBeanMap);
		} catch (SecurityException e) {
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
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成beanMap，根据反射来生成
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IntrospectionException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	protected void generateBeanMap(Map<String,DefinitionBean> definitionBeanMap) throws SecurityException, IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		/**
		 * 1、实例化本bean，
		 * 2、把生成的bean放到beanMap里
		 * 3、读取相应的属性
		 * 4、再给每一个bean里设置相应的属性值
		 */
		//实例化bean
		 Set<Map.Entry<String, DefinitionBean>>  set=definitionBeanMap.entrySet();
		 for(Map.Entry<String, DefinitionBean> entry:set){
			 DefinitionBean definittionBean=entry.getValue();
			 String name=definittionBean.getName();
			 String clazz=definittionBean.getClassPath();
			 try {
				Class<? extends Object> c = Class.forName(clazz);
				Object object=c.newInstance();
				beanMap.put(name, object);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		
		//实例化里面的属性.在map里只配置简单属性
		 for(Map.Entry<String, DefinitionBean> entry:set){
			
			 DefinitionBean definittionBean=entry.getValue();
			 String name=definittionBean.getName();
			 Object bean= beanMap.get(name);//实例化过的bean对象
			 String clazz=definittionBean.getClassPath();
			 Class<? extends Object> c=null;
			 try {
				c = Class.forName(clazz);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 Map<String, Object> propertiesMap=definittionBean.getProperties();
			 Set<Map.Entry<String, Object>>  propertiesSet=propertiesMap.entrySet();
			 for(Map.Entry<String, Object> properties:propertiesSet){
				 String propertiesName=properties.getKey();
				 Object object=properties.getValue();
				 Field field=null;
				try {
					field = c.getDeclaredField(propertiesName);
					 field.setAccessible(true);
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(field==null){
					continue;
				}
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), c);//得到属性
				
				 
				 Method wM= pd.getWriteMethod();//得到该属性的set方法
				 if(object instanceof Map){//当是map时
					 wM.invoke(bean, (Map)object);//给属性设置值
				 }else{
					 String strValue=String.valueOf(object);
					 String[] strValues=strValue.split("&");
					 if("ref".equals(strValues[1])){
						 Object propertiesBean= beanMap.get(strValues[0]);//实例化过的bean对象
						 wM.invoke(bean, propertiesBean);
					 }else{
						 wM.invoke(bean, strValues[0]);
					 }
				 }
			 } 
		 }
		
	} 
	
	
	/**
	 * 读取xml配置，来解析bean
	 * 把bean放到beanMap里
	 * @param xmlPath
	 */
	private void readXml(String xmlPath){
		
	}
	
	/**
	 * 刷新，每一次加载的时候都把beanMap清空，并且重新加载所有的数据
	 */
	protected void refresh() {
		//TODO 
	}
}
