package cbrdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private String url="jdbc:postgresql://localhost:5432/casebase";
	private String username="postgres";
	private String password = "postgres";
	
	public Database(){
		
	}
	
	public Connection getConnection(){
		Connection conn=null;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}
