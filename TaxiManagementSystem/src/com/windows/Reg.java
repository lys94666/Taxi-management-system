package com.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mysqld.Mysqld;
import com.tools.Tools;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reg {

	public  JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JPasswordField passwordField;


	public Reg() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("注册界面");
		frame.setBounds(100, 100, 270, 232);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("账号");
		lblNewLabel.setBounds(69, 10, 58, 15);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(137, 7, 66, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setBounds(69, 41, 58, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("姓名");
		lblNewLabel_2.setBounds(69, 71, 58, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(137, 69, 66, 21);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("注册账号");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String data[]= {
						textField.getText(),
						new  String(passwordField.getPassword()),
						"2",
						textField_2.getText()
						
				};
				if(textField.getText().equals("")) {
					Tools.messageWindows("账号不能为空");
				}else if(new  String(passwordField.getPassword()).equals("")){
					Tools.messageWindows("密码不能为空");
				}else if(textField_2.getText().equals("")){
					Tools.messageWindows("姓名不能为空");
				}else {
					
					int a= Mysqld.upDate("insert into admin VALUES (?,?,?,?)", data);
					if(a==1) {
						Tools.messageWindows("注册成功");
					}else {
						Tools.messageWindows("注册失败");
					}
				}
				
			}
		});
		btnNewButton.setBounds(96, 118, 97, 23);
		frame.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(137, 38, 68, 21);
		frame.getContentPane().add(passwordField);
	}
}
