package com.dataStructure;

import javax.swing.Icon;
import javax.swing.text.IconView;
import javax.swing.tree.DefaultMutableTreeNode;

import java.io.File;

public class FileNode extends DefaultMutableTreeNode {
	public boolean isInit = false;
	public String fileName = null;
	public File filePath = null;
	public Icon fileIcon = null;

	public FileNode(String fileName,File filePath){
		super(fileName);
		 this.fileName = fileName;
		 this.filePath = filePath;
	}
	public FileNode(String fileName,File filePath,Icon fileIcon){
		super(fileName);
		 this.fileName = fileName;
		 this.filePath = filePath;
		 this.fileIcon = fileIcon;
	}
}
