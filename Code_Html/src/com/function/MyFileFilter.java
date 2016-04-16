package com.function;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class MyFileFilter implements FileFilter{
		private ArrayList<String> extendsName = null;
		public MyFileFilter(ArrayList<String> lst_extendsName){
			extendsName = lst_extendsName;
		}
        @Override  
        public boolean accept(File pathname) {  
        	boolean hasEN = false;
            String filename = pathname.getName().toLowerCase();  
            for(int i=0;i<extendsName.size();i++){
            	StringBuilder s = new StringBuilder(filename);
            	int startIndex = s.indexOf(".");
            	if(startIndex == -1) return false;
            	String ts = s.substring(startIndex);
            	
            	//System.out.println("ÍØÕ¹ÃûÓÐ£º"+extendsName.size() +"    ["+ts+"] and ["+ extendsName.get(i) +"]" + "  has:"+hasEN);
            	
            	if(ts.equals(extendsName.get(i))){
            		hasEN = true;
            	}
            }
            return hasEN;
        }  
}
