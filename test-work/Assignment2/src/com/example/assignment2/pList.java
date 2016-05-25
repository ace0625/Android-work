package com.example.assignment2;

import java.util.ArrayList;

public class pList {
	private static final String TAG = "MainActivity";
	
	private ArrayList<Item> itemList = new ArrayList<Item>();

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "pList [itemList=" + itemList + "]";
	}


}
