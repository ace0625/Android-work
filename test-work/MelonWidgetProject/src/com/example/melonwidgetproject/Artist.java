package com.example.melonwidgetproject;

import java.io.Serializable;

public class Artist implements Serializable {

	private static final long serialVersionUID = 7285722444789604324L;
	
	private String artistId;
	private String artistName;
	
	public String getArtistId() {
		return artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	@Override
	public String toString() {
		return "Artist [artistId=" + artistId + ", artistName=" + artistName
				+ "]";
	}
	
	
	
}
