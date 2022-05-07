package com.edu;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection 
{
	static String driver="com.mysql.cj.jdbc.Driver";
	static String url="jdbc:mysql://localhost:3306/servletdb";
	static String un="root";
	static String up="root";
	static Connection con=null;
	public static Connection getconnection()
	{
		try
		{
			Class.forName(driver);
			con=DriverManager.getConnection(url,un,up);
			if(con==null)
			{
				System.out.println("connection is null");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
		
	}
	

}
