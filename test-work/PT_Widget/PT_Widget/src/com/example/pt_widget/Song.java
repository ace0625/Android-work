package com.example.pt_widget;

import java.io.Serializable;
import java.util.ArrayList;

public class Song implements Serializable{
	private static final long serialVersionUID = 1727781081720063349L;
	
	private String songName;
	private ArrayList<Artist> artists = new ArrayList<Artist>();
	private int albumId;
	
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
	private String albumName;
	private int currentRank, songId;
	public int getSongId() {
		return songId;
	}
	private int pastRank;
	public void setSongId(int songId) {
		this.songId = songId;
	}
	private int playTime;
	private String issueDate;
	private boolean isTitleSong, isHitSong, isAdult, isFree;
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public ArrayList<Artist> getArtists() {
		return artists;
	}
	public void setArtists(ArrayList<Artist> artists) {
		this.artists = artists;
	}
	public int getAlbumId() {
		return albumId;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public int getCurrentRank() {
		return currentRank;
	}
	public void setCurrentRank(int currentRank) {
		this.currentRank = currentRank;
	}
	public int getPastRank() {
		return pastRank;
	}
	public void setPastRank(int pastRank) {
		this.pastRank = pastRank;
	}
	public int getPlayTime() {
		return playTime;
	}
	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public boolean isTitleSong() {
		return isTitleSong;
	}
	public void setTitleSong(boolean isTitleSong) {
		this.isTitleSong = isTitleSong;
	}
	public boolean isHitSong() {
		return isHitSong;
	}
	@Override
	public String toString() {
		return "Song [songName=" + songName + ", artists=" + artists
				+ ", albumId=" + albumId + ", albumName=" + albumName
				+ ", currentRank=" + currentRank + ", songId=" + songId
				+ ", pastRank=" + pastRank + ", playTime=" + playTime
				+ ", issueDate=" + issueDate + ", isTitleSong=" + isTitleSong
				+ ", isHitSong=" + isHitSong + ", isAdult=" + isAdult
				+ ", isFree=" + isFree + "]";
	}
	public void setHitSong(boolean isHitSong) {
		this.isHitSong = isHitSong;
	}
	public boolean isAdult() {
		return isAdult;
	}
	public void setAdult(boolean isAdult) {
		this.isAdult = isAdult;
	}
	public boolean isFree() {
		return isFree;
	}
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

}
