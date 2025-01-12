package com.tapfoods.dbUtils;

import java.sql.Connection;
import java.sql.DriverManager;

final public class DBUtils {
	private static Connection con=null;
	private static String url="jdbc:mysql://localhost:3306/tapfoods";
	private static String username="root";
	private static String password="root";
	
	public static Connection myConnect()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
}
