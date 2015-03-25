package com.wang.spring.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import com.wang.spring.bean.DefinitionBean;


/**
 * 读取xml的帮助类
 * 
 * @author wangcanpei
 * 
 */
public class XmlUtil {
	
	
	
	    private static Element rootElement = null;  

	public static void main(String[] args) {
		// XStream xstream = new XStream(new DomDriver());
		// Map map = new HashMap();
		//
		// List list1 = new ArrayList();
		// list1.add(new T("a1", "b1", "c1"));
		// list1.add(new T("a2", "b2", "c2"));
		// List list2 = new ArrayList();
		// list2.add(new T("a3", "b3", "c3"));
		// list2.add(new T("a4", "b4", "c4"));
		// map.put("t1", list1);
		// map.put("t2", list2);
		//
		// xstream.alias("classes", Map.class);
		// xstream.alias("class", Map.Entry.class);
		// xstream.alias("name", String.class);
		//
		// xstream.alias("fields", List.class);
		// xstream.alias("field", T.class);
		// xstream.aliasAttribute(T.class, "a", "a");
		// xstream.aliasAttribute(T.class, "b", "b");
		// xstream.aliasAttribute(T.class, "c", "c");
		//
		// System.out.println(xstream.toXML(map));
		//
		// System.out.println((Map)xstream.fromXML(xstream.toXML(map)));

		// Map<String,String> map = new HashMap<String,String>();
		// XStream xstream = new XStream(new DomDriver());
		// xstream.alias("beans", List.class);
		// xstream.alias("bean",DefinitionBean.class);
		// xstream.aliasAttribute(DefinitionBean.class,"id","id");
		// xstream.aliasAttribute(DefinitionBean.class,"classPath","class");
		// xstream.alias("properties",Map.class);
		// xstream.aliasAttribute(T.class, "a", "a");
		// xstream.alias("entry", Map.Entry.class);
		// // xstream.alias("key",String.class);
		// // xstream.alias("value",String.class);
		// List list1 = new ArrayList();
		// DefinitionBean bean=new DefinitionBean();
		// bean.setId("id1");
		// bean.setClassPath("kkkkk");
		// Map<String,Object> properties=new HashMap<String,Object>();
		// properties.put("a", 1);
		// properties.put("b", 2);
		// bean.setProperties(properties);
		// list1.add(bean);
		// System.out.println(xstream.toXML(list1));
		Map<String,DefinitionBean> map=fromXml("beans.xml");
		
		System.out.println(map);
			
		 

	}
	
	/** 
     * 初始化文档对象 
     * @param filePath 
     */  
    public void loadXmlResource(String filePath) {  
        SAXBuilder builder = new SAXBuilder();  
        try {  
            Document doc = builder.build(new File(filePath));  
            rootElement = doc.getRootElement();  
        } catch (JDOMException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
    
    
    /** 
     * 一、使用JDOM读取单个的节点 
     * 采用Xpath路径读取 
     */  
    public String getElement(String path) {  
    	 String text="";
        try {  
        	 Element element = (Element) XPath.selectSingleNode(rootElement,path);  
             text=element.getText();
           // System.out.println(driver.getText());  
        } catch (JDOMException e) {  
            e.printStackTrace();  
        }  
        return text;
    }  
      
    /** 
     * 二、使用JDOM读取一个节点列表 
     */  
    public static List<?> getElements(String path,Element element){  
    	List<?> elementList =null;
        try {  
            elementList = XPath.selectNodes(element, path);  
          
        } catch (JDOMException e) {  
            e.printStackTrace();  
        }  
        return elementList;
    }  
  
    /** 
     * 三、使用JDOM创建Xml文件 
     */  
    public void createXmlExcample(){  
        Element root = new Element("selects");  
          
        Element select = new Element("select");  
        Element id = new Element("id");  
        // 设置值  
        id.addContent("1");  
        Element name = new Element("name");  
        name.addContent("小迪之家");  
        select.addContent(id);  
        select.addContent(name);  
        root.addContent(select);  
          
        Document document = new Document();  
        document.addContent(root);  
        // 使用XMLOutputter对象进行输出  
        XMLOutputter out = new XMLOutputter();  
        // 设置输出编码格式  
        out.setFormat(Format.getCompactFormat().setEncoding("GBK"));        
        String docStr = out.outputString(document);  
        System.out.println(docStr);  
        try {  
            out.output(document, new FileOutputStream("src/com/test.xml"));  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  

	/**
	 * 把xml读取成map类型的
	 * Map<String,DefinitionBean>
	 * @param xmlPath
	 * @return
	 */
	 public static Map<String,DefinitionBean> fromXml(String xmlPath){
		 Map<String,DefinitionBean> map=new HashMap<String,DefinitionBean>();
		 SAXBuilder sb = new SAXBuilder();  
	     try {
			Document doc =sb.build(XmlUtil.class.getClassLoader().getResourceAsStream(xmlPath));//read the xml file in classpath  
			rootElement=doc.getRootElement();  
			List<?> elementList=getElements("//beans/bean",rootElement);//得到bean的列表
			  for(Object e : elementList){  
				  DefinitionBean definitionBean=new DefinitionBean();
	                Element element =  (Element)e;  
	                String id = element.getAttributeValue("id");  
	                String clszz = element.getAttributeValue("class"); 
	                definitionBean.setClassPath(clszz);
	                definitionBean.setName(id);
	                System.out.print(id + "\t" + clszz);  
	                System.out.println(); 
	                //读取属性的值
	              List<?> propertiesList=getElements("properties",element);//得到bean的列表
	              Map<String,Object> properties=new HashMap<String,Object>();
	  			  for(Object el : propertiesList){
	  	                Element propertie =  (Element)el;//得到属性配置
	  	                String name = propertie.getAttributeValue("name");//属性名
	  	                String propertieValue = propertie.getAttributeValue("value"); //属性所对应的value值
	  	                String propertieRef = propertie.getAttributeValue("ref"); //属性所对应的value值
	  	                if(StringUtils.isNotBlank(propertieValue)){
	  	                	 properties.put(name, propertieValue+"&value");//当有class的时候，属性就直接可以赋值
	  	                }else if(StringUtils.isNotBlank(propertieRef)){
	  	                	 properties.put(name, propertieRef+"&ref");//当有class的时候，属性就直接可以赋值
	  	                }else{//当属性是map
	  	                	Map<String,String> entryMap=new HashMap<String,String>();
	  	                	List<?> entryList=getElements("map/entry",propertie);//得到map
	  	                	for(Object entryEl:entryList){
	  	                		Element entryE =  (Element)entryEl;//得到属性配置
	  	                		entryMap.put(entryE.getAttributeValue("key"), entryE.getChildText("value"));
	  	                		properties.put(name, entryMap);
	  	                	}  
	  	                }
	  	                
	  	            }
	  			definitionBean.setProperties(properties);
	  			map.put(id, definitionBean);
	            }  
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return map;
	 }
}
