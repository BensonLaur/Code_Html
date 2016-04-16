package com.function;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.dataStructure.FileNode;

import static com.uiComponent.MainFrame.currentFileFilter;
import static com.uiComponent.MainFrame.currentSourceType;

public class MyDefaultTreeCellRenderer extends DefaultTreeCellRenderer {
	private Icon javaIcon = new ImageIcon(getClass().getResource("/com/material/java.png"));
	private Icon cppIcon =new ImageIcon(getClass().getResource("/com/material/cpp.png"));
	private Icon cIcon =new ImageIcon(getClass().getResource("/com/material/c.png"));
	private Icon hIcon = new ImageIcon(getClass().getResource("/com/material/h.png"));
	private Icon CRootIcon =new ImageIcon(getClass().getResource("/com/material/CRoots.png"));
	private Icon RootsIcon = new ImageIcon(getClass().getResource("/com/material/roots.png"));
	private Icon FloderIcon = new ImageIcon(getClass().getResource("/com/material/floder.png"));
	private Icon DesktopIcon = new ImageIcon(getClass().getResource("/com/material/desktop.png"));
	
	public Component getTreeCellRendererComponent(JTree tree, Object value,  
            boolean sel, boolean expanded, boolean leaf, int row,  
            boolean hasFocus)  {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		setText(value.toString());
		
		if (sel)  
        {  
            setForeground(getTextSelectionColor());  
        }  
        else  
        {  
            setForeground(getTextNonSelectionColor());  
        }  
          
        //得到每个节点的TreeNode  
		FileNode node = (FileNode) value;  
        if(node.filePath.isDirectory()){
        	this.setIcon(FloderIcon);
        }
        
        //得到每个节点的text  
        String str = node.toString();         
          
        //判断是哪个文本的节点设置对应的值（这里如果节点传入的是一个实体,则可以根据实体里面的一个类型属性来显示对应的图标）  
        if (str.equals( "C:\\"))  
        {  
            this.setIcon(CRootIcon);  
        } 
        else if (str.equals("D:\\")||str.equals("E:\\")||str.equals("F:\\")||str.equals("G:\\"))  
        {  
            this.setIcon(RootsIcon);  
        }  
        
        if(currentFileFilter.accept(node.filePath)){
        	if(currentSourceType.equals("java")){
        		this.setIcon(javaIcon);
        	}
        	else if(currentSourceType.equals("cpp")){
        		if(str.contains(".cpp")) 
            		this.setIcon(cppIcon);
        		else if(str.contains(".c"))
        			this.setIcon(cIcon);
        		else if(str.contains(".h"))
            		this.setIcon(hIcon);
        	}
        }
        if(str.equals("Desktop")){
    		this.setIcon(DesktopIcon);
        }
		 
		return this;
	}
}
