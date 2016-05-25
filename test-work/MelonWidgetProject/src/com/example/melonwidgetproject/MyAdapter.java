package com.example.melonwidgetproject;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	int layout;
    Melon data;
//	ArrayList<Song> Sdata = null;
//	ArrayList<Artist> Adata = null;
	Context context;
	
	public void setData(Melon data)
	{
		this.data = data;
	}
	public MyAdapter(Context context, int layout, Melon data) 
	{
		this.context = context;
		this.layout = layout;
		this.data = data;
	}

	@Override
	public int getCount() {
		int size = 0;
		if(data.getSongs() != null)
		{
			size = data.getSongs().size();
		}
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.getSongs().get(position);
	}

	@Override
	public long getItemId(int position) {
		
		// TODO Auto-generated method stub
		return position;
	}

	class Holder
	{
		TextView rank, title, artist;
		
	}
	@Override
	public View getView(int position, View cView, ViewGroup parent) {
		Holder holder = null;
		
		if(cView == null)
		{
			cView = View.inflate(context, R.layout.appwidget, null);
			holder = new Holder();
			holder.rank = (TextView)cView.findViewById(R.id.textView1);
			holder.title = (TextView)cView.findViewById(R.id.textView2);
			holder.artist = (TextView)cView.findViewById(R.id.textView3);
			cView.setTag(holder);
		}
		else
		{
			holder = (Holder)cView.getTag();
		}
		Song song = data.getSongs().get(position);
		holder.rank.setText(song.getCurrentRank()+"");
		holder.title.setText(song.getSongName()+"");
		holder.artist.setText(song.getArtists().get(0).getArtistName()+"");

		return cView;
	}

}
