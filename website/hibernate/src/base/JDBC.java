package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	public static Connection conn;
	
	public static void connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/web";
		    String user = "root";
		    String password = "admin";
		    conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	
	public void execute(String sql){
		try{
			if(conn == null){
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite://c://myworkspace//web//test.db");
			}
			Statement stat = conn.createStatement();
			stat.executeUpdate(sql);
		}catch(SQLException e){
			System.out.println(e);
		}catch(ClassNotFoundException e){
			
		}
	}
	
	public static void main(String args[]){
		JDBC.connect();
	}

}
