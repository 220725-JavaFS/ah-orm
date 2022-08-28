package rev.orm.daos;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import rev.orm.utils.ConnectionUtil;
import rev.orm.utils.ObjectReflection;

public class ObjectDAOImpl implements ObjectDAO {
	
	private ObjectReflection objReflection = new ObjectReflection();
	@Override
	public Object getByContentByColumnNum(String content, int columnNum, Object obj){
		
		Object newObject = null;
		String[] objFields = objReflection.returnDeclaredFields(obj);
		String[] objFieldsDB = objReflection.returnDeclaredFieldsDB(obj);
		String[] objParamType = objReflection.returnParameterType(obj);
		
		try(Connection connect = ConnectionUtil.getConnection()) {
			
			newObject = obj.getClass().getDeclaredConstructor().newInstance();
		
			String sql = "SELECT * FROM " + objReflection.returnObjectClassName(obj).toLowerCase()
					+ " WHERE "+ objFieldsDB[columnNum-1] +" = "+ "'" + content + "'" + ";";
			Statement statement = connect.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if(result.next()) {
				int counter = 0;

				for(String str: objParamType) {
					String setterName = "set" +objFields[counter].substring(0,1).toUpperCase()
							+ objFields[counter].substring(1);
					Class<?> setterParamType = obj.getClass().getDeclaredField(objFields[counter]).getType();
					Method setter = obj.getClass().getMethod(setterName, setterParamType);
					//makes a switch statement here and also make a method for these steps
					//Refactor this code latter ^ and add the necessary primitives 
					if(str.equals("String")){
						setter.invoke(newObject,result.getString(objFieldsDB[counter]));

					}else if(str.equals("int")) {
						setter.invoke(newObject,result.getInt(objFieldsDB[counter]));
					
					}
					counter++;
				}
				return newObject;
			}
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | SQLException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Object> getAll(Object obj) {
		
		Object newObject = null;
		List<Object> newObjectList = new LinkedList<Object>();
		String[] objFields = objReflection.returnDeclaredFields(obj);
		String[] objFieldsDB = objReflection.returnDeclaredFieldsDB(obj);
		String[] objParamType = objReflection.returnParameterType(obj);

		try(Connection connect = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM " + objReflection.returnObjectClassName(obj).toLowerCase() + ";";
			Statement statement = connect.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				int counter = 0;
				newObject = obj.getClass().getDeclaredConstructor().newInstance();
				for(String str: objParamType) {
					String setterName = "set" +objFields[counter].substring(0,1).toUpperCase()
							+ objFields[counter].substring(1);
					Class<?> getterParamType = obj.getClass().getDeclaredField(objFields[counter]).getType();
					Method setter = obj.getClass().getMethod(setterName, getterParamType);
					//makes a switch statement here and also make a method for these steps
					//Refactor this code latter ^ and add the necessary primitives 
					if(str.equals("String")){
						setter.invoke(newObject,result.getString(objFieldsDB[counter]));

					}else if(str.equals("int")) {
						setter.invoke(newObject,result.getInt(objFieldsDB[counter]));
					
					}
					counter++;
				}
				newObjectList.add(newObject);
				
			}
			return newObjectList;
			
		} catch (SQLException | NoSuchFieldException | SecurityException | NoSuchMethodException | IllegalAccessException 
				| IllegalArgumentException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public void storeObject(Object obj) {
		
		String[] objFields = objReflection.returnDeclaredFields(obj);
		String[] objFieldsDB = objReflection.returnDeclaredFieldsDB(obj);
		String[] objParamType = objReflection.returnParameterType(obj);
		
		try(Connection connect = ConnectionUtil.getConnection()) {
			
	
			StringBuilder builder = new StringBuilder("INSERT INTO " + objReflection.returnObjectClassName(obj).toLowerCase()+
					"(");
			int temp = 1;
			for(String field: objFieldsDB) {
				
				builder.append(field);
				if(objFieldsDB.length > temp) {
					builder.append(", ");
					++temp;
				}
			}
			builder.append(") VALUES(");
			for(int i = 0; i < objFieldsDB.length; i++) {
				
				builder.append("?");
				if(objFieldsDB.length > i+1) {
					builder.append(", ");
					++temp;
				}
			}
			builder.append(");");
			
			String sql = builder.toString();

			PreparedStatement statement = connect.prepareStatement(sql);
			int counter = 0;
			for(String field: objFields) {

				String getterName = "get" +field.substring(0,1).toUpperCase()+ field.substring(1);
				Method getterMethod = obj.getClass().getMethod(getterName);
				Object fieldValue = getterMethod.invoke(obj);
				
				if(objParamType[counter].equals("String")){
					statement.setString(counter+1, fieldValue.toString());

				}else if(objParamType[counter].equals("int")) {
					statement.setInt(counter+1, (int)fieldValue);
				}
				counter++;
			}
			
			statement.execute();
	
		} catch (IllegalArgumentException | SecurityException | SQLException | NoSuchMethodException | 
				IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Object updateRowByColumnNum(String setContent, int setColumnNum, String whereContent, int whereColumnNum, Object obj) {
		
		String[] objFieldsDB = objReflection.returnDeclaredFieldsDB(obj);
		
		try(Connection connect = ConnectionUtil.getConnection()) {
			
			String sql = "UPDATE " + objReflection.returnObjectClassName(obj).toLowerCase()+
					" SET " + objFieldsDB[setColumnNum-1] + "= '" + setContent + "' WHERE " + ""
							+ objFieldsDB[whereColumnNum-1] + "= '" + whereContent + "';";
			
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Object deleteRowByColumnNum(String whereContent, int whereColumnNum, Object obj) {
		String[] objFieldsDB = objReflection.returnDeclaredFieldsDB(obj);

		try(Connection connect = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM " + objReflection.returnObjectClassName(obj).toLowerCase()+
					" WHERE " + objFieldsDB[whereColumnNum-1] + "= '" + whereContent + "';";
			
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}


}
