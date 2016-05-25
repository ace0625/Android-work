package com.example.list3project;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends BaseAdapter {
	private static final String TAG = "MainActivity";
	Context context;
	int layout;
	ArrayList<MyItem> data;
	
	public MyAdapter(Context context, int layout, ArrayList<MyItem> data)
	{
		this.context = context;
		this.layout = layout;
		this.data = data;
	}
	
	@Override
	public int getCount() {
		
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

	class ViewHolder
	{
		TextView tv;
		ImageView img;
		Button btn;
	}
	@Override
	public View getView(int position, View currentView, ViewGroup parent) {
		Log.v(TAG, "getView : position : " + position);
		final MyItem item = data.get(position);
		ViewHolder holder = null;
		if(currentView == null)
		{
			currentView = View.inflate(context, layout, null);
			holder = new ViewHolder();
			holder.tv = (TextView)currentView.findViewById(R.id.textView1);
			holder.img = (ImageView)currentView.findViewById(R.id.imageView1);
			holder.btn = (Button)currentView.findViewById(R.id.button1);
			currentView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)currentView.getTag();
		}
		holder.tv.setText(item.getName());
		holder.img.setImageResource(item.getImageRes());
		holder.btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(context, item.getName(), Toast.LENGTH_SHORT).show();
			}
		});
		
//		TextView tv = (TextView)currentView.findViewById(R.id.textView1);
//		ImageView img = (ImageView)currentView.findViewById(R.id.imageView1);
//		Button btn = (Button)currentView.findViewById(R.id.button1);
//		tv.setText(item.getName());
//		
//		if(position %  3 == 0)
//		{
//			tv.setTextColor(Color.WHITE);
//		}
//		else
//		{
//			tv.setTextColor(Color.BLACK);
//		}
//		
		
//		img.setImageResource(item.getImageRes());
		
		
		return currentView;
	}

}
