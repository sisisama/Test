package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass 
{
	public static Connection myConnection()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StudentManagementMVC","root","rootroot");
		}catch (ClassNotFoundException e) 
		{
			System.out.println("Driver class not found");
		} catch (SQLException e) 
		{
			System.out.println("Database Coonectin not found"); 
		}
		return con;
	}
}
