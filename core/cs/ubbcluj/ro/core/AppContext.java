package cs.ubbcluj.ro.core;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.management.ServiceNotFoundException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AppContext {

	public static final String BEANS = "beans";
	public static final String BEAN = "bean";
	public static final String PROPERTY = "property";
	
	protected static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	static Log log = LogFactory.getLog(AppContext.class.getSimpleName());
	
	static class BeanInfo{
		Object bean;
		Map<String,String> props = new HashMap<>();
	}
	
	private HashMap<String,BeanInfo> beans;
	
	public AppContext(String filename){
		XMLInputFactory factory = XMLInputFactory.newFactory();
		try{
			InputStream fileInputStream = ClassLoader.getSystemResourceAsStream(filename);
			if(fileInputStream==null){
				//LOGGER.severe("App context - file not find");
				log.error("App context - file not found");
				throw new ServiceNotFoundException("App context file not found");
				
			}
			XMLStreamReader reader = factory.createXMLStreamReader(fileInputStream);
			BeanInfo currentBeanInfo = null;
			while(reader.hasNext()){
				reader.next();
				int eventType = reader.getEventType();
				String elementName = null;
				switch(eventType){
				case XMLStreamReader.START_ELEMENT:
					elementName = reader.getLocalName();
					if(elementName.equals(BEANS)){
						beans = new HashMap<String,BeanInfo>();
					}else if(elementName.equals(BEAN)){
						String id = reader.getAttributeValue(null,"id");
						String className = reader.getAttributeValue(null,"class");
						currentBeanInfo = createBean(id,className);
						log.info("Element "+elementName+" "+className);
					}else if(elementName.equals(PROPERTY)){
						String name = reader.getAttributeValue(null,"name");
						String ref = reader.getAttributeValue(null,"ref");
						currentBeanInfo.props.put(name, ref);
						log.info("Element "+elementName+" "+name);
					}
					break;
				case XMLStreamReader.END_ELEMENT:
					elementName = reader.getLocalName();
					if(elementName.equals(BEANS)){
						bindBeans();
					}else if(elementName.equals(BEAN)){
						
					}else if(elementName.equals(PROPERTY)){
						
					}
					break;
				}
			}
			log.info("App context created");
		}catch(XMLStreamException e){
			//LOGGER.severe("App context - invalid format"+e.getMessage());
			log.error("App context - invalid format",e);
			throw new ServiceException(e);
		} catch (ServiceNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void bindBeans(){
		for(BeanInfo bi:beans.values()){
			for(String fieldName:bi.props.keySet()){
				log.info("bindBeans "+fieldName+" ");
				Object fieldValue = beans.get(bi.props.get(fieldName)).bean;
				log.info("bindBeans "+fieldName+" with "+fieldValue);
				Object bean = bi.bean;
				
				try {
					Field field = getFieldRec(bean.getClass(),fieldName);
					field.setAccessible(true);
					field.set(bean, fieldValue);
				} catch (NoSuchFieldException e) {
					log.error("No such field "+ fieldName+ " for "+bean.getClass().getSimpleName(),e);
					throw new ServiceException(e);
				} catch (IllegalAccessException e) {
					log.error("Illegal access "+fieldName,e);
					throw new ServiceException(e);
				}
			}
		}
	}

	private Field getFieldRec(Class<? extends Object> class1, String fieldName) throws NoSuchFieldException {
		try {
			return class1.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			if (class1.getSuperclass()==null){
				throw e;
			}else{
				return getFieldRec(class1.getSuperclass(),fieldName);
			}
		}
		
	}
	
	private BeanInfo createBean(String id,String className){
		try {
			Object bean = Class.forName(className).newInstance();
			BeanInfo bi = new BeanInfo();
			bi.bean = bean;
			beans.put(id, bi);
			return bi;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public <T> T getBean(Class<T> clazz){
		for(BeanInfo bi:beans.values()){
			if(clazz.isInstance(bi.bean)){
				return extracted(bi);
			}
		}
		return null; //bean.get();
	}

	@SuppressWarnings("unchecked")
	private <T> T extracted(BeanInfo bi) {
		return (T) bi.bean;
	}
	
}
