package org.kimjw.navermovie;

import java.util.ArrayList;

public class Channel {
	private String title;
	private String link;
	private String desc;
	private String total;
	private String start;
	private String disp;
	private String lastBuildDate;
	
	private ArrayList<MyItem> data ;
	
	public Channel(){
		data = new ArrayList<MyItem>();
		title = "";
		link = "";
		desc = "";
		total = "";
		start = "";
		disp = "";
		lastBuildDate = "";
	}
	public String getLastBuildDate() {
		return lastBuildDate;
	}
	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public String getDisp() {
		return disp;
	}
	public void setDisp(String disp) {
		this.disp = disp;
	}
	public ArrayList<MyItem> getData() {
		return data;
	}
	public void setData(ArrayList<MyItem> data) {
		this.data = data;
	}
	
}
