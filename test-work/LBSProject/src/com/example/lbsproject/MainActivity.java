package com.example.lbsproject;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	EditText et;
	LocationManager manager;
	Location location = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText)findViewById(R.id.editText1);
		manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		
		location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//		location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		if(location != null)
		{ //가장 마지막에 알았던 위치정보
			String text = String.format("마지막 위치 위도: %s  경도: %s 고도: %s\n", 
					location.getLatitude(), 
					location.getLongitude(), 
					location.getAltitude());
			et.append(text);
			
		}
	}
	
	@Override
	protected void onPause() {
		manager.removeUpdates(listener);
		super.onPause();
	}

	@Override
	protected void onResume() {
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 100, listener);
		super.onResume();
	}

	int cnt = 0;
	LocationListener listener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location location) {
			cnt++;
			String text = String.format(" 마지막 위치 위도: %s  경도: %s 고도: %s\n",
//					
//					cnt
					location.getLatitude(), 
					location.getLongitude(), 
					location.getAltitude());
				
			et.append(text);
			manager.removeUpdates(listener);//한번만 읽어들일때
			
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
