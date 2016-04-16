package com.uiComponent.sourceManager;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class ResourceManagmentPanel extends JPanel{
	public  Model_fileTreePanel  pnl_cpp= new Model_fileTreePanel("null",new ArrayList<String>());
	public  Model_fileTreePanel  pnl_java= new Model_fileTreePanel("null",new ArrayList<String>());
	public ArrayList<String> extendsName_java = new ArrayList<String>();
	public ArrayList<String> extendsName_cpp = new ArrayList<String>();
	
	public ResourceManagmentPanel (){
		extendsName_java.add(".java");
		pnl_java = new  Model_fileTreePanel("Java",extendsName_java);
		extendsName_cpp.add(".cpp");
		extendsName_cpp.add(".c");
		extendsName_cpp.add(".h");
		pnl_cpp = new  Model_fileTreePanel("C/C++",extendsName_cpp);
		pnl_java.setHideTree(true);
		
		initComponent();
	}
	
	private void initComponent(){
		
		GroupLayout pLayout = new GroupLayout(this);
		this.setLayout(pLayout);
		pLayout.setAutoCreateContainerGaps(false);
		pLayout.setAutoCreateGaps(false);
		
		GroupLayout.SequentialGroup hGroup = pLayout.createSequentialGroup();
		hGroup.addGroup(pLayout.createParallelGroup().addComponent(pnl_java).addComponent(pnl_cpp));
		GroupLayout.SequentialGroup vGroup = pLayout.createSequentialGroup();
		vGroup.addGroup(pLayout.createParallelGroup()
				.addComponent(pnl_java));
		vGroup.addGroup(pLayout.createParallelGroup()
				.addComponent(pnl_cpp));
		
		pLayout.setHorizontalGroup(hGroup);
		pLayout.setVerticalGroup(vGroup);
	}
}
