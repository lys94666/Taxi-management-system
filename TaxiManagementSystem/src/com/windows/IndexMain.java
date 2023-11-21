package com.windows;

import com.mysqld.DBUtil;

public class IndexMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DBUtil D=new DBUtil("root", "chenxiaofeng", "carman");//数据库账号，数据库密码，数据库名字
		Login window = new Login();
		window.frame.setVisible(true);
	}

}
