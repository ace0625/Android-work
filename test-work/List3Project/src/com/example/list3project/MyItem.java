package com.example.list3project;

import java.io.Serializable;

public class MyItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int imageRes;
	private String name;
	
	public MyItem(){}
	public MyItem(int imageRes, String name)
	{
		this.imageRes = imageRes;
		this.name = name;
	}
	
	public int getImageRes() {
		return imageRes;
	}
	public void setImageRes(int imageRes) {
		this.imageRes = imageRes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "MyItem [imageRes=" + imageRes + ", name=" + name + "]";
	}
	
	
}
