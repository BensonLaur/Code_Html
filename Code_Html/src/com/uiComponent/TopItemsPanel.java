package com.uiComponent;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class TopItemsPanel extends JPanel{
	public JButton btn_outputFolder = new JButton("打开输出文件夹");
	//public JButton btn_setOutput = new JButton("修改");
	public JLabel lbl_setOutput = new JLabel();//getClass().getResource("/com/material/settingPressed.png")
	private Icon settingIcon = new ImageIcon(getClass().getResource("/com/material/setting.png"));
	private Icon settingEnteredIcon = new ImageIcon(getClass().getResource("/com/material/settingEntered.png"));
	private Icon settingPressedIcon = new ImageIcon(getClass().getResource("/com/material/settingPressed.png"));
	private Frame ownerFrame;
	public outputDirectoryFrame outFrame = new outputDirectoryFrame(null);
	
	public TopItemsPanel(Frame owner){
		ownerFrame = owner;
		outFrame = new outputDirectoryFrame(owner);
		outFrame.setTitle("设置输出目录");
		outFrame.setSize(500, 500);
		outFrame.setLocationRelativeTo(null);
		outFrame.setVisible(false);
		
		initLayout();
		initListener();
	}
	
	public void initLayout(){

		//lbl_setOutput.setText("修改");
		lbl_setOutput.setVerticalAlignment(SwingConstants.CENTER);
		lbl_setOutput.setIcon(settingIcon);
		lbl_setOutput.setToolTipText("修改输出目录");
		
		GroupLayout topLayout = new GroupLayout(this);
		this.setLayout(topLayout);
		topLayout.setAutoCreateContainerGaps(true);
		topLayout.setAutoCreateGaps(true);
		
		GroupLayout.SequentialGroup hGroup = topLayout.createSequentialGroup();
		hGroup.addGroup(topLayout.createParallelGroup().addComponent(btn_outputFolder));
		//hGroup.addGroup(topLayout.createParallelGroup().addComponent(btn_setOutput));
		hGroup.addGroup(topLayout.createParallelGroup().addComponent(lbl_setOutput));
		GroupLayout.SequentialGroup vGroup = topLayout.createSequentialGroup();
		vGroup.addGroup(topLayout.createParallelGroup()
				.addComponent(btn_outputFolder)
				//.addComponent(btn_setOutput)
				.addComponent(lbl_setOutput));
		
		topLayout.setHorizontalGroup(hGroup);
		topLayout.setVerticalGroup(vGroup);
		
	}

	public void initListener(){
		/*
		btn_setOutput.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				uiComponent.outputDirectoryFrame.
				directoryTree.setCellRenderer(new function.MyDefaultTreeCellRenderer());
				uiComponent.outputDirectoryFrame.tfd_currentOutDir.setText(uiComponent.MainFrame.outputDirectory.getAbsolutePath());
				outFrame.setVisible(true);
			}
		});*/
		
		lbl_setOutput.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				com.uiComponent.outputDirectoryFrame.
				directoryTree.setCellRenderer(new com.function.MyDefaultTreeCellRenderer());
				com.uiComponent.outputDirectoryFrame.tfd_currentOutDir.setText(com.uiComponent.MainFrame.outputDirectory.getAbsolutePath());
				outFrame.setVisible(true);
			}
			 public void  mouseEntered(MouseEvent e) { 
				 lbl_setOutput.setIcon(settingEnteredIcon);
		        }
			 public void  mouseExited(MouseEvent e) {
				 lbl_setOutput.setIcon(settingIcon);
			 }
			 public void  mouseReleased(MouseEvent e) {
				 lbl_setOutput.setIcon(settingEnteredIcon);
	            	
	        }
			 public void  mousePressed(MouseEvent e) { 
				 lbl_setOutput.setIcon(settingPressedIcon);
	            } 
		});
	}
}
