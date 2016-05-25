package com.example.naverproject;

import java.util.ArrayList;

public class Channel {
	private static final String TAG = "MainActivity";
	private String title;
	private String link;
	private String decription;
	private String lastBuildDate;
	private String total;
	private String start;
	private String display;
	private ArrayList<Mitem> itemList;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public String getLastBuildDate() {
		return lastBuildDate;
	}
	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public ArrayList<Mitem> getItemList() {
		return itemList;
	}
	public void setItemList(ArrayList<Mitem> itemList) {
		this.itemList = itemList;
	}
	
	
}
//<title>Naver Open API - movie ::'홍길동'</title>
//<link>http://search.naver.com</link>
//<description>Naver Search Result</description>
//<lastBuildDate>Mon, 14 Oct 2013 12:46:08 +0900</lastBuildDate>
//<total>24</total>
//<start>1</start>
//<display>10</display>