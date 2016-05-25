package com.example.melonproject;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	MainActivity mainactivity;
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
		TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;
		
	}
	@Override
	public View getView(int position, View cView, ViewGroup parent) {
		Holder holder = null;
		
		if(cView == null)
		{
			cView = View.inflate(context, R.layout.item, null);
			holder = new Holder();
			holder.tv1 = (TextView)cView.findViewById(R.id.textView1);
			holder.tv2 = (TextView)cView.findViewById(R.id.textView2);
			holder.tv3 = (TextView)cView.findViewById(R.id.textView3);
			holder.tv4 = (TextView)cView.findViewById(R.id.textView4);
			holder.tv5 = (TextView)cView.findViewById(R.id.textView5);
			holder.tv6 = (TextView)cView.findViewById(R.id.textView6);
			holder.tv7 = (TextView)cView.findViewById(R.id.textView7);
			holder.tv8 = (TextView)cView.findViewById(R.id.textView8);
			cView.setTag(holder);
		}
		else
		{
			holder = (Holder)cView.getTag();
		}
		Song song = data.getSongs().get(position);
		String date = data.getRankDay();
		String year = date.substring(0,4);
		String month = date.substring(4,6);
		String day = date.substring(6,date.length());
		String time  = data.getRankHour();
		holder.tv1.setText(year +"년 " +month +"월 "+ day+ "일" );
		holder.tv2.setText(time);
		holder.tv3.setText(song.getCurrentRank() +"");
		holder.tv4.setText(song.getPastRank()+"");
		holder.tv5.setText(song.getSongName()+"");
		holder.tv6.setText(song.getPlayTime()+"");
		holder.tv7.setText(song.getAlbumName()+"");
		holder.tv8.setText(song.getArtists().get(0).getArtistName()+"");

		// TODO Auto-generated method stub
		return cView;
	}

}
