package com.mysqld;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;



public class  Mysqld{

	
	static Connection con=DBUtil.con;
	//写数据库的读写 3 
	//增加   （删  改   查）  3
	public static int  upDate(String sqlStr,String data[]) { //增加数据  传过来数据库语句 和一个 参数
		
		PreparedStatement preSql;//select * from ? (数据库名字)
		int num;
		try {
			
			preSql=con.prepareStatement(sqlStr);
			if(data==null) {
				num=preSql.executeUpdate();//返回一个执行成功的数子
			}else {
				
				
				for(int i=0;i<data.length;i++) {
					//遍历传入的data数据 把他存入 数据库语句   
					preSql.setString(i+1,data[i]);
					
				}
				System.out.println(preSql);
				num=preSql.executeUpdate();//返回一个执行成功的数子
				
			}

			return num;//如果数字 就>0就是执行成功   -1 就是失败  

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}

	}
	
	//执行查找数据
	public static ResultSet  QueryData(String sqlStr,String data[]) {
		
		ResultSet rs = null;
		PreparedStatement preSql;
		try {
			
			preSql=con.prepareStatement(sqlStr);
			
			if(data!=null) {
				for(int i=0;i<data.length;i++) {
					//遍历传入的data数据 把他存入 数据库语句   
					preSql.setString(i+1,data[i]);
				}

			}

			System.out.println(preSql);
			rs=preSql.executeQuery();
	
			return rs;//如果数字 就>0就是执行成功   -1 就是失败  

		} catch (SQLException e) {
			// TODO: handle exception
			//e.printStackTrace();
			return rs;
		}
		
	}

}
