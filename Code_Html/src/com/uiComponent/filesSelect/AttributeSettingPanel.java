package com.uiComponent.filesSelect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import static com.uiComponent.filesSelect.FileSelectPanel.htmlAttribute;
import static com.uiComponent.filesSelect.FontDemoPanel.panelSize_X;
import static com.uiComponent.filesSelect.FontDemoPanel.panelSize_Y;

public class AttributeSettingPanel extends JPanel {
	private JPanel pnl_settingWhole = new JPanel();
	private JPanel  pnl_colorSetting = new JPanel();
	private JPanel pnl_fontStyleSetting = new JPanel();
	private JPanel pnl_fontSizeSetting = new JPanel();
	public static FontDemoPanel pnl_DemoShow = new FontDemoPanel();
	public static JScrollPane scrl_DemoShow =new JScrollPane(pnl_DemoShow);
	
	private JLabel lbl_baseColor = new JLabel("基本色");//
	private JComboBox<String> cbb_baseColor = new JComboBox<String>(new String[]{
			"red","black","purple","green","yellow","blue","cyan","gray"});
	private JLabel lbl_keyWordColor = new JLabel("关键字");
	private JComboBox<String> cbb_keyWordColor = new JComboBox<String>(new String[]{
			"red","black","purple","green","yellow","blue","cyan","gray"});
	private JLabel lbl_stringColor = new JLabel("字符串");
	private JComboBox<String> cbb_stringColor = new JComboBox<String>(new String[]{
			"red","black","purple","green","yellow","blue","cyan","gray"});
	private JLabel lbl_commentColor = new JLabel("注释");
	private JComboBox<String> cbb_commentColor = new JComboBox<String>(new String[]{
			"red","black","purple","green","yellow","blue","cyan","gray"});
	private JComboBox<String> cbb_fontStyle = new JComboBox<String>(new String[]{
			"Arial","Comic Sans MS","Courier New" ,"Fixedsys", "Georgia","Impact",
			"Lucia Console","Microsoft Sans Serif","Tahoma", "Times New Roman",
			"Verdana", "楷体_GB3212","宋体" ,"黑体", "隶书"," 幼圆"});
	private JComboBox<Integer> cbb_fontSize = new JComboBox<Integer>(new Integer[]{1,2,3,4,5,6,7});
	
	public AttributeSettingPanel (){
		initSelectedAttribute();
		initListener();
		initLayout();
	}
	
	private void initSelectedAttribute(){
		cbb_baseColor.setSelectedItem(htmlAttribute.getBaseColor());
		cbb_keyWordColor.setSelectedItem(htmlAttribute.getKeywordColor());
		cbb_stringColor.setSelectedItem(htmlAttribute.getStringColor());
		cbb_commentColor.setSelectedItem(htmlAttribute.getCommentColor());	
		cbb_fontStyle.setSelectedItem(htmlAttribute.getFontStyle());
		cbb_fontSize.setSelectedItem(htmlAttribute.getFontSize());
	}
	
