package com.uiComponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import com.dataStructure.FileNode;
import com.dataStructure.RightClickMenu;
import com.function.MyDefaultTreeCellRenderer;
import com.function.MyDirFilter;
import com.function.MyFileFilter;
import com.uiComponent.filesSelect.FileSelectPanel;
import com.uiComponent.sourceManager.ResourceManagmentPanel;

public class MainFrame extends JFrame{
	public static enum SourceType{CPP,JAVA}
	public static ArrayList<String> lst_typeName = new ArrayList<String>();

	public static File currentDirectory = javax.swing.filechooser.FileSystemView  
            .getFileSystemView().getHomeDirectory();//获得桌面路径
	public static MyFileFilter currentFileFilter = null;
	public static File outputDirectory = null;
	public static String currentSourceType = "";
	private  Icon collapsedIcon = new ImageIcon(getClass().getResource("/com/material/collapsedIcon.png"));
	private  Icon expandedIcon = new ImageIcon(getClass().getResource("/com/material/expandedIcon.png"));

	private TopItemsPanel pnl_top= new TopItemsPanel(this);
	private ResourceManagmentPanel pnl_management = new ResourceManagmentPanel();
	private FileSelectPanel pnl_fileSelection = new FileSelectPanel(this);
	private JPanel pnl_body = new JPanel();
	private static JTree directoryTree = new JTree();
	private static JTree currentShowedTree = null;
	private static TreePath currentShowTreePath=null;
	private MyDefaultTreeCellRenderer  treeRender = new MyDefaultTreeCellRenderer();

	private File selectedDirectory = null;
	private RightClickMenu popMenu = new RightClickMenu(RightClickMenu.TYPE_RESOURCE );
	private static TreePath TreeSelectedPath;
	private static int TreeSelectedRow=-1;
	
	public static ArrayList<File> filesT0Copied = new ArrayList<File>();
	
	public  MainFrame(){
		initOutputSetting();
		initComponents();
		this.setLocationRelativeTo(null);
		// Set the location of the MainFrame to the middle of the screen 
	}

