package com.cfw.movies.commons.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * Simple assign class, to assign new PO's value to old PO. 
 * @author CaiFangwei
 * @time since Dec 4, 2015 3:09:53 PM
 */
public class SimpleAssign {
	// Seems useless.
	private static final String [] setterIdentifierNames = {
			"Short","int","Integer","Long",
			"String","Boolean","Date","Double",
			"float","Float","double"
		};
	// Get method identifier.
	private static final String [] isIdentifierNames = {"Boolean"};
	
	
	/**
	 * <b>Main method to assign</b>.<p>
	 * Attempting to use this method, something should keep in mind is that:<br>
	 * 1: This method just invoke given object's setter method to assign value,<br>
	 * and a general setter method always need one parameter, you should observe this.<br>
	 * 2: Second but important. You should use your IDE to create your POJO's getter or <br>
	 * setter methods. It's important here. We use PO's attribute name to find its<br>
	 * setter method, for example, if attribute name is "attribute" and your setter method<br>
	 * should like "setAttribute(...)". 
	 * 
	 * @author CaiFangwei
	 * @time since Dec 4, 2015 3:12:43 PM
	 * @param paramList
	 * @param paramPO
	 * @param oldPO
	 * @return
	 * @throws Exception
	 */
	public static <T> T assignValue(String [] paramList,T paramPO,T oldPO){
		
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) oldPO.getClass();
		
		try{
			Field [] fields = getFields(paramList,clazz);
			
			Method method = null;
			for(Field field : fields){
				Object value = field.get(paramPO);
				
				if(value==null) continue;
				
				String methodName = createMethodName(field,false);
				//if(methodName==null) continue;
				method = clazz.getDeclaredMethod(methodName, field.getType());
				if(!Modifier.isPublic(method.getModifiers())) continue;
				
				method.invoke(oldPO, value);
			}			
		}catch(Exception e){
			e.printStackTrace();
			oldPO = null;
		}
		
		return oldPO;
	}
	
	/**
	 * Assign the value of obejct's attribute into map parameter.
	 * @author Fangwei_Cai
	 * @time since 2016年4月24日 下午3:56:01
	 * @param map
	 * @param object
	 */
	public static <T> boolean assignValueToMap(Map<String,Object> map,T object){
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) object.getClass();
		Field [] fields = getFields(null,clazz);
		try{
			for(Field field : fields){
				map.put(field.getName(), field.get(object));
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月24日 下午4:24:38
	 * @param paramList
	 * @param clazz
	 * @return
	 */
	public static <T> Field[] getFields(String [] paramList, Class<T> clazz ){
		Field [] fields = null;
		if(paramList == null){
			fields = clazz.getDeclaredFields();
			
		}else{				
			try{
				// Get the value out from given POJO parameter and store into map collection for further process.
				fields = new Field[paramList.length];
				for(int i=0;i<paramList.length;i++){
					fields[i] = clazz.getDeclaredField(paramList[i]);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		if(fields != null){
			Field.setAccessible(fields, true);
		}
		
		return fields;
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月24日 下午4:24:49
	 * @param simpleIdentiferName
	 * @return
	 */
	@Deprecated
	private static boolean setterIdentiferHas(String simpleIdentiferName){
		for(String identiferName : setterIdentifierNames){
			if(simpleIdentiferName.equalsIgnoreCase(identiferName))
				return true;
		}
		
		return false;
	}

	/**
	 * @author Fangwei_Cai
	 * @create 2016年7月11日14:36:59
	 * @param isIdentifierName
	 * @return
     */
	private static boolean isIdentifierHas(String isIdentifierName){
		for(String identifierName : isIdentifierNames){
			if(identifierName.equalsIgnoreCase(isIdentifierName))
				return true;
		}

		return false;
	}

	/**
	 * Use visible field to create corresponding setter method.
	 * @author Fangwei_Cai
	 * @create 2016年4月24日 下午4:24:43
	 * @modified 2016年7月11日14:24:34
	 * @param field
	 * @param isGet False in default to create Setter method.
     * @return
     */
	public static String createMethodName(Field field, boolean isGet){
		// Get the property type/identifier first then create the method name.
		Class<?> identifer = field.getType();
		String simpleIdentiferName = identifer.getSimpleName();
		
		String attributeName = field.getName();
		
		String methodName = null;
		char [] attributeNameChar = attributeName.toCharArray();
		attributeNameChar[0] = attributeName.toUpperCase().charAt(0);
		
		String upperAttributeName = new String(attributeNameChar);
		
		if(isGet){
			// To create Getter method.
			if(isIdentifierHas(simpleIdentiferName)){
				methodName = "is";
			}else{
				methodName = "get";
			}
			methodName += upperAttributeName;
		}else{
			// To create Setter method
			methodName = "set"+upperAttributeName;
		}
		
		return methodName;
	}
	
}
