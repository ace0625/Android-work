package com.example.corsorproject;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class myCursorAdapter extends CursorAdapter {

	Context context;
	Cursor c;
	int layout;
	
	public myCursorAdapter(Context context, Cursor c, int layout) { //layout 추가 맴버 변수화
		super(context, c, layout);
		this.context = context;
		this.c = c;
		this.layout = layout;
	}
	
	int[] imgRes = {R.drawable.hp, R.drawable.lg, R.drawable.sam};
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		
		TextView tv1 = (TextView)view.findViewById(R.id.textView1);
		tv1.setText(cursor.getString(1) + cursor.getString(2));
		
		TextView tv2 = (TextView)view.findViewById(R.id.textView2);
		tv2.setText(cursor.getInt(3) +"");
		
		ImageView img = (ImageView)view.findViewById(R.id.imageView1);
		img.setImageResource(imgRes[cursor.getInt(5)]);
		
		
		

	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		return View.inflate(context, layout, null);
	}

}