	private void initOutputSetting() {
		File outFile = new File("outputDirectory.dat");
		if(!outFile.exists()){
			try {
				try {
					outFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				PrintWriter writer = new PrintWriter("outputDirectory.dat");
				writer.print("C:\\");
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		Scanner readFile;
		String outFileString="";
		try {
			readFile = new Scanner(outFile);
			outFileString = readFile.nextLine();
			readFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		outputDirectory = new File(outFileString);
		System.out.println("输出目录为"+"："+outputDirectory.getAbsolutePath());
		if(!outputDirectory.exists()) {
		//	System.out.println(outputDirectory.getAbsolutePath());
			File SourceOutputDirectory = new File(outputDirectory.getAbsolutePath()+"/html_output");;
			File cppSource = new File(outputDirectory.getAbsolutePath()+"/html_output/CppSource");
			File javaSource = new File(outputDirectory.getAbsolutePath()+"/html_output/JavaSource");
			SourceOutputDirectory.mkdirs();
			 cppSource.mkdirs();
			 javaSource.mkdirs();
		}
		
	}
	
	public  static void saveOutputSetting(){
		// <comment> 再次检验有没有 outputDirectory.dat 存在 ，如果没有，创建新的文件
		File outFile = new File("outputDirectory.dat");
		if(!outFile.exists()){
			try {
				try {
					outFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				PrintWriter writer = new PrintWriter("outputDirectory.dat");
				writer.print("C:\\");
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		// </comment>
		
		Scanner readFile;
		String outFileString="";
		try {
			readFile = new Scanner(outFile);
			outFileString = readFile.nextLine();
			readFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		if(com.uiComponent.outputDirectoryFrame.directoryToSave.getAbsolutePath().equals(outFileString)){
			JOptionPane.showConfirmDialog(null,outFileString+"\\html_output"+" 已为当前输出目录 .",
					"Conformation", JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
		}else{
			int result = JOptionPane.showConfirmDialog(null,"是否确定将输出目录更改为："
					+com.uiComponent.outputDirectoryFrame.directoryToSave.getAbsolutePath()+"\\html_output",
					"Conformation", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(result == 0){
				if( com.uiComponent.outputDirectoryFrame.directoryToSave  != null){	
						outputDirectory = new File(com.uiComponent.outputDirectoryFrame.directoryToSave.getAbsolutePath());
						System.out.println("输出目录更改为"+"："+outputDirectory.getAbsolutePath()+"html_output");
						File SourceOutputDirectory = new File(outputDirectory.getAbsolutePath()+"/html_output");;
						File cppSource = new File(outputDirectory.getAbsolutePath()+"/html_output/CppSource");
						File javaSource = new File(outputDirectory.getAbsolutePath()+"/html_output/JavaSource");
						SourceOutputDirectory.mkdirs();
						 cppSource.mkdirs();
						 javaSource.mkdirs();
						 com.uiComponent.outputDirectoryFrame.tfd_currentOutDir.setText(outputDirectory.getAbsolutePath());
						 
						PrintWriter writer;
						try {
							writer = new PrintWriter("outputDirectory.dat");
							writer.print(outputDirectory.getAbsolutePath());
							writer.close();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
					   }
						
						JOptionPane.showConfirmDialog(null,"修改成功 !\n\n当前输出目录为："+outputDirectory.getAbsolutePath()+"html_output",
								"Conformation", JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
						
				}
			}
		}
	}
	
	private	void initComponents(){
		this.setSize(875, 610);
		this.setTitle("Code_Html");
		this.setBackground(new java.awt.Color(45, 223, 238));
		this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		this.setFont(new java.awt.Font("宋体",1,14));
		this.setName("MainFrame");
		
		lst_typeName.add("cpp");
		lst_typeName.add("java");
		
		JPanel panel = new JPanel();
		BorderLayout layout = new BorderLayout(4,4);
		panel.setLayout(layout);
		LineBorder border1 = new LineBorder(Color.BLACK,1);
		LineBorder border2 = new LineBorder(Color.BLACK,6);

		panel.setBorder(border2);
		pnl_top.setBorder(border1);
		pnl_management.setBorder(border1);
		pnl_fileSelection.setBorder(border1);
		
		addListennerToTopPanel();																						//为 pnl_top注册各种监听器
		panel.add(pnl_top , BorderLayout.NORTH);
		
		
		initPopMenuListener();								//为右键菜单添加监听器
		
		//<comment>  
		//初始化 <instance>MainFrame </instance>时,对  <instance>pnl_management </instance> 进行初始化
		this.currentFileFilter = new MyFileFilter(pnl_management.extendsName_java);
		currentSourceType = lst_typeName.get((SourceType.JAVA.ordinal()));
		
		FileNode rootNode = createRootNode(javax.swing.filechooser.FileSystemView  
                .getFileSystemView().getHomeDirectory().getAbsolutePath());									//创建根节点(桌面)
		rootNode.isInit = true;
		DefaultTreeModel tModel = new DefaultTreeModel(rootNode);
		directoryTree = new JTree(tModel);																								//创建树
		directoryTree.setCellRenderer(treeRender);
		
		
		directoryTree.putClientProperty("JTree.lineStyle","None");
        UIManager.put("Tree.collapsedIcon", collapsedIcon);  
        UIManager.put("Tree.expandedIcon",expandedIcon);  	
        
		directoryTree.setShowsRootHandles(false);
		directoryTree.setRootVisible(true);
		pnl_management.pnl_java.addTree(directoryTree);															//首次添加树
		addTreeListener(directoryTree);																					    			//为树注册各种监听器
		
		currentShowedTree=directoryTree;					//为粘贴功能初始化 currentShowedTree
		
		pnl_management.pnl_cpp.sclp_tree.setVisible(false);
		RefleshFrame();	
		//</comment>
		
		addListenerToManagementPanel();																					  	//为ManagementPanel注册各种监听器
		
		GroupLayout mLayout = new GroupLayout(pnl_body);
		pnl_body.setLayout(mLayout);
		mLayout.setAutoCreateContainerGaps(false);
		mLayout.setAutoCreateGaps(true);
		GroupLayout.SequentialGroup hGroup = mLayout.createSequentialGroup();
		hGroup.addGroup(mLayout.createParallelGroup().addComponent(pnl_management, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE));
		hGroup.addGroup(mLayout.createParallelGroup().addComponent(pnl_fileSelection,GroupLayout.PREFERRED_SIZE, 620, GroupLayout.PREFERRED_SIZE));
		GroupLayout.SequentialGroup vGroup = mLayout.createSequentialGroup();
		vGroup.addGroup(mLayout.createParallelGroup().addComponent(pnl_management, GroupLayout.PREFERRED_SIZE, 498, GroupLayout.PREFERRED_SIZE)
				.addComponent(pnl_fileSelection));
		mLayout.setHorizontalGroup(hGroup);
		mLayout.setVerticalGroup(vGroup);
		/*
		mLayout.setHorizontalGroup(mLayout.createParallelGroup()
					.addComponent(jSeparator)
					.addGap(30)
					.addGroup(mLayout.createSequentialGroup()
							.addComponent(pnl_management, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
							.addComponent(pnl_fileSelection,GroupLayout.PREFERRED_SIZE, 620, GroupLayout.PREFERRED_SIZE)
							)	
				);
		mLayout.setVerticalGroup(mLayout.createParallelGroup()
				.addGroup(mLayout.createParallelGroup()
						.addComponent(jSeparator)
						)
				.addGroup(mLayout.createParallelGroup()
						.addComponent(pnl_management)
						.addComponent(pnl_fileSelection)
						)
				);*/
		
		panel.add(pnl_body,BorderLayout.CENTER);
		
		panel.setBorder(border1);
		this.add(panel);
		//this.pack();
		
		this.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		        exit();
		      }
		    });
	}
	
	private void addListennerToTopPanel(){
		pnl_top.btn_outputFolder.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(outputDirectory.getAbsolutePath().equals("C:\\")||outputDirectory.getAbsolutePath().equals("D:\\")
							||outputDirectory.getAbsolutePath().equals("E:\\")||outputDirectory.getAbsolutePath().equals("D:\\")
							||outputDirectory.getAbsolutePath().equals("G:\\"))
						Runtime.getRuntime().exec("cmd /c start explorer "+outputDirectory.getAbsolutePath()+"html_output");
					else
					Runtime.getRuntime().exec("cmd /c start explorer "+outputDirectory.getAbsolutePath()+"\\html_output");
					//System.out.println("execute... ");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
	}
	
	private void initPopMenuListener(){
		popMenu.menuItem1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("JMenuItem(\"展开\")");
				//System.out.println("pop Location:"+((JMenuItem)e.getSource()).getPopupLocation());
				directoryTree.expandRow(TreeSelectedRow);
				//directoryTree.collapseRow(TreeSelectedRow);
			}
		});
		
		popMenu.menuItem2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("JMenuItem(\"创建子文件夹\")");
				//TreeSelectedRow
				System.out.println("current:"+((FileNode)(TreeSelectedPath.getLastPathComponent())));
				//(TreeSelectedPath.getParentPath().getLastPathComponent());
				FileNode currentFileNode= ((FileNode)(TreeSelectedPath.getLastPathComponent()));

				directoryTree.expandRow(TreeSelectedRow);
				String inString = JOptionPane.showInputDialog(null, "输入新文件夹名称：", "新建文件夹");
				if(inString==null)return ;
				
				File files[] = currentFileNode.filePath.listFiles();
				boolean hasExit = false;
				for(int i=0;i<files.length;i++){
					if(files[i].isDirectory()){
						if(files[i].getName().equals(inString)){
							JOptionPane.showMessageDialog(null, "文件夹名称已存在,无法创建子文件夹", "重命名",JOptionPane.INFORMATION_MESSAGE);
							hasExit = true ;   break;
						}
					}
				}
				String addedString =currentFileNode.filePath.getAbsolutePath() ;
				int LastIndex = addedString.lastIndexOf('\\');
				if(LastIndex+1!=addedString.length()){
					addedString+="\\";
				};
				if(!hasExit){
					File newDir = new File(addedString+inString);
					newDir.mkdirs();
					currentFileNode.isInit=false;
					directoryTree.collapseRow(TreeSelectedRow);
					directoryTree.expandRow(TreeSelectedRow);
				}
				
			}
		});
	}
	 
	private void addTreeListener(JTree directoryTree){
		directoryTree.addTreeExpansionListener(new TreeExpansionListener() {
            //展开
            public void treeExpanded(TreeExpansionEvent event) {
            	FileNode selectedTreeNode = (FileNode) event.getPath().getLastPathComponent();
                String path = event.getPath().toString();
                System.out.println("Expended Component:  "+path);
            }
            //折叠

			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				// TODO Auto-generated method stub
				
			}
        });
		
		directoryTree.addTreeWillExpandListener(new TreeWillExpandListener() {
			@Override
			 public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException{
				//System.out.println("Tree will expend!");
				FileNode lastFileNode = (FileNode)(event.getPath().getLastPathComponent());
				addTreeNode(lastFileNode,lastFileNode.filePath);
			}
			@Override
			public void treeWillCollapse(TreeExpansionEvent event)
					throws ExpandVetoException {
				// TODO Auto-generated method stub
			}
			
		});
		
		directoryTree.addMouseListener(new MouseAdapter() {
            @Override
			public void mouseClicked(MouseEvent e) {
				JTree tree = (JTree)(e.getSource());
				TreeSelectedPath = tree.getPathForLocation(e.getX(), e.getY());
				if(TreeSelectedPath==null)return;
				tree.setSelectionPath(TreeSelectedPath);//得到当前选择路径

				TreeSelectedRow = tree.getRowForLocation(e.getX(), e.getY());//得到树当前选择的行
				
            	FileNode OpenedNode = (FileNode)tree.getLastSelectedPathComponent();
            	if(OpenedNode != null){
            		selectedDirectory = OpenedNode.filePath;
            		System.out.println("clicked on:  "+selectedDirectory);
            		
            		if(e.getButton()==MouseEvent.BUTTON1){
            			currentShowedTree = tree;				//获得当前显示的树
            			currentShowTreePath = TreeSelectedPath;//获得当前显示的树的展开路径

            			System.out.println("current treePath:"+currentShowTreePath.getLastPathComponent());
            			
            			FileNode clickedNode = (FileNode)tree.getLastSelectedPathComponent();//获得当前展开显示的节点
                    	if(clickedNode != null){
                    		if(clickedNode.filePath.toString().equals("null"))return;
                    		currentDirectory = clickedNode.filePath;
                    		System.out.println("single click on:  "+currentDirectory);
                    		pnl_fileSelection.refresh_sourceFilesList();
                    	}
            		}
            		
            		if(e.getButton()==MouseEvent.BUTTON3){
            			if(!selectedDirectory.isFile())
            			popMenu.show(tree, e.getX(), e.getY());
            		}
            	}
            	else return;
            	
			}
        });
	}
	public static void refleshCurrentDir(){
		if(TreeSelectedRow==-1){
			directoryTree.setSelectionRow(0);
			currentShowTreePath=directoryTree.getPathForRow(0);
			TreeSelectedRow=0;
			directoryTree.collapseRow(0);
			directoryTree.expandRow(0);
			System.out.println("reflesh -1 (第一次主界面的粘贴)");
		}
		if(currentShowTreePath==null)return;
		System.out.println("current treePath:"+currentShowTreePath.getLastPathComponent());
		
		currentShowedTree.setSelectionPath(currentShowTreePath);
		TreeSelectedPath=currentShowTreePath;
		
		FileNode currentFileNode= ((FileNode)(TreeSelectedPath.getLastPathComponent()));
		currentFileNode.isInit=false;
		//System.out.println("set init false!");
		directoryTree.collapseRow(TreeSelectedRow);
		directoryTree.expandRow(TreeSelectedRow);

		
	}
	
	private void addListenerToManagementPanel(){

		//<comment>    Add ActionListener for two <instance> btn_languageTitle </instance > 
		//								of <instance> pnl_management </instance>
		pnl_management.pnl_cpp.btn_languageTitle.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(pnl_management.pnl_cpp.sclp_tree.isVisible())
					return;
				else{
					TreeSelectedRow=-1;
					pnl_management.pnl_cpp.sclp_tree.setVisible(true);
					pnl_management.pnl_java.sclp_tree.setVisible(false);
				}
				currentFileFilter = new MyFileFilter(pnl_management.extendsName_cpp);
				
				FileNode rootNode = createRootNode(javax.swing.filechooser.FileSystemView  
		                .getFileSystemView().getHomeDirectory().getAbsolutePath());									//创建根节点
				rootNode.isInit = true;
				DefaultTreeModel tModel = new DefaultTreeModel(rootNode);
				directoryTree = new JTree(tModel);
				directoryTree.setCellRenderer(treeRender);
				directoryTree.setShowsRootHandles(false);
				directoryTree.setRootVisible(true);
				directoryTree.putClientProperty("JTree.lineStyle","None");

				addTreeListener(directoryTree);
				
				currentSourceType = lst_typeName.get((SourceType.CPP.ordinal()));
				pnl_management.pnl_cpp.addTree(directoryTree);
				RefleshFrame();
			}
		});
		
		pnl_management.pnl_java.btn_languageTitle.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pnl_management.pnl_java.sclp_tree.isVisible())
					return;
				else{
					TreeSelectedRow=-1;
					pnl_management.pnl_java.sclp_tree.setVisible(false);
					pnl_management.pnl_cpp.sclp_tree.setVisible(false);
				}
				currentFileFilter = new MyFileFilter(pnl_management.extendsName_java);
				
				FileNode rootNode = createRootNode(javax.swing.filechooser.FileSystemView  
		                .getFileSystemView().getHomeDirectory().getAbsolutePath());									//创建根节点
				rootNode.isInit = true;
				DefaultTreeModel tModel = new DefaultTreeModel(rootNode);
				directoryTree = new JTree(tModel);
				directoryTree.setCellRenderer(treeRender);
				directoryTree.setShowsRootHandles(false);
				directoryTree.setRootVisible(true);
				directoryTree.putClientProperty("JTree.lineStyle","None");
				addTreeListener(directoryTree);
				
				currentSourceType = lst_typeName.get((SourceType.JAVA.ordinal()));
				pnl_management.pnl_java.addTree(directoryTree);
				RefleshFrame();
			}
		});
		//</comment>
	}
	
	private void addTreeNode(FileNode node, File dir) {
	       if (node == null || dir == null) {
	           return;
	       }
	       if (!dir.isDirectory() ) {
	           return;
	       }
	       if(dir.getName().toString().equals("Documents and Settings"))return;
	      
	       if (!node.isInit) {
	    	   node.removeAllChildren();
	           
	    	   if(node.filePath.getAbsolutePath().equals(javax.swing.filechooser.FileSystemView  
                .getFileSystemView().getHomeDirectory().getAbsolutePath())){					//如果路径为桌面，添加额外盘符
	    		   			File[] Roots =node.filePath.listRoots();
	    		   			FileNode childNode=null;
	    		   			for(int i = 0; i < Roots.length; i++){ 
	    		   				if(Roots[i].isDirectory()){ 
	    		   					childNode = new FileNode(Roots[i].toString(),Roots[i]); 
	    		   					childNode.add(new FileNode("null",new File("tempFile")));
	    		   					node.add( childNode); 
	    		   					childNode = null; 
	    		   				} 
	    		   			}
	    	   }
	    	   
	    	   // get all files in node
	           File file[] = dir.listFiles();
	           File sourceFiles[] = dir.listFiles(this.currentFileFilter );
	           
	           if(file.length == 0 && sourceFiles.length==0) {
	        	   ((DefaultTreeModel) this.directoryTree.getModel()).insertNodeInto(
	        			   new FileNode("null",new File("null")), node, node.getChildCount());
	           }
	           
	           FileNode treeNode;
			for (int i = 0; i < file.length; i++) {
	              // hidden is not show
	              if (file[i].isDirectory() && !file[i].isHidden()) {		
	                  // create node
	                  treeNode = new FileNode(file[i].getName(),file[i]);
	                  treeNode.add(new FileNode("null",new File("temp")));
	                  // add to tree
	                  ((DefaultTreeModel) this.directoryTree.getModel()).insertNodeInto(
	                         treeNode, node, node.getChildCount());
	                  
	      			DefaultTreeModel model =(DefaultTreeModel) directoryTree.getModel();
	      			model.nodeStructureChanged(node);
	                 // this.treeNode = null;
	              }
	           }
	           for(int i = 0;i<sourceFiles.length;i++){
	        	   treeNode = new FileNode(sourceFiles[i].getName(),sourceFiles[i]);
	        	   ((DefaultTreeModel)(this.directoryTree.getModel())).insertNodeInto(
	        			   treeNode, node, node.getChildCount());
	        	   DefaultTreeModel model =(DefaultTreeModel) directoryTree.getModel();
	      			model.nodeStructureChanged(node);
	           }
	       }
	       node.isInit = true;
	       
	    }
	
	private FileNode createRootNode(String root){ 
	      File currentDirectory = new File(root); 
	      FileNode rootNode = new FileNode(currentDirectory.getName(),currentDirectory); 
	      File[] files = currentDirectory.listFiles(this.currentFileFilter);
	      File[] dirs = currentDirectory.listFiles(new MyDirFilter());
	      File[] Roots =currentDirectory.listRoots();
	      FileNode childNode=null;
	      for(int i = 0; i < Roots.length; i++){ 
	          if(Roots[i].isDirectory()){ 
	        	  childNode = new FileNode(Roots[i].toString(),Roots[i]); 
	        	  childNode.add(new FileNode("null",new File("tempFile")));
	              rootNode.add( childNode); 
	              childNode = null; 
	          } 
	      }
	      for(int i = 0; i < dirs.length; i++){ 
	          if(dirs[i].isDirectory() &&!dirs[i].isHidden()){ 
	        	  childNode = new FileNode(dirs[i].getName(),dirs[i]); 
	        	  childNode.add(new DefaultMutableTreeNode("null"));
	              rootNode.add( childNode); 
	              childNode = null; 
	          } 
	      }
	      for(int i = 0; i < files.length; i++){ 
	          if(files[i].isFile()){ 
	        	  childNode =  new FileNode( files[i].getName(),files[i]); 
	              rootNode.add( childNode); 
	              childNode = null; 
	          } 
	      } 
	        return rootNode; 
	    }
	
	public void RefleshFrame(){
		 currentDirectory =  javax.swing.filechooser.FileSystemView  
		            .getFileSystemView().getHomeDirectory();//获得桌面路径
		pnl_fileSelection.refresh_sourceFilesList();
	}
	
	private void exit(){
		 Object[] options = { "确定", "取消" };
		    JOptionPane pane2 = new JOptionPane("真想退出吗?", JOptionPane.QUESTION_MESSAGE,
		        JOptionPane.YES_NO_OPTION, null, options, options[1]);
		    JDialog dialog = pane2.createDialog(this, "Information");
		    dialog.setVisible(true);
		    Object selectedValue = pane2.getValue();
		    if (selectedValue == null || selectedValue == options[1]) {
		      setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 这个是关键
		    } else if (selectedValue == options[0]) {
		      setDefaultCloseOperation(EXIT_ON_CLOSE);
		    }
	}

	public static void main(String[] args){
		new MainFrame().setVisible(true);
	}
}
