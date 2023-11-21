package com.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputListener;

import com.mysqld.DBUtil;
import com.mysqld.Mysqld;
import com.other.EasyCode;
import com.tools.Tools;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login {

	public  JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;


	public Login() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("汽车出租管理系统");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//自动隐藏并释放该窗体
		frame.getContentPane().setLayout(null);//用getContentPane()方法获得JFrame的内容面板
											   //setLayout(null)表示设为绝对布局
											   //不管如何改变窗体都不会改变已固定组件的坐标及大小
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 416, 243);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("汽车出租管理系统");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 34));
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBounds(64, 10, 342, 74);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("账号");
		lblNewLabel_1.setBounds(90, 94, 58, 18);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(157, 91, 113, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("密码");
		lblNewLabel_2.setBounds(90, 131, 58, 18);
		panel.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(157, 128, 113, 21);
		panel.add(passwordField);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String data[]= {
						textField.getText(),
						Tools.getPassword(passwordField)
					
				};
				String sqlString="select * from admin where account=? and password=?";
				int a=EasyCode.isLogin( sqlString, data);
				if(a==1) {
					//登录成功
					ResultSet rs = Mysqld.QueryData(sqlString, data);
					try {
						while(rs.next()) {
							
							if(rs.getString(3).equals("1")){
								//登录管理员
								ManCar window = new ManCar();
								window.frame.setVisible(true);
								frame.dispose();
							}else {
								//登录普通用户
								UseMan window = new UseMan(textField.getText());
								window.frame.setVisible(true);
								frame.dispose();
							}
						}
						rs.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}else {
					//登录失败
					Tools.messageWindows("密码错误或者账号错误");
					
				}

			}
		});
		btnNewButton.setBounds(157, 176, 97, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("注册账号>");
		lblNewLabel_3.setBounds(328, 200, 80, 15);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.addMouseListener(new MouseInputListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Reg window = new Reg();
				window.frame.setVisible(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
