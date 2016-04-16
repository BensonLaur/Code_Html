package com.dataStructure;

import java.util.ArrayList;
import java.util.Collections;

public class OrderIntList {
	private ArrayList<Integer> list = new ArrayList<Integer>();
	
	public void add(Integer intAdded){
		boolean isAdded = false;
		for(int i=0;i<list.size();i++){
			if(list.get(i).equals(intAdded)){
				isAdded = true;	break;
			}
		}

		if(!isAdded){
			list.add(intAdded);
			sort();
		}
	}
	
	public void remove(Integer num){
		for(int i=0;i<list.size();i++){
			if(list.get(i).equals(num)){
				list.remove(i);	break;
			}
		}
	}
	
	public Integer get(int index){
		return list.get(index);
	}
	
	public int size(){
		return list.size();
	}
	
	public void removeAll(){
		list.removeAll(list);
	}
	
	private void sort(){
		Collections.sort(list);
	}
}

