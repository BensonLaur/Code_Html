package com.uiComponent.filesSelect;

import static com.uiComponent.MainFrame.currentDirectory;
import static com.uiComponent.MainFrame.currentFileFilter;
import static com.uiComponent.MainFrame.currentSourceType;
import static com.uiComponent.MainFrame.lst_typeName;
import static com.uiComponent.MainFrame.outputDirectory;
import static com.uiComponent.MainFrame.filesT0Copied;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.LineBorder;

import com.dataStructure.Attribute;
import com.dataStructure.FileNode;
import com.dataStructure.OrderIntList;
import com.dataStructure.RightClickMenu;
import com.function.ConvertFromCpp;
import com.function.ConvertFromJava;
import com.function.MyFileFilter;
import com.uiComponent.DialogFrame;
import com.uiComponent.MainFrame.SourceType;


public class FileSelectPanel  extends JPanel{
	public static Attribute htmlAttribute = new Attribute();
	private LineBorder border = new LineBorder(Color.GRAY,1);
	private JPanel pnl_ShowFiles = new JPanel();
	private JPanel pnl_SettingOut = new JPanel();
	
	private JCheckBox checkAll = new JCheckBox();
	private JLabel lbl_checkAll = new  JLabel("全选");
	private JLabel lbl_filesName = new JLabel("   文件名");
	private JLabel lbl_date = new JLabel("   最后修改日期");
	private JLabel lbl_size = new JLabel("   大小");
	private JPanel pnl_fistLine = new JPanel();
	
	private ArrayList<File> filesToShow = new ArrayList<File>();	
	private ArrayList<File> filesSelected = new ArrayList<File>();
	private ArrayList<JCheckBox> lst_chkbx = new ArrayList<JCheckBox>();
	private ArrayList<JLabel> lst_label_filesName = new ArrayList<JLabel>();
	private ArrayList<JLabel> lst_label_date = new ArrayList<JLabel>();
	private ArrayList<JLabel> lst_label_size = new ArrayList<JLabel>();
	private ArrayList<JPanel> lst_pnl_files = new ArrayList<JPanel>();
	private JPanel pnl_showList = new JPanel();
	private JScrollPane scrl_ShowFiles =new JScrollPane(pnl_showList);
	private RightClickMenu popMenu = new RightClickMenu(RightClickMenu.TYPE_FILES_PANEL );
	
	private JLabel lbl_fileName = new JLabel("所选文件名");
	private JLabel lbl_outName = new JLabel("目标文件名");
	private JTextField tfd_fileName = new JTextField();
	private JTextField tfd_outName = new JTextField();
	public JButton btn_convert = new JButton("开始转换");
	public JButton btn_setting = new JButton("输出设置");
	
	private OrderIntList lst_NumSelected = new OrderIntList();

	private DialogFrame frame_setAttribute = new DialogFrame(null);
	private AttributeSettingPanel pnl_setAttribute = new AttributeSettingPanel();
	
	public FileSelectPanel(JFrame ownerFrame){
		frame_setAttribute = new DialogFrame(ownerFrame);
		initListPanel();
		initShowPanel();
		initWhileComponent();
	}
	
	public void refresh_sourceFilesList(){
			initListPanel();
			scrl_ShowFiles.validate();
	}
	
