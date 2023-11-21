package com.windows;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysqld.DBUtil;
import com.mysqld.Mysqld;
import com.other.EasyCode;
import com.tools.Table;
import com.tools.Tools;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UseMan {

	public  JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	String account;
	private JTextField textField_2;


	public UseMan(String account) {
		this.account=account;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("用户管理界面");
		frame.setBounds(100, 100, 1016, 526);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 982, 479);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("汽车车牌");
		lblNewLabel.setBounds(10, 10, 58, 15);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(78, 7, 66, 21);
		panel.add(textField);
		textField.setColumns(10);
	
		//________________________________________________________
		
		Object columns[] ={"汽车车牌","汽车车型","汽车款式","汽车颜色","租车价格"};//创建表格
					
		Table t1Table=new Table(columns);
		JTable table = t1Table.getTables();
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		JS.setPreferredSize(new Dimension( 600,280));//设置整个滚动条窗口的大小
		JS.setBounds(10, 54, 950, 200);
				
				
		JButton btnNewButton = new JButton("查找车辆");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textField.getText().equals("")) {
					EasyCode.showAllData("select * from carmes", 5, model);
				}else {
					String data[]= {
							textField.getText()
					};
				
				ResultSet rs = Mysqld.QueryData("select * from carmes where id=?",data);
				Tools.addDataTable(rs, model, 4);
					
				}
				
			}
		});
		btnNewButton.setBounds(157, 6, 97, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("租车");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textField.getText().equals("")) {
					Tools.messageWindows("请输入汽车车牌");
					
				}else if(textField_1.getText().equals("")){
					Tools.messageWindows("请输入天数");
					
				}else {
					String data[]= {
							textField.getText()
					};
					ResultSet rs = Mysqld.QueryData("select * from carmes where id=?", data);
					int a=0;
					try {
						while(rs.next()) {
							a++;
						}
						rs.close();
						if(a==0) {
							Tools.messageWindows("请输入正确汽车车牌");
						}else {
							rs = Mysqld.QueryData("select * from carmes where id=?", data);
							while(rs.next()) {
								//判断是否有这个车，有则进行租车
								int a1=Integer.parseInt(rs.getString(5));//获取租车价格
								int a2=Integer.parseInt(textField_1.getText());//获取租车价格
								int a3=a1*a2;
								String a4=String.valueOf(a3);
								String a5=String.valueOf(a3);
								int showConfirmDialog = JOptionPane.showConfirmDialog(null, "您要租"+rs.getString(2)+"汽车,时间"+textField_1.getText()+"天,价格"+a4+"元", "是否租车", JOptionPane.YES_NO_OPTION);
								//System.out.println(showConfirmDialog);
								if(showConfirmDialog==0) {
									System.out.println(a5);
									String s = String.valueOf(a3);
									String data2[]= {
											account,
											textField.getText(),
											s,
											textField_1.getText()
									};
									int a111=Mysqld.upDate("insert into rcode (account,id,price,rdate,ddate) VALUES ((select name from admin where account=?),?,?,now(),DATE_ADD(now(), INTERVAL ? DAY))", data2);
									if(a111==1) {
										Tools.messageWindows("租车成功,祝你旅途愉快");
									}else {
										
										Tools.messageWindows("您已经租了当前车辆，不能重复租借");
									}
									
								}
								
								
							}
							rs.close();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				
			}
		});
		btnNewButton_1.setBounds(475, 6, 97, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("租车时间");
		lblNewLabel_1.setBounds(287, 10, 81, 15);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(378, 7, 66, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		panel.add(JS);
		
		JLabel lblNewLabel_2 = new JLabel("天");
		lblNewLabel_2.setBounds(448, 10, 58, 15);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("还车");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String data[]= {
						account,
						textField_2.getText(),
						"租车中"
						
				};
			//如果状态为租车中如果有则进行还车
				ResultSet rs = Mysqld. QueryData("select * from rcode where account=(select name from admin where account=?) and id1=? and star=?", data);
				try {
					int a=0;
					while(rs.next()) {
						
						a++;
						
					}
					//有数据
					if(a!=0) {
						//可以进行还车
						String data1[]= {
								"已还车",
								account,
								textField_2.getText(),
								
								
						};
						
						
						 a=Mysqld.upDate("UPDATE rcode set gdate=now(), star=? where account=(select name from admin where account=?) and id1=? ", data1);
						if(a==1) {
							Tools.messageWindows("还车成功");
						}else
						Tools.messageWindows("还车失败");
						
					}else {
						Tools.messageWindows("还车失败");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_2.setBounds(723, 6, 97, 23);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("还车编码");
		lblNewLabel_3.setBounds(582, 10, 58, 15);
		panel.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(643, 8, 66, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);
		//添加表格
		Object column[] ={"租车编号","姓名","车牌","价格","租车日期","到期日期","归还日期","状态"};//创建表格
			
		Table t1Tabl=new Table(column);
		 JTable tabl = t1Tabl.getTables();
		JScrollPane J = t1Tabl.getJScrollPane();
		DefaultTableModel mode = t1Tabl.getModel();
		//J.setPreferredSize(new Dimension( 600,280));//设置整个滚动条窗口的大小

		
		
		JButton btnNewButton_3 = new JButton("查看已租车辆");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data[]= {
						account
				};
				//查找租车记录
				ResultSet rs = Mysqld.QueryData("select * from rcode where account=(select name from admin where account=?)", data);
				Tools.addDataTable(rs, mode, 8);
				
			}
		});
		btnNewButton_3.setBounds(78, 279, 140, 23);
		panel.add(btnNewButton_3);
		
		
		J.setBounds(10, 320, 950, 149);
		panel.add(J);
		

		//JScrollPane
	}
}
