package com.example.pt_widget;

import java.io.Serializable;

public class Artist implements Serializable {
	private static final long serialVersionUID = 7285722444789604324L;
	
	private int artistId;
	private String artistName;

	

	public int getArtistId() {
		return artistId;
	}

	public void setArtistId(int artistId) {
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
