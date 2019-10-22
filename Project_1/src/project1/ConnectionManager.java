package project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	public Connection connection() throws ClassNotFoundException, SQLException
	{
	Class.forName("com.mysql.jdbc.Driver");  
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","");  
	
	if (con != null)
	{
		System.out.println("Connected");
	}
	else
	{
		System.out.println("not Connected");
	}
	return con;
	}
	}

