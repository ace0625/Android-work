package com.example.assignment;

import java.util.ArrayList;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends BaseAdapter {
	private static final String TAG = "MyAdapter";
	MainActivity mainactivity;
	int layout;
	ArrayList<MyItem> data;
	
	public MyAdapter(MainActivity mainactivity, int layout, ArrayList<MyItem> data ) 
	{
		this.mainactivity = mainactivity;
		this.layout = layout;
		this.data = data;
	}
	@Override
	public int getCount() 
	{
		return data.size();
	}

	@Override
	public Object getItem(int position) 
	{
		return data.get(position);
	}

	@Override
	public long getItemId(int position) 
	{
		return position;
	}
	class ViewHolder
	{
		TextView lName, lAdd;
		ImageView img;
	}
	@Override
	public View getView(int position, View cView, ViewGroup parent) 
	{
		Log.v(TAG, "Getview : position : " + position);
		final MyItem item = data.get(position);
		ViewHolder holder = null;
		if(cView == null)
		{
			cView = View.inflate(mainactivity, layout, null);
			holder = new ViewHolder();
			holder.lName = (TextView)cView.findViewById(R.id.List_Name);
			holder.lAdd = (TextView)cView.findViewById(R.id.List_Address);
			holder.img = (ImageView)cView.findViewById(R.id.List_img);
			cView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)cView.getTag();
		}
		holder.lName.setText(item.getName());
		holder.lAdd.setText(item.getAddress());
		holder.img.setImageResource(item.getRadG());
			
		cView.setOnClickListener(new View.OnClickListener() {
		
			public void onClick(View v) {
				mainactivity.eName.setText(item.getName());
				mainactivity.eAddress.setText(item.getAddress());
				mainactivity.radG.getCheckedRadioButtonId();
				mainactivity.changeframe(1);
				Toast.makeText(mainactivity, "Name: " +item.getName() 
						+ "\nAddress: " +item.getAddress(), Toast.LENGTH_SHORT).show();
			}
		});
		return cView;
	}

}

//
//context.etName.setText( item.getName());
//context.etAddr.setText( item.getAddress());
//context.rgType.clearCheck();
//context.setOrderTypeChecked( item.getType());
//context.changeFrameTo(1);
