package com.example.geocorderproject;

import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	EditText et;
	Geocoder coder = null;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String lat = "37.46633";
			doFindAddress(Double.parseDouble(lat), 126.960224);
//			doFindAddress("낙성대");

			
		}
	};
	void doFindAddress(double lat, double lon)
	{
		List<Address> list = null;  //내가간 위치 흔적남기기
		try {
			list = coder.getFromLocation(lat,lon, 5);
			StringBuilder sb = new StringBuilder();
			int cnt = 0;
			for(Address address : list)
			{
				cnt = address.getMaxAddressLineIndex() + 1;
				String temp = "";
				for (int i=0; i<cnt; i++)
				{
					temp += address.getAddressLine(i) + " ";
				}
				sb.append(address.getFeatureName() + " " + temp + "\n");
			}
			et.setText(sb.toString());
			Log.v(TAG, sb.toString());
		} catch (Exception e) {
			Log.v(TAG, "find error: " +e);
		}
	}
//	void doFindAddress(String name)
//	{
//		List<Address> list = null;  //내가간 위치 흔적남기기
//		try {
//			list = coder.getFromLocationName(name, 5);
//			StringBuilder sb = new StringBuilder();
//			int cnt = 0;
//			for(Address address : list)
//			{
//				cnt = address.getMaxAddressLineIndex() + 1;
//				String temp = "";
//				for (int i=0; i<cnt; i++)
//				{
//					temp += address.getAddressLine(i) + " ";
//				}
//				sb.append(address.getFeatureName() + " " + temp + "\n");
//			}
//			et.setText(sb.toString());
//			Log.v(TAG, sb.toString());
//		} catch (Exception e) {
//			Log.v(TAG, "find error: " +e);
//		}
//	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et = (EditText)findViewById(R.id.editText1);
		findViewById(R.id.button1).setOnClickListener(bHandler);
		
		coder = new Geocoder(this, Locale.KOREAN);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
