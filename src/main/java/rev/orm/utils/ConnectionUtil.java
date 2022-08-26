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
	private static ConfigUtil cfg = new ConfigUtil();
	public static Connection getConnection() throws SQLException {
		
		if(connection!=null && !connection.isClosed()) {
			return connection;
		}else {
			
			try {
				
				Class.forName("org.postgresql.Driver");	
			}catch(ClassNotFoundException e){
				e.printStackTrace();	
			}
			cfg.startConfig();
			connection = DriverManager.getConnection(cfg.getDatabaseURL(),cfg.getDatabaseUsername(),
					cfg.getDatabasePWD());
			
			return connection;
		}
		
		
		
	}
	
}
