package com.example.xmlparsehomework;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	MainActivity mainactivity;
	int layout;
    pList data;
//	ArrayList<Song> Sdata = null;
//	ArrayList<Artist> Adata = null;
	Context context;
	
	public void setData(pList data)
	{
		this.data = data;
	}
	public MyAdapter(Context context, int layout, pList data) 
	{
		this.context = context;
		this.layout = layout;
		this.data = data;
	}

	@Override
	public int getCount() {
		int size = 0;
		if(data.getItemList() != null)
		{
			size = data.getItemList().size();
		}
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.getItemList().get(position);
	}

	@Override
	public long getItemId(int position) {
		
		// TODO Auto-generated method stub
		return position;
	}

	class Holder
	{
		TextView title, count, price, category,image2;
		ImageView image;
		
	}
	@Override
	public View getView(int position, View cView, ViewGroup parent) {
		Holder holder = null;
		
		if(cView == null)
		{
			cView = View.inflate(context, R.layout.listview, null);
			holder = new Holder();
			holder.title = (TextView)cView.findViewById(R.id.title);
			holder.count = (TextView)cView.findViewById(R.id.count);
			holder.price = (TextView)cView.findViewById(R.id.price);
			holder.category = (TextView)cView.findViewById(R.id.category);
		
			holder.image=(ImageView)cView.findViewById(R.id.image);
			cView.setTag(holder);
		}
		else
		{
			holder = (Holder)cView.getTag();
		}
		item item = data.getItemList().get(position);
		holder.title.setText(item.getTitle()+"");
		holder.count.setText(item.getCount()+"");
		holder.price.setText(item.getPrice()+"");
		
		holder.category.setText(item.getCategory()+"");
	
		// TODO Auto-generated method stub
		return cView;
	}

}
