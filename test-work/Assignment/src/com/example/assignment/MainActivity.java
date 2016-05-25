package com.example.assignment;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends Activity 
{
	private static final String TAG = "MainActivity";
	LinearLayout BackList, BackDetail;
	ListView list;
	EditText eName, eAddress;
	RadioGroup radG;
	
	
	
	ArrayList<MyItem> data = new ArrayList<MyItem>();
	MyItem item = null;
	int img = 0;
	
	MyAdapter adapter = null;
	
	void hideView()
	{
		BackList.setVisibility(View.INVISIBLE);
		BackDetail.setVisibility(View.INVISIBLE);
	}
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			hideView();
			switch(v.getId())
			{
			case R.id.bnlist:
				Log.v(TAG, "listtap");
				BackList.setVisibility(View.VISIBLE);
				break;
			case R.id.bndetail:
				Log.v(TAG, "listtap");
				BackDetail.setVisibility(View.VISIBLE);
				break;
			case R.id.btnsave:
				Createlist(img);
				BackList.setVisibility(View.VISIBLE);
				break;
			}
		}
	};
	
	void Createlist(int img)
	{
		item = new MyItem();
		item.setName(eName.getText().toString());
		item.setAddress(eAddress.getText().toString());
		item.setRadG(img);
		data.add(item);
		
	
		adapter = new MyAdapter(this, R.layout.item, data);
		list.setAdapter(adapter);
	}
	public void changeframe(int frame)
	{
		if(frame == 0)
		{
			BackList.setVisibility(View.VISIBLE);
			BackDetail.setVisibility(View.INVISIBLE);
		}
		else
		{
			BackList.setVisibility(View.INVISIBLE);
			BackDetail.setVisibility(View.VISIBLE);
		}
	}
 RadioGroup.OnCheckedChangeListener Rcheck = 
		 new RadioGroup.OnCheckedChangeListener() {
	
	public void onCheckedChanged(RadioGroup group, int checkedId) 
	{
		if(radG.getId() == R.id.radioGroup1)
		{
			switch(checkedId)
			{
			case R.id.rdTakeout:
				item.setRadG(R.drawable.takeout);
				break;
			case R.id.rdSitdown:
				item.setRadG(R.drawable.sitdown);
				break;
			case R.id.rdDelivery:
				item.setRadG(R.drawable.delivery);
				break;
			}
		}
		
	}
};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		BackList = (LinearLayout)findViewById(R.id.BackList);
		BackDetail = (LinearLayout)findViewById(R.id.BackDetail);
		
		list= (ListView)findViewById(R.id.listView1);
		findViewById(R.id.bnlist).setOnClickListener(bHandler);
		findViewById(R.id.bndetail).setOnClickListener(bHandler);
		
		eName = (EditText)findViewById(R.id.etName);
		eAddress = (EditText)findViewById(R.id.etAddress);
		radG = (RadioGroup)findViewById(R.id.radioGroup1);
		radG.setOnCheckedChangeListener(Rcheck);
		
		findViewById(R.id.btnsave).setOnClickListener(bHandler);
		
//		item = new MyItem();
//		adapter = new MyAdapter(this, R.layout.item, data);
//		list.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
