package rev.orm.daos;

import java.lang.reflect.InvocationTargetException;
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
			
			System.out.println(newObject);
			
			String sql = "SELECT * FROM " + objReflection.returnObjectClassName(obj).toLowerCase()
					+ " WHERE "+ objFieldsDB[columnNum-1] +" = "+ "'" + content + "'" + ";";
			//System.out.println(sql);
			Statement statement = connect.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			if(result.next()) {
				String setterName = "set" +objFields[0].substring(0,1).toUpperCase()
						+ objFields[0].substring(1);
				
				Class<?> setterParamType = obj.getClass().getDeclaredField(objFields[0]).getType();
				System.out.println(setterName);
				System.out.println(setterParamType);
				int counter = 0;
				for(String str: objParamType) {
					//makes a switch statement here and also make a method for these steps
					if(str.equals("String")){
						
					}else if(str.equals("int")) {
						
					}
				}
				
			}
			
			return newObject;
			
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

		System.out.println("========================");
		
		ObjectReflection or = new ObjectReflection();
		
		System.out.println(or.returnObjectClassName(account).toLowerCase());
		System.out.println("========================");
		for(String str: or.returnDeclaredFieldsDB(account)) {
			System.out.println(str);
		}	
//		String[] ty = or.returnDeclaredFields(account);
//		System.out.println(ty[0]);
		System.out.println("========================");
		for(String str: or.returnParameterType(account)) {
			System.out.println(str);
		}
		ObjectDAO odjDao = new ObjectDAOImpl();
		
		odjDao.getByContentByColumnNum("Alice",1,account);


	}




}
