package com.example.pt_widget;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class MyJsonParser {
	private static final String TAG = "MainActivity";
	public static Melon parse(String data) {
		// TODO Auto-generated method stub
		Melon melon = null;
		JSONObject object = null;
		JSONObject jMelon = null;
		JSONObject jSongs = null;
		try {
			object = new JSONObject(data);
			melon = new Melon();
			jMelon = object.getJSONObject("melon");
			melon.setMenuId(jMelon.getInt("menuId"));
			melon.setCount(jMelon.getInt("count"));
			melon.setPage(jMelon.getInt("page"));
			melon.setTotalPages(jMelon.getInt("totalPages"));
			melon.setRankDay(jMelon.getString("rankDay"));
			melon.setRankHour(jMelon.getString("rankHour"));
			jSongs = jMelon.getJSONObject("songs");
			JSONArray songArray = jSongs.getJSONArray("song");
			JSONObject jSong = null;
			Song song = null;
			for(int i = 0; i < songArray.length(); i++){
				song = new Song();
				jSong = songArray.getJSONObject(i);
				song.setSongId(jSong.getInt("songId"));
				song.setSongName(jSong.getString("songName"));
				JSONObject jTemp = jSong.getJSONObject("artists");
				JSONArray jATemp = jTemp.getJSONArray("artist");
				Artist artist= null;
				JSONObject aTemp = null;
				for (int j = 0; j < jATemp.length(); j++) {
					artist = new Artist();
					aTemp = jATemp.getJSONObject(j);
					artist.setArtistId(aTemp.getInt("artistId"));
					artist.setArtistName(aTemp.getString("artistName"));
					song.getArtists().add(artist);
				}
				song.setAlbumId(jSong.getInt("albumId"));
				song.setAlbumName(jSong.getString("albumName"));
				song.setCurrentRank(jSong.getInt("currentRank"));
				song.setPastRank(jSong.getInt("pastRank"));
				song.setPlayTime(jSong.getInt("playTime"));
				song.setIssueDate(jSong.getString("issueDate"));
				song.setTitleSong(jSong.getBoolean("isTitleSong"));
				song.setHitSong(jSong.getBoolean("isHitSong"));
				song.setAdult(jSong.getBoolean("isAdult"));
				song.setFree(jSong.getBoolean("isFree"));
				melon.getSongs().add(song);
			}
			
		} catch (JSONException e) {
			Log.v(TAG, "json parse error : " + e);
		}
		return melon;
	}

}
