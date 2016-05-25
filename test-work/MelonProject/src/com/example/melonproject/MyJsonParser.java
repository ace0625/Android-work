package com.example.melonproject;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class MyJsonParser {
	private static final String TAG = "MainActivity";
	public static Melon parse(String data)
	{
		Melon melon = null;
		JSONObject object = null;
		JSONObject jMelon = null;
		JSONObject jSongs = null;
		try {
			object = new JSONObject(data);
			melon = new Melon();
			jMelon = object.getJSONObject("melon");
			melon.setMenuId(jMelon.getString("menuId"));
			melon.setCount(jMelon.getInt("count"));
			melon.setPage(jMelon.getInt("page"));
			melon.setTotalPages(jMelon.getInt("totalPages"));
			melon.setRankDay(jMelon.getString("rankDay"));
			melon.setRankHour(jMelon.getString("rankHour"));
			
			jSongs = jMelon.getJSONObject("songs");
			JSONArray songArray = jSongs.getJSONArray("song");
			Song song = null;
			JSONObject jSong;
			for(int i=0; i<songArray.length(); i++)
			{
				jSong = (JSONObject)songArray.get(i);
				song = new Song();
				
				song.setSongId(jSong.getString("songId"));
				song.setSongName(jSong.getString("songName"));
				
				JSONObject jTemp = jSong.getJSONObject("artists");
				JSONArray jATemp = jTemp.getJSONArray("artist");
				Artist artist = null;
				JSONObject aTemp = null;
				for(int j=0; j<jATemp.length(); j++)
				{
					aTemp = jATemp.getJSONObject(j);
					artist = new Artist();
					artist.setArtistId(aTemp.getString("artistId"));
					artist.setArtistName(aTemp.getString("artistName"));
					
					song.getArtists().add(artist);
				}
				song.setAlbumId(jSong.getString("albumId"));
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
			

		} catch (Exception e) {
			Log.v(TAG, "json parse error : " + e);
			// TODO: handle exception
		}finally{
			
		}
		return melon;
	}
}
