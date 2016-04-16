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
          
        //�õ�ÿ���ڵ��TreeNode  
		FileNode node = (FileNode) value;  
        if(node.filePath.isDirectory()){
        	this.setIcon(FloderIcon);
        }
        
        //�õ�ÿ���ڵ��text  
        String str = node.toString();         
          
        //�ж����ĸ��ı��Ľڵ����ö�Ӧ��ֵ����������ڵ㴫�����һ��ʵ��,����Ը���ʵ�������һ��������������ʾ��Ӧ��ͼ�꣩  
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
