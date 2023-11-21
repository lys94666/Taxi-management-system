package com.tools;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table {
	
	JTable tableL=null;//定义一个表格
	JScrollPane jscrollpane;//滚动条
	DefaultTableModel  model = null;//默认模式

	public Table(Object columns[]) {
		Table(columns);
	}
	
	
	void Table(Object columns[]) {
		tableL=getTable(columns);//��ʼ�����
		jscrollpane=new JScrollPane(tableL);//给表格添加滚动条
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);	//设置滚动条放心为竖着的
	}
	 JTable getTable(Object columns[]) {
		if(tableL==null) {
			tableL=new JTable();//
			model=new DefaultTableModel() {
				public boolean isCellEditable(int row, int column)
				{
				return false;
				}
				
			};
		model.setColumnIdentifiers(columns);
		tableL.setModel(model);
		tableL.getTableHeader().setReorderingAllowed(false);
		tableL.getTableHeader().setResizingAllowed(false);
		}
		
		return tableL;
	}
	 //返回表格
	 public JTable getTables() {
		 return tableL;
	 }
	 public  JScrollPane getJScrollPane() {
		 return jscrollpane;
	 }
	 public DefaultTableModel getModel() {
		 return model;
	 }
}
