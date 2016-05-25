package com.example.xmlparsehomework;

import java.io.Serializable;
import java.util.ArrayList;



public class pList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7042081849890580239L;
	private ArrayList<item> itemList=new ArrayList<item>();
	
	//private ArrayList<Song> songs = new ArrayList<Song>();
	public ArrayList<item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<item> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "pList [toString()=" + super.toString() + "]";
	}

	
}