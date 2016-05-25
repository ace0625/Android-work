package com.example.pt_widget;

import java.io.Serializable;
import java.util.ArrayList;

public class Melon implements Serializable {

	private static final long serialVersionUID = 235169842815924751L;
	
	private int menuId, count, page, totalPages;
	private String rankDay, rankHour;
	private ArrayList<Song> songs = new ArrayList<Song>();
	@Override
	public String toString() {
		return "Melon [menuId=" + menuId + ", count=" + count + ", page="
				+ page + ", totalPages=" + totalPages + ", rankDay=" + rankDay
				+ ", rankHour=" + rankHour + ", songs=" + songs + "]";
	}
	
	public int getMenuId() {
		return menuId;
	}
	
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public String getRankDay() {
		return rankDay;
	}
	public void setRankDay(String rankDay) {
		this.rankDay = rankDay;
	}
	public String getRankHour() {
		return rankHour;
	}
	public void setRankHour(String rankHour) {
		this.rankHour = rankHour;
	}

	public ArrayList<Song> getSongs() {
		return songs;
	}

	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}
	
	
}
