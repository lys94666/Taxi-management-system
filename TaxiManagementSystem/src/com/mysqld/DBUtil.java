package com.mysqld;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.tools.Tools;



public class DBUtil {

	//定义一个可以调用连接
	public static Connection con=null;
	
	
	//连接数据库
	//账号  密码  数据库 名字 
	public DBUtil(String account,String password,String datbasName) {//数据库的账号，数据库的密码 ，数据库的名字
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("加载驱动成功");

			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("加载驱动失败");
		
			e.printStackTrace();
		}
		
		String url="jdbc:mysql://localhost:3306/"+datbasName+"?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
		
		//连接数据库
		try {
			con=DriverManager.getConnection(url,account, password);
			
			System.out.println("连接数据库成功");
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("链接数据库失败");
			String temp=e.getMessage();
			System.out.println(temp);
			String[] arr1=temp.split(" ");
			if(arr1[0].equals("Unknown")) {
				System.out.println("请建立名字为："+arr1[2]+"数据库");
			}
			if(arr1[0].equals("Access")) {
				System.out.println("请检查数据库密码是否正确：数据库密码错误");
			}
			if(temp.contains("the server was 0 milliseconds ago")){
				System.out.println("请安装Mysql数据库");
			}
		
		}

	}
	
}
