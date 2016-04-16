package com.uiComponent.sourceManager;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

public class Model_fileTreePanel extends JPanel{

	private String languageTitle = null;
	private ArrayList<String> fileExtendName = new ArrayList<String>();
	public JButton btn_languageTitle = new JButton("null");
	private JPanel pnl_button = new  JPanel();
	public JScrollPane sclp_tree = new JScrollPane();
	private boolean hideTree = true;
	
	public Model_fileTreePanel(String LanguageName,ArrayList<String> list_extendsName){
		languageTitle = LanguageName;
		fileExtendName = list_extendsName;
		btn_languageTitle.setText(languageTitle);
		initComponent();
	}
	
	private void  initComponent(){
		pnl_button.setLayout(new GridLayout(1,1));
		pnl_button.add(btn_languageTitle);
		
		
		GroupLayout pLayout = new GroupLayout(this);
		this.setLayout(pLayout);
		pLayout.setAutoCreateContainerGaps(false);
		pLayout.setAutoCreateGaps(false);
		
		GroupLayout.SequentialGroup hGroup = pLayout.createSequentialGroup();
		hGroup.addGroup(pLayout.createParallelGroup().addComponent(pnl_button,javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
																											.addComponent(sclp_tree));
		GroupLayout.SequentialGroup vGroup = pLayout.createSequentialGroup();
		vGroup.addGroup(pLayout.createParallelGroup().addComponent(pnl_button,javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE));
		vGroup.addGroup(pLayout.createParallelGroup().addComponent(sclp_tree));
		
		pLayout.setHorizontalGroup(hGroup);
		pLayout.setVerticalGroup(vGroup);
		
		sclp_tree.setVisible(hideTree);
	}
	
	public void addTree(JTree dirTree){
		sclp_tree.setViewportView(dirTree);
		reload();
	}
	
	public void setHideTree(boolean state){
		hideTree = state;
		reload();
	}
	
   private void  reload(){
	   initComponent();
   }
	
}
