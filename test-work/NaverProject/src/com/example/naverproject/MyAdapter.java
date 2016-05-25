package com.example.naverproject;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	MainActivity activity;
	int layout;
	ArrayList<Item> data = null;
	public MyAdapter(){}
	
	public MyAdapter(MainActivity activity, int layout, ArrayList<Item> data)
	{
		this.activity = activity;
		this.layout = layout;
		this.data = data;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	class Holder
	{
		TextView tv1, tv2, tv3;
		ImageView img;
	}
	@Override
	public View getView(int position, View cView, ViewGroup parent) {
		Holder holder = null;
		if(cView == null)
		{
			cView = View.inflate(activity, R.layout.item, null);
			holder = new Holder();
			holder.tv1 = (TextView)cView.findViewById(R.id.textView1);
			holder.tv2 = (TextView)cView.findViewById(R.id.textView2);
			holder.tv3 = (TextView)cView.findViewById(R.id.textView3);
			holder.img = (ImageView)cView.findViewById(R.id.imageView1);
			cView.setTag(holder);
		}
		else
		{
			holder = (Holder)cView.getTag();
		}
		Item item = data.get(position);
		holder.tv1.setText(position + 1 + "");
		holder.tv2.setText(item.getK());
		if(item.getV().equals("0"))
		{
			holder.tv3.setText("");
		}
		else
		{
			holder.tv3.setText(item.getV());
		}
		if(item.getS().equals("+"))
		{
			holder.img.setImageResource(R.drawable.up);
		}
		else if(item.getS().equals("new"))
		{
			holder.img.setImageResource(R.drawable.inew);
		}
		else if(item.getS().equals("."))
		{
			holder.img.setImageResource(R.drawable.inew);
		}
		else if(item.getS().equals("-"))
		{
			holder.img.setImageResource(R.drawable.inew);
		}
		return cView;
	}

}
