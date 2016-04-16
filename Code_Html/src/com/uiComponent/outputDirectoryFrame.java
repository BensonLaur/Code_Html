package com.uiComponent;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import com.dataStructure.FileNode;
import com.dataStructure.RightClickMenu;
import com.function.MyDirFilter;

public class outputDirectoryFrame extends DialogFrame {
	private JPanel pnl_recover = new JPanel();
	private JPanel pnl_bottom = new JPanel();
	public static JTree directoryTree = new JTree();
	private JScrollPane scrl_DirectoryTree = new JScrollPane(directoryTree);
	
	private JLabel lbl_currentOutDir = new JLabel("当前输出目录：");
	public static JTextField tfd_currentOutDir = new JTextField();
	private JButton btn_recover = new JButton("恢复默认文件夹");
	private JButton btn_save = new JButton("保存");
	private JLabel lbl_outputDirectory = new JLabel("所选文件夹：");
	private JTextField tfd_outputDirectory = new JTextField();
	private FileNode rootNode;
	private FilenameFilter currentFileFilter;
	private FileNode treeNode;
	private File selectedDirectory = null;
	public static File directoryToSave = null;
	private FileNode pointedNode = null;
	private RightClickMenu popMenu = new RightClickMenu(RightClickMenu.TYPE_RESOURCE );
	private TreePath TreeSelectedPath;
	private int TreeSelectedRow;

	private  Icon collapsedIcon = new ImageIcon(getClass().getResource("/com/material/collapsedIcon.png"));
	private  Icon expandedIcon = new ImageIcon(getClass().getResource("/com/material/expandedIcon.png"));
	
	public outputDirectoryFrame(Frame owner){
		super(owner);
		initTreeScrollPane();
		initLayout();
		initButtonListener();
		initPopMenuListener();
	}
	private void initTreeScrollPane(){
		rootNode =createRootNode(javax.swing.filechooser.FileSystemView  
                .getFileSystemView().getHomeDirectory().getAbsolutePath());									//创建根节点
		rootNode.isInit = true;
		DefaultTreeModel tModel = new DefaultTreeModel(rootNode);
		directoryTree = new JTree(tModel);																								//创建树
		directoryTree.setShowsRootHandles(true);
		directoryTree.setRootVisible(true);
		directoryTree.putClientProperty("JTree.lineStyle","None");
        UIManager.put("Tree.collapsedIcon", collapsedIcon);  
        UIManager.put("Tree.expandedIcon",expandedIcon);  
		scrl_DirectoryTree.setViewportView(directoryTree);
		addTreeListener(directoryTree);																					    			//为树注册各种监听器
		
	}

	private void initLayout(){
		this.tfd_currentOutDir.setEditable(false);
		this.tfd_outputDirectory.setEditable(false);
		
		GroupLayout recoverLayout = new GroupLayout(pnl_recover);
		pnl_recover.setLayout(recoverLayout);
		recoverLayout.setAutoCreateContainerGaps(true);
		recoverLayout.setAutoCreateGaps(true);
		GroupLayout.SequentialGroup hGroup = recoverLayout.createSequentialGroup();
		hGroup.addGroup(recoverLayout.createParallelGroup().addComponent(lbl_currentOutDir));
		hGroup.addGroup(recoverLayout.createParallelGroup().addComponent(tfd_currentOutDir));
		hGroup.addGroup(recoverLayout.createParallelGroup().addComponent(btn_recover));
		GroupLayout.SequentialGroup vGroup = recoverLayout.createSequentialGroup();
		vGroup.addGroup(recoverLayout.createParallelGroup().addComponent(lbl_currentOutDir).addComponent(tfd_currentOutDir).addComponent(btn_recover));
		recoverLayout.setHorizontalGroup(hGroup);
		recoverLayout.setVerticalGroup(vGroup);
		
		GroupLayout bottomLayout = new GroupLayout(pnl_bottom);
		pnl_bottom.setLayout(bottomLayout);
		bottomLayout.setAutoCreateContainerGaps(true);
		bottomLayout.setAutoCreateGaps(true);
		GroupLayout.SequentialGroup hGroup2 = bottomLayout.createSequentialGroup();
		hGroup2.addGroup(bottomLayout.createParallelGroup().addComponent(lbl_outputDirectory,GroupLayout.DEFAULT_SIZE,40,GroupLayout.DEFAULT_SIZE));
		hGroup2.addGroup(bottomLayout.createParallelGroup().addComponent(tfd_outputDirectory,GroupLayout.DEFAULT_SIZE,140,GroupLayout.DEFAULT_SIZE));
		hGroup2.addGroup(bottomLayout.createParallelGroup().addComponent(btn_save,GroupLayout.DEFAULT_SIZE,40,GroupLayout.DEFAULT_SIZE));
		GroupLayout.SequentialGroup vGroup2 = bottomLayout.createSequentialGroup();
		vGroup2.addGroup(bottomLayout.createParallelGroup().addComponent(lbl_outputDirectory).addComponent(tfd_outputDirectory).addComponent(btn_save));
		bottomLayout.setHorizontalGroup(hGroup2);
		bottomLayout.setVerticalGroup(vGroup2);
		
		this.setLayout(new BorderLayout(2,2));
		this.add(pnl_recover,BorderLayout.NORTH);
		this.add(pnl_bottom,BorderLayout.SOUTH);
		this.add(scrl_DirectoryTree);
	}
	
