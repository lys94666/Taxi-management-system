package com.windows;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysqld.DBUtil;
import com.other.EasyCode;
import com.tools.Table;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManCar {

	public  JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;



	public ManCar() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("汽车出租");
		frame.setBounds(100, 100, 908, 519);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 874, 462);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("汽车车牌");
		lblNewLabel.setBounds(10, 10, 58, 15);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(78, 7, 66, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("汽车车型");
		lblNewLabel_1.setBounds(154, 10, 58, 15);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(222, 7, 66, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("汽车款式");
		lblNewLabel_2.setBounds(300, 10, 58, 15);
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(367, 7, 66, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("租车价格/天");
		lblNewLabel_3.setBounds(618, 10, 91, 15);
		panel.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(719, 7, 66, 21);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("目标汽车编号");
		lblNewLabel_4.setBounds(10, 62, 82, 15);
		panel.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(99, 59, 66, 21);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
	//________________________________________________________
		
		Object columns[] ={"汽车车牌","汽车车型","汽车款式","汽车颜色","租车价格"};//创建表格
			
		Table t1Table=new Table(columns);
		 JTable table = t1Table.getTables();
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		JS.setPreferredSize(new Dimension( 600,280));//设置整个滚动条窗口的大小
		JS.setBounds(10, 113, 736, 339);
		
		
		
		
		JButton btnNewButton = new JButton("增加汽车");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JTextField text[]= {
						textField,
						textField_1,
						textField_2,
						textField_5,
						textField_3
				};
				EasyCode.insertData(text, "insert into carmes VALUES (?,?,?,?,?)", 1 ,"请输入汽车编码");
				
				
			}
		});
		btnNewButton.setBounds(175, 58, 97, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("删除汽车");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JTextField text[]= {
						textField_4
				};

				EasyCode.deleteDate(text, "delete from carmes where id=?", 1, "请输入目标汽车编号");
				
				
			}
		});
		btnNewButton_1.setBounds(282, 58, 97, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("更改汽车");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//修改改
				JTextField text[]= {
						textField,
						textField_1,
						textField_2,
						textField_5,
						textField_3,
						textField_4,
				};
				EasyCode.upData(text, "UPDATE carmes set id=?,name=?,cor=?,carbrand=?,rendpr=? where id=?", 5, "请输入汽车车牌");

			}
		});
		btnNewButton_2.setBounds(389, 58, 97, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("查找汽车");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JTextField text[]= {
						textField_4
				};
				
				//如果为空 查
				if(textField_4.getText().equals("")) {
					
					EasyCode.showAllData("select * from carmes", 5, model);

				}else {
					
					JTextField text1[]= {
							textField,
							textField_1,
							textField_2,
							textField_5,
							textField_3,
					};
					int showdata[]= {1,2,3,4,5};
					EasyCode.showOneData(text, text1, "select * from carmes where id=?", 5, model, showdata);
					
				}
			}
		});
		btnNewButton_3.setBounds(504, 58, 97, 23);
		panel.add(btnNewButton_3);

		panel.add( JS);
		
		JLabel lblNewLabel_5 = new JLabel("颜色");
		lblNewLabel_5.setBounds(455, 10, 58, 15);
		panel.add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setBounds(511, 7, 66, 21);
		panel.add(textField_5);
		textField_5.setColumns(10);
		

	}
}