	private void initListPanel(){
		////</test>System.out.println("Reload List of Files in: "+currentDirectory);
		
		// <comment> This <code>if </code> is just used for the First invocation of <code>initListPanel </code>
		if(currentFileFilter==null ){
			ArrayList<String> javaExtendsName =new ArrayList<String>();
			javaExtendsName .add(".java");
			currentFileFilter = new MyFileFilter(javaExtendsName);
		}
		// </comment>
		
		 if(currentDirectory.getAbsolutePath().equals("C:\\Documents and Settings")){
			pnl_showList.removeAll();
			pnl_showList.repaint();
			 return;
		 }
		
		File[] tempFiles = currentDirectory.listFiles(currentFileFilter);
		filesToShow.removeAll(filesToShow);
		if(tempFiles ==null) filesToShow.add(currentDirectory);
		else{
			for(int i =0 ;i<tempFiles.length;i++){
				filesToShow.add(tempFiles[i]);
			}
		}
		int  filesCount = filesToShow.size();
		/*// <test>
 		System.out.println("count of List:------->"+filesToShow.size());
		for(File t:filesToShow){System.out.println(t);}
		*/// </test>
		
		lst_chkbx.removeAll(lst_chkbx);
		lst_label_filesName.removeAll(lst_label_filesName);
		lst_label_date.removeAll(lst_label_date);
		lst_label_size.removeAll(lst_label_size);
		lst_pnl_files.removeAll(lst_pnl_files);
		lst_NumSelected.removeAll();
		filesSelected.removeAll(filesSelected);
		checkAll.setSelected(false);
		tfd_fileName.setText("");
		tfd_outName.setText("");
		
		// <comment>  This  <code> for </code>  loop initialize  every sub Panel  in <code>lst_pnl_files</code>,
		// 							  and  add ActionListener to each instance of <class>JcheckBox </class>
		for(int i=0;i<filesCount;i++){
			File tempFile= filesToShow.get(i);
			JCheckBox chbx = new JCheckBox();
			chbx.setName(""+i);  chbx.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					JCheckBox cbx_selected = (JCheckBox)(e.getSource());
					Integer num = Integer.parseInt(cbx_selected.getName());
					if(cbx_selected.isSelected()){
						lst_NumSelected.add(num);
						lst_pnl_files.get(num).setBackground(Color.LIGHT_GRAY );
					}else{
						lst_NumSelected.remove(num);
						lst_pnl_files.get(num).setBackground(Color.decode(Integer.valueOf("E3E3E3",16).toString()));
					}
					resetTfd_filesName();
				}
			});
			lst_chkbx.add( chbx);
			lst_label_filesName.add(new JLabel(tempFile.getName()));
			Date date = new Date(tempFile.lastModified());
			lst_label_date.add(new JLabel(date.toLocaleString()));
			lst_label_size.add(new JLabel(tempFile.length()/1024+" KB"));
			
			JPanel pnl_temp = new JPanel();
			pnl_temp.setBorder(border);
			pnl_temp.setBackground(Color.decode(Integer.valueOf("E3E3E3",16).toString()));
			lst_pnl_files.add(pnl_temp);
		
			// <comment> Set layout of <code> lst_pnl_files </code>
			GroupLayout layout = new GroupLayout(lst_pnl_files.get(i));
			lst_pnl_files.get(i).setLayout(layout);
			layout.setAutoCreateContainerGaps(false);
			layout.setAutoCreateGaps(false);
			GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
			hGroup.addGap(6).addComponent(lst_chkbx.get(i) )
			.addGap(25)
			.addComponent(lst_label_filesName.get(i),GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
			.addComponent(lst_label_date.get(i),GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
			.addComponent(lst_label_size.get(i),GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE);
			GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
			vGroup.addGroup(layout.createParallelGroup().
			addComponent(lst_chkbx.get(i),GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE )
			.addComponent(lst_label_filesName.get(i))
			.addComponent(lst_label_date.get(i)).addComponent(lst_label_size.get(i)));
			layout.setHorizontalGroup(hGroup);
			layout.setVerticalGroup(vGroup);
			// </comment>
		}
		// </comment>
		
		pnl_showList.removeAll();
		pnl_showList.setLayout(null);
		for(int i= 0; i<filesCount; i++){
			lst_pnl_files.get(i).setBounds(0, i*24, 598, 23);
			pnl_showList.add(lst_pnl_files.get(i));
		}
		pnl_showList.repaint();
		pnl_showList.setPreferredSize(new Dimension(500,filesCount*24));
		
		////</comment> add ActionListener for <instance> checkAll <instance>
		checkAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(checkAll.isSelected()){
					for(int i=0;i< lst_chkbx.size();i++){
						lst_chkbx.get(i).setSelected(true);
						lst_pnl_files.get(i).setBackground(Color.LIGHT_GRAY );
					}
					for(int i=0;i< lst_chkbx.size();i++)lst_NumSelected.add(i);
					resetTfd_filesName();
				}else {
					for(int i=0;i< lst_chkbx.size();i++){
						lst_chkbx.get(i).setSelected(false);
						lst_pnl_files.get(i).setBackground(Color.decode(Integer.valueOf("E3E3E3",16).toString()));
					}
					lst_NumSelected.removeAll();
					resetTfd_filesName();
				}
			}
		});
		pnl_showList.validate();
	}
	
	private void initShowPanel(){
		initPopMenuListener();
		pnl_showList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
            		if(e.getButton()==MouseEvent.BUTTON3){
            			popMenu.show(pnl_showList, e.getX(), e.getY());
            		}
            		else return;
			}
		});
		
		GroupLayout layout = new GroupLayout(pnl_fistLine);
		pnl_fistLine.setLayout(layout);
		layout.setAutoCreateGaps(false);
		layout.setAutoCreateContainerGaps(false);
		lbl_filesName.setBorder(border);
		lbl_filesName.setBorder(border);
		lbl_date.setBorder(border);
		lbl_size.setBorder(border);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGap(2).addComponent(checkAll )
		.addGap(2).addComponent( lbl_checkAll)
		.addGap(5).addComponent(lbl_filesName,GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
		.addComponent(lbl_date,GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
		.addComponent(lbl_size,GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE);
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().
		addComponent(checkAll ).addComponent(lbl_checkAll).addComponent(lbl_filesName)
		.addComponent(lbl_date).addComponent(lbl_size));

		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);
		
		BorderLayout fileLayout = new BorderLayout();
		pnl_ShowFiles.setLayout(fileLayout);
		
		pnl_ShowFiles.add(pnl_fistLine,BorderLayout.NORTH);
		pnl_ShowFiles.add(scrl_ShowFiles);
		
	}
	
	private void initPopMenuListener(){
		popMenu.menuItem3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("JMenuItem(\"复制\")");
				filesT0Copied.clear();
				for(int i=0;i<filesSelected.size();i++){
					filesT0Copied.add(filesSelected.get(i));
				}
				System.out.println("copied "+filesSelected.size()+" files.");
			}
		});
		
		popMenu.menuItem4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("JMenuItem(\"粘贴\")");
				//System.out.println(" ----当前目录: "+currentDirectory);
				if(filesT0Copied.size()==0)return ;
				
				for(int i=0;i<filesT0Copied.size();i++){				// 循环实现文件的复制
					String newFileName = filesT0Copied.get(i).getName();
					int[] count=new int [1];//使用数字以便在方法调用中可以改变count的值
					while(exitName(newFileName,currentDirectory)){
						count[0]++;
						newFileName= reName(newFileName,currentDirectory,count);
					}
					try {
						buildNewFile(newFileName,currentDirectory,filesT0Copied.get(i));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				com.uiComponent.MainFrame.refleshCurrentDir();
				System.out.print(" ----------- 完成粘贴-----------\n");
				refresh_sourceFilesList();
			}
		});
		
		popMenu.menuItem5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("JMenuItem(\"删除\")");
				int result = JOptionPane.showConfirmDialog(null, "是否删除选中项");
				if(result!=0)return;
				else {//删除
					for(int i=0;i<filesSelected.size();i++){
						filesSelected.get(i).delete();
					}
				}
				com.uiComponent.MainFrame.refleshCurrentDir();
				System.out.print(" ----------- 完成删除-----------");
				refresh_sourceFilesList();
			}
		});
	}
	//这三个函数为上面一个所用
	boolean exitName(String newFileName,File dirToCheck){
		String newFileNameString = newFileName;
		File files[] = dirToCheck.listFiles();
		for(int i=0;i<files.length;i++){
			if(newFileNameString.equals(files[i].getName()))return true;
		}
		return false;
	}
	String reName(String newFileName,File currentDirectory,int[] integerCount){
		int count =integerCount[0];
		int dotIndex = newFileName.indexOf(".");
		StringBuilder tailString = new StringBuilder(newFileName.substring(dotIndex, newFileName.length()));
		StringBuilder headString = new StringBuilder(newFileName.substring(0,dotIndex));
		//System.out.println(headString+"+"+tailString);
		if(count==1){
			int index_leftBracket = headString.lastIndexOf("(");
			int index_rightBracket = headString.lastIndexOf(")");
			if(index_leftBracket==-1)
				headString.append("("+1+")");
			else{
				int num=0;
				try{
					num = Integer.parseInt(headString.substring(index_leftBracket+1, index_rightBracket));
					integerCount[0]=num;
				}catch(NumberFormatException e){
					headString.append("("+1+")");
				}
			}
		}
		else{
			headString=new StringBuilder(headString.substring(0, headString.lastIndexOf("(")));
			headString.append("("+count+")");
		}
		return (headString.append( tailString) ).toString();
	}
	void buildNewFile(String newFileName,File TargetDir,File SourceDir) throws IOException{
		String addedString =TargetDir.getAbsolutePath() ;
		int LastIndex = addedString.lastIndexOf('\\');
		if(LastIndex+1!=addedString.length()){
			addedString+="\\";
		};
			File newFile = new File(addedString+newFileName);
		
		// 新建文件输入流并对它进行缓冲   
        FileInputStream input = new FileInputStream(SourceDir);  
        BufferedInputStream inBuff=new BufferedInputStream(input);  
  
        // 新建文件输出流并对它进行缓冲   
        FileOutputStream output = new FileOutputStream(newFile);  
        BufferedOutputStream outBuff=new BufferedOutputStream(output);  
          
        // 缓冲数组   
        byte[] b = new byte[1024 * 5];  
        int len;  
        while ((len =inBuff.read(b)) != -1) {  
            outBuff.write(b, 0, len);  
        }  
        // 刷新此缓冲的输出流   
        outBuff.flush();  
          
        //关闭流   
        inBuff.close();  
        outBuff.close();  
        output.close();  
        input.close(); 
        
        System.out.println("copied:"+newFile.getAbsolutePath());
	}
	
	private void initWhileComponent(){
		frame_setAttribute.setSize(480, 485);
		frame_setAttribute.setTitle("转换属性设置");
		frame_setAttribute.add(pnl_setAttribute);
		//frame_setAttribute.pack();
		//frame_setAttribute.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_setAttribute.setLocationRelativeTo(null);
		frame_setAttribute.setVisible(false);
		
		BorderLayout layout = new BorderLayout(3,3);
		setLayout(layout);
		this.removeAll();
		add(pnl_ShowFiles,BorderLayout.CENTER);
		add(pnl_SettingOut,BorderLayout.SOUTH);
		addButtonListenner();
		
		GroupLayout layout2 = new GroupLayout(pnl_SettingOut);
		pnl_SettingOut.setLayout(layout2);
		layout2.setAutoCreateGaps(true);
		layout2.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout2.createSequentialGroup();
		
		hGroup.addGroup(layout2.createParallelGroup()
				.addComponent(lbl_fileName ,GroupLayout.PREFERRED_SIZE,66,GroupLayout.PREFERRED_SIZE)
				.addComponent(lbl_outName ,GroupLayout.PREFERRED_SIZE,66,GroupLayout.PREFERRED_SIZE));
		hGroup.addGroup(layout2.createParallelGroup()
		.addComponent(tfd_fileName ,GroupLayout.PREFERRED_SIZE,410,GroupLayout.PREFERRED_SIZE)
		.addComponent(tfd_outName ,GroupLayout.PREFERRED_SIZE,410,GroupLayout.PREFERRED_SIZE));
		hGroup.addGroup(layout2.createParallelGroup()
		.addComponent(btn_convert ,GroupLayout.PREFERRED_SIZE,100,GroupLayout.PREFERRED_SIZE )
		.addComponent(btn_setting ,GroupLayout.PREFERRED_SIZE,100,GroupLayout.PREFERRED_SIZE));
		
		layout2.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout2.createSequentialGroup();
		
		vGroup.addGroup(layout2.createParallelGroup().
		addComponent(lbl_fileName ).addComponent(tfd_fileName).addComponent(btn_convert));
		vGroup.addGroup(layout2.createParallelGroup().
		addComponent(lbl_outName).addComponent(tfd_outName).addComponent(btn_setting));
		
		layout2.setVerticalGroup(vGroup);
		
		tfd_fileName.setEditable(false);
		tfd_outName.setEditable(false);
	}
	
	private void resetTfd_filesName() {
		filesSelected.removeAll(filesSelected);
		for(int i = 0;i<lst_NumSelected.size();i++){
			filesSelected.add(filesToShow.get(lst_NumSelected.get(i)));
		}
		String s = new String("");
		for(int i = 0;i<lst_NumSelected.size();i++){
			if(i != 0 ) s += " , ";
			s += lst_label_filesName.get(lst_NumSelected.get(i)).getText();
		}
		tfd_fileName.setText(s);
		resetTfd_outName();
	}

	private void resetTfd_outName() {
		String s = new String("");
		for(int i = 0;i<lst_NumSelected.size();i++){
			if(i != 0 ) s += " , ";
			StringBuilder sb = new StringBuilder( lst_label_filesName.get(lst_NumSelected.get(i)).getText()); 
			int startIndex = sb.indexOf(".", 0);
			int endIndex = sb.length();
			sb.replace(startIndex, endIndex, ".html");
			s +=sb.toString();
		}
		tfd_outName.setText(s);
	}
	
	private void addButtonListenner(){
		btn_convert.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(filesSelected.isEmpty()){
					JOptionPane.showConfirmDialog(null,"请选择后再转换", "未选中文件", JOptionPane.CLOSED_OPTION,JOptionPane.WARNING_MESSAGE);
					System.out.println("No files selected!");
				}
				else{
					String fileType = null;
					if(currentSourceType.equals(lst_typeName.get(SourceType.CPP.ordinal())))
						fileType = new String("html_output/CppSource");
					else if(currentSourceType.equals(lst_typeName.get(SourceType.JAVA.ordinal())))
						fileType = new String("html_output/JavaSource");
						
					File outputDir = new File(outputDirectory.getAbsolutePath()+"/"+fileType);
					for(int i=0;i<filesSelected.size();i++){
						if(currentSourceType.equals(lst_typeName.get(SourceType.CPP.ordinal())) ){
							    //转换 cpp代码
								ConvertFromCpp.convertFromCpp(filesSelected.get(i).getAbsolutePath(), outputDir, htmlAttribute);
						}
						else if(currentSourceType.equals(lst_typeName.get(SourceType.JAVA.ordinal()))){
							//转换 java代码
							ConvertFromJava.convertFromJava(filesSelected.get(i), outputDir, htmlAttribute);
						}
					}
					
					int result = JOptionPane.showConfirmDialog(null,"转换成功！  \n\n是否打开输出文件夹", "成功转换", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
					if(result == 0){
						String command = new String("cmd /c start explorer "+outputDir.getAbsolutePath());
						try {
							Runtime.getRuntime().exec(command);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
			
		});
		btn_setting.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				frame_setAttribute.setVisible(true);
			}
			
		});
	}
	
}
