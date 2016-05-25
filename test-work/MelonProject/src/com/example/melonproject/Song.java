package com.example.melonproject;

import java.io.Serializable;
import java.util.ArrayList;

public class Song implements Serializable {

	private static final long serialVersionUID = 3964440762234754695L;
	
	private String songId;
	private String songName;
	
	private ArrayList<Artist> artists = new ArrayList<Artist>();
	
	private String albumId;
	private String albumName;
	private int currentRank;
	private int pastRank;
	private int playTime;
	private String issueDate;
	private boolean isTitleSong;
	private boolean isHitSong;
	private boolean isAdult;
	private boolean isFree;
	public String getSongId() {
		return songId;
	}
	public void setSongId(String songId) {
		this.songId = songId;
	}
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
	public String getAlbumId() {
		return albumId;
	}
	public void setAlbumId(String albumId) {
		this.albumId = albumId;
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
	@Override
	public String toString() {
		return "Song [songId=" + songId + ", songName=" + songName
				+ ", artists=" + artists + ", albumId=" + albumId
				+ ", albumName=" + albumName + ", currentRank=" + currentRank
				+ ", pastRank=" + pastRank + ", playTime=" + playTime
				+ ", issueDate=" + issueDate + ", isTitleSong=" + isTitleSong
				+ ", isHitSong=" + isHitSong + ", isAdult=" + isAdult
				+ ", isFree=" + isFree + "]";
	}
}