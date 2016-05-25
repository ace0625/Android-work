package com.example.corsorproject;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	
	Context context;
	int layout;
	ArrayList<MemberVO> data;
	
	public MyAdapter(Context context, int layout, ArrayList<MemberVO> data) {
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
		TextView tv1, tv2;
		ImageView img;
	}
	int[] imgRes = {R.drawable.hp, R.drawable.lg, R.drawable.sam};
	
	@Override
	public View getView(int position, View cView, ViewGroup parent) {
		Holder holder = null;
		if(cView == null)
		{
			cView = View.inflate(context, layout, null);
			holder = new Holder();
			holder.tv1 = (TextView)cView.findViewById(R.id.textView1);
			holder.tv2 = (TextView)cView.findViewById(R.id.textView2);
			holder.img = (ImageView)cView.findViewById(R.id.imageView1);
			cView.setTag(holder);
		}
		else
		{
			holder = (Holder)cView.getTag();
		}
		MemberVO member = data.get(position);
		holder.tv1.setText(member.getfName() + member.getlName());
		holder.tv2.setText(member.getAge() +"");
		holder.img.setImageResource(imgRes[member.getBigo()]);
		return cView;
	}

}
