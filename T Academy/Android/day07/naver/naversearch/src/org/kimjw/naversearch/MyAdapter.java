package org.kimjw.naversearch;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
    private static final String TAG = "MainActivity";
	private Context mContext;
	private int layout;
	private ArrayList<MyItem> data;
	public MyAdapter(){}
	public void setData(ArrayList<MyItem> data){
		this.data = data;
	}
	public MyAdapter(Context context, int layout, ArrayList<MyItem> data){
		mContext = context;
		this.layout = layout;
		this.data = data;
	}
	public int getCount() {
		return data.size();
	}
	public Object getItem(int position) {
		return data.get(position).getK();
	}
	public long getItemId(int position) {
		return position;
	}
	public View getView(int position, View currentView, ViewGroup parent) {
		if(currentView == null){
			currentView = View.inflate(mContext, layout, null);
		}
		TextView tv = (TextView)currentView.findViewById(R.id.txtnum);
		tv.setText((position+1) + "");
		tv = (TextView)currentView.findViewById(R.id.textView1);
		tv.setText(data.get(position).getK());
		String s = data.get(position).getS();
		ImageView img = (ImageView)currentView.findViewById(R.id.img);
		if(s.equals("+")){
			img.setImageResource(R.drawable.up);
		}else if(s.equals("new")){
			img.setImageResource(R.drawable.inew);
		}else if(s.equals("-")){
			img.setImageResource(R.drawable.inew);
		}
		tv = (TextView)currentView.findViewById(R.id.txtcnt);
		tv.setText(data.get(position).getV());		
		return currentView;
	}
}
