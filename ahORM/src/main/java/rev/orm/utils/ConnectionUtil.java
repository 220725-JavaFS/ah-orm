package rev.orm.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @author Work
 * sets up an active connection with a database
 */
public class ConnectionUtil {
	
	private static Connection connection;	
	
	public static Connection getConnection() throws SQLException {
		
		if(connection!=null && !connection.isClosed()) {
			return connection;
		}else {
			
			try {	
				Class.forName("org.postgresql.Driver");	
			}catch(ClassNotFoundException e){
				e.printStackTrace();	
			}
			//jdbc:postgresql://<dbURL>:5432/<dbName>
			//replace getConnection() parameters by setting up a configuration file.
			connection = DriverManager.getConnection(System.getenv("DB_URL"), System.getenv("DB_UN"),System.getenv("DB_PWD"));
			
			return connection;
		}
		
		
		
	}
}
