package rev.orm.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ObjectReflection {
	
	/*
	 * Takes the object and returns the parameter types in an array from the objects constructor
	 * 
	 */
	public String[] returnParameterType(Object o){
		
		Class<?> objectClass = o.getClass();
		Constructor<?>[] publicConstructors = objectClass.getConstructors();
		
		for(Constructor<?> c: publicConstructors) {
			//add a feature that will take an object 
			if(c.getParameterCount() > 0) {
				String[] newArr = new String[c.getParameterCount()];
				int temp = 0;
				Class<?>[] pType  = c.getParameterTypes();
				for (Class<?> t : pType) {
					newArr[temp] = t.getSimpleName();
					temp++;
				}
				
				return newArr;
			}
			

		}

		return null;
	}
	
	public String[] returnDeclaredFields(Object o){
		
		Class<?> objectClass = o.getClass();
		Field[] fields = objectClass.getDeclaredFields();
		if(fields.length > 0) {
			String[] newArr = new String[fields.length];
			int count = 0;
			for(Field field: fields) {		
				newArr[count] = field.getName();
				count++;
			}
			return newArr;
		}
	
		return null;	
	}
	
	public String[] returnDeclaredFieldsDB(Object o){
		
		Class<?> objectClass = o.getClass();
		Field[] fields = objectClass.getDeclaredFields();
		
		if(fields.length > 0) {
			String[] newArr = new String[fields.length];
			int count = 0;
			for(Field field: fields) {		
				String fieldName = field.getName();

				//seprates the field names by CamelCase and inserts a _ to match DB varibles
				String temp = "";
				for(int i = 0; i < fieldName.length(); i++) {
					Character ch = fieldName.charAt(i);
					if(Character.isUpperCase(ch))
						temp += "_" + Character.toLowerCase(ch);
					else
						temp += ch;
				}
				newArr[count] = temp;
				count++;
			}
			return newArr;
		}
	
		return null;	
	}
	
	public String returnObjectClassName(Object o) {
		Class<?> objectClass = o.getClass();
		Field[] fields = objectClass.getDeclaredFields();
		if(fields.length > 0) {
			return objectClass.getSimpleName();
		}
		return null;
	}
}
