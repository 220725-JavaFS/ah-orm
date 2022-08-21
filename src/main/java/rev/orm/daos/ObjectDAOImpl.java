package rev.orm.daos;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rev.orm.models.Account;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//testing remove b4 final code
	public static void main(String[] args) {
		
		Account account = new Account();
		ObjectDAO odjDao = new ObjectDAOImpl();
		
		System.out.println(odjDao.getByContentByColumnNum("Alice",1,account));
		System.out.println(odjDao.getByContentByColumnNum("Alice",2,account));
		System.out.println(odjDao.getByContentByColumnNum("Bob",2,account));
		System.out.println(odjDao.getByContentByColumnNum("17401",5,account));

	}




}
