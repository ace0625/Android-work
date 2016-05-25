package com.example.assignment2;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	Context context;
	int layout;
	ArrayList<Item> data = null;
	pList plist;
	public MyAdapter(){}
	
	public MyAdapter(Context context, int layout, ArrayList<Item> data)
	{
		this.context = context;
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
		ImageView img;
		TextView title, count, category, price; 
		
	}
	@Override
	public View getView(int position, View cView, ViewGroup parent) {
		Holder holder = null;
		if(cView == null)
		{
			cView = View.inflate(context, R.layout.item, null);
			holder = new Holder();
			holder.img = (ImageView)cView.findViewById(R.id.imageView1);
			holder.title = (TextView)cView.findViewById(R.id.title);
			holder.count = (TextView)cView.findViewById(R.id.count);
			holder.category = (TextView)cView.findViewById(R.id.category);
			holder.price = (TextView)cView.findViewById(R.id.price);
			cView.setTag(holder);	
		}
		else
		{
			holder = (Holder)cView.getTag();
		}
		Item item = plist.getItemList().get(position);
//		Bitmap bitmap;
//		byte[] brr = IOUtils.toByteArray(item.getImage().ge);
//		bitmap = BitmapFactory.decodeByteArray(brr, 0, brr.length);
//		img.setImageBitmap(bitmap);
		String image = item.getImage();
		File sdFile = Environment.getExternalStorageDirectory();
		File f = new File(sdFile, image);
		Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
		holder.img.setImageBitmap(bitmap);
		
		holder.title.setText(item.getTitle()+"");
		holder.count.setText(item.getCount()+"");
		holder.category.setText(item.getCategory()+"");
		holder.price.setText(item.getPrice()+"");
		
		return cView;
	}

}