	private void addTreeListener(JTree directoryTree){
		directoryTree.addTreeExpansionListener(new TreeExpansionListener() {
            private FileNode selectedTreeNode;

			//展开
            public void treeExpanded(TreeExpansionEvent event) {
            	selectedTreeNode =(FileNode) event.getPath().getLastPathComponent();
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
            public void mousePressed(MouseEvent e) {
            	
            }
			
			public void mouseClicked(MouseEvent e) {
				JTree tree = (JTree)(e.getSource());
				
				TreeSelectedPath = tree.getPathForLocation(e.getX(), e.getY());
				if(TreeSelectedPath==null)return;
				tree.setSelectionPath(TreeSelectedPath);

				TreeSelectedRow = tree.getRowForLocation(e.getX(), e.getY());
				
            	FileNode OpenedNode = (FileNode)tree.getLastSelectedPathComponent();
            	if(OpenedNode != null){
            		selectedDirectory = OpenedNode.filePath;
            		System.out.println("selected on:  "+selectedDirectory);
            		if(!OpenedNode.fileName.equals("null")){
            			 tfd_outputDirectory.setText(selectedDirectory.getAbsolutePath());
            		}
            	
            		if(e.getButton()==MouseEvent.BUTTON3){
            			popMenu.show(tree, e.getX(), e.getY());
            		}
            	}
            	
			}
        });
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
	           // get all files in node
	           File directorys[] = dir.listFiles(new  MyDirFilter());
	           if(directorys.length == 0) {
	        	   ((DefaultTreeModel) this.directoryTree.getModel()).insertNodeInto(
	        			   new FileNode("null",new File("null")), node, node.getChildCount());
	           }else{
	        	   for (int i = 0; i < directorys.length; i++) {
	 	              // hidden is not show
	 	              if ( !directorys[i].isHidden()) {		
	 	                  // create node
	 	                  this.treeNode = new FileNode(directorys[i].getName(),directorys[i]);
	 	                  this.treeNode.add(new FileNode("null",new File("temp")));
	 	                  // add to tree
	 	                  ((DefaultTreeModel) this.directoryTree.getModel()).insertNodeInto(
	 	                         treeNode, node, node.getChildCount());
	 	                  
	 	      			DefaultTreeModel model =(DefaultTreeModel) directoryTree.getModel();
	 	      			model.nodeStructureChanged(node);
	 	                 // this.treeNode = null;
	 	              }
	 	           }
	           }
	       }
	       node.isInit = true;
	       
	    }
	
	private FileNode createRootNode(String root){ 
	      File currentDirectory = new File(root); 
	      FileNode rootNode = new FileNode(currentDirectory.getName(),currentDirectory); 
	      File[] dirs = currentDirectory.listFiles(new MyDirFilter());
	      File[] Roots =currentDirectory.listRoots();
	      for(int i = 0; i < Roots.length; i++){ 
	          if(Roots[i].isDirectory()){ 
	              this.rootNode = new FileNode(Roots[i].toString(),Roots[i]); 
	              this.rootNode.add(new FileNode("null",new File("tempFile")));
	              rootNode.add(this.rootNode); 
	              this.rootNode = null; 
	          } 
	      }
	      for(int i = 0; i < dirs.length; i++){ 
	          if(dirs[i].isDirectory() &&!dirs[i].isHidden()){ 
	              this.rootNode = new FileNode(dirs[i].getName(),dirs[i]); 
	              this.rootNode.add(new DefaultMutableTreeNode("null"));
	              rootNode.add(this.rootNode); 
	              this.rootNode = null; 
	          } 
	      } 
	        return rootNode; 
	    }
	
	private void initButtonListener(){
		btn_recover.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(tfd_currentOutDir.getText().equals("C:\\")){
					JOptionPane.showConfirmDialog(null,"当前目录已经为默认输出目录 。",
							"Conformation", JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				int result = JOptionPane.showConfirmDialog(null,"是否确定将输出目录恢复为："+"C:\\",
						"Conformation", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(result == 0){
							File outputDirectory = new File("C:\\");
							System.out.println("输出目录更改为"+"："+outputDirectory.getAbsolutePath()+"html_output");
							com.uiComponent.MainFrame.outputDirectory = new File("C:\\");
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
							} catch (FileNotFoundException ev) {
								ev.printStackTrace();
						   }
							JOptionPane.showConfirmDialog(null,"修改成功 !\n\n当前输出目录为："+outputDirectory.getAbsolutePath()+"html_output",
									"Conformation", JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
							
				}
			}
		});
		
		btn_save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				directoryToSave = selectedDirectory;
				
				if(directoryToSave == null){
					JOptionPane.showConfirmDialog(null,"未选中新目录！",
							"Information", JOptionPane.CLOSED_OPTION,JOptionPane.INFORMATION_MESSAGE);
				}else{
							com.uiComponent.MainFrame.saveOutputSetting();
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
		
		popMenu.menuItem3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("JMenuItem(\"复制\")");
			}
		});
		
		popMenu.menuItem4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("JMenuItem(\"粘贴\")");
			}
		});
		
		popMenu.menuItem5.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("JMenuItem(\"删除\")");
			}
		});
	}
	/*	
	public static void main(String[] args){
		 outputDirectoryFrame frame = new  outputDirectoryFrame();
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}*/
	
}