	private void initListener(){
		cbb_baseColor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				htmlAttribute.setBaseColor(cbb_baseColor.getSelectedItem().toString());
				pnl_DemoShow.update();
			}
		});
		
		cbb_keyWordColor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				htmlAttribute.setKeywordColor(cbb_keyWordColor.getSelectedItem().toString());
				pnl_DemoShow.update();
			}
		});
		
		cbb_stringColor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				htmlAttribute.setStringColor(cbb_stringColor.getSelectedItem().toString());
				pnl_DemoShow.update();
			}
		});
		
		cbb_commentColor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				htmlAttribute.setCommentColor(cbb_commentColor.getSelectedItem().toString());
				pnl_DemoShow.update();
			}
		});
		
		cbb_fontStyle.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				htmlAttribute.setFontStyle(cbb_fontStyle.getSelectedItem().toString());
				pnl_DemoShow.update();
			}
		});
		
		cbb_fontSize.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				htmlAttribute.setFontSize((int)cbb_fontSize.getSelectedItem());
				
				updateThread();
				
				scrl_DemoShow.revalidate();
			}
		});
	}
	
	private void initLayout(){
		
		pnl_colorSetting.setBorder(new TitledBorder("颜色"));
		 pnl_fontStyleSetting.setBorder(new TitledBorder("字体类型"));
		 pnl_fontSizeSetting.setBorder(new TitledBorder("字体大小"));
		scrl_DemoShow.setBorder(new LineBorder(Color.black,2));
		
		pnl_fontStyleSetting.setLayout(new FlowLayout(FlowLayout.LEFT));
		 pnl_fontSizeSetting.setLayout(new FlowLayout(FlowLayout.LEFT));
		 
		 cbb_fontSize.setPreferredSize(new Dimension(146,27));
		 pnl_fontStyleSetting.add(cbb_fontStyle);
		 pnl_fontSizeSetting.add(cbb_fontSize);
		 /*
		 pnl_settingWhole.setLayout(new GridLayout(4,2,8,8));
		 pnl_settingWhole.add(lbl_baseColor);
		 pnl_settingWhole.add(cbb_baseColor);
		 pnl_settingWhole.add(lbl_keyWordColor);
		 pnl_settingWhole.add(cbb_keyWordColor);
		 pnl_settingWhole.add(lbl_stringColor);
		 pnl_settingWhole.add(cbb_stringColor);
		 pnl_settingWhole.add(lbl_commentColor);
		 pnl_settingWhole.add(cbb_commentColor);
		 */
		 GroupLayout cpLayout = new GroupLayout( pnl_colorSetting);
		 pnl_colorSetting.setLayout(cpLayout);
		 cpLayout.setAutoCreateContainerGaps(true);
		 cpLayout.setAutoCreateGaps(true);
		 cpLayout.setHorizontalGroup(cpLayout.createSequentialGroup()
				 .addGroup(cpLayout.createParallelGroup()
						 .addComponent(lbl_baseColor,GroupLayout.DEFAULT_SIZE,100,GroupLayout.DEFAULT_SIZE)
						 .addComponent(lbl_keyWordColor,GroupLayout.DEFAULT_SIZE,100,GroupLayout.DEFAULT_SIZE)
						 .addComponent(lbl_stringColor,GroupLayout.DEFAULT_SIZE,100,GroupLayout.DEFAULT_SIZE)
						 .addComponent(lbl_commentColor,GroupLayout.DEFAULT_SIZE,100,GroupLayout.DEFAULT_SIZE)
						 )
				.addGroup(cpLayout.createParallelGroup()
						.addComponent(cbb_baseColor,GroupLayout.DEFAULT_SIZE,100,GroupLayout.DEFAULT_SIZE)
						.addComponent(cbb_keyWordColor,GroupLayout.DEFAULT_SIZE,100,GroupLayout.DEFAULT_SIZE)
						.addComponent(cbb_stringColor,GroupLayout.DEFAULT_SIZE,100,GroupLayout.DEFAULT_SIZE)
						.addComponent(cbb_commentColor,GroupLayout.DEFAULT_SIZE,100,GroupLayout.DEFAULT_SIZE)
						)
						
				 );
		 cpLayout.setVerticalGroup(cpLayout.createSequentialGroup()

				 .addGroup(cpLayout.createParallelGroup()
						 .addComponent(lbl_baseColor,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
						 .addComponent(cbb_baseColor,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
						 )
				.addGroup(cpLayout.createParallelGroup()
						 .addComponent(lbl_keyWordColor,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
						 .addComponent(cbb_keyWordColor,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
						 )
				.addGroup(cpLayout.createParallelGroup()
						.addComponent(lbl_stringColor,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
						.addComponent(cbb_stringColor,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
						)
				.addGroup(cpLayout.createParallelGroup()
						.addComponent(lbl_commentColor,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
						.addComponent(cbb_commentColor,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
						 )
				 );
		 
		 pnl_settingWhole.setLayout(null);
		 pnl_settingWhole.setPreferredSize(new Dimension(400,130));
		 pnl_colorSetting.setBounds(5, 5,200, 170);
		 pnl_fontStyleSetting.setBounds(210, 5, 180, 60);
		 pnl_fontSizeSetting.setBounds(210,70, 180, 60);
		 pnl_settingWhole.add(pnl_colorSetting);
		 pnl_settingWhole.add(pnl_fontStyleSetting);
		 pnl_settingWhole.add(pnl_fontSizeSetting);
		 
		 
		 this.setLayout(null);
		 this.setPreferredSize(new Dimension(495,380));
		 pnl_settingWhole.setBounds(5, 5, 490, 190);
		 scrl_DemoShow.setBounds(5, 210, 450, 230);
		 this.add(pnl_settingWhole);
		 this.add(scrl_DemoShow);

		 updateThread();
	}
	
	private void updateThread(){
		Thread t1 = new Thread(new Runnable(){
			Thread t2 = new Thread (new Runnable(){
				@Override
				public void run() {
					//System.out.println("Enter----------- updata");
					pnl_DemoShow.update();
				//System.out.println("After updata");
				}
			});
			@Override
			public void run() {
				t2.start();
				try {t2.join();} 					catch (InterruptedException e1) {e1.printStackTrace();}
				try {Thread.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
				
				//System.out.println("panelSize_X = " + panelSize_X +"  panelSize_Y = " +panelSize_Y );
				pnl_DemoShow.setPreferredSize(new Dimension(panelSize_X , panelSize_Y));
				scrl_DemoShow.revalidate();
				//System.out.println("preferredDone!");
			}
		});
		t1.start();
	}
	
	/*public static void main(String  []args){
    	JFrame f = new JFrame();
    	f.setSize(480, 485);
    	f.add(new AttributeSettingPanel());
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	f.setVisible(true);
    }*/
}
