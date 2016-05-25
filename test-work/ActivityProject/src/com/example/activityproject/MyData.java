package com.example.activityproject;

import java.io.Serializable;

public class MyData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1080800L;
	private String str;
	private int cnt;
	
	private MyData(){}
	private static MyData data = new MyData();
	
	public static MyData getInstance()
	{
		return data;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
}
