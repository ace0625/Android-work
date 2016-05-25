package com.example.distanceproject;

import android.app.Activity;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	EditText et1, et2;
	TMapView mapView = null;
	LocationManager manager = null;
	Location location = null;
	TMapPoint point = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mapView = (TMapView)findViewById(R.id.tMap);
		manager = (LocationManager)getSystemService(LOCATION_SERVICE);
		location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		if(location != null)
		{
			point = new TMapPoint(location.getLatitude(), location.getLongitude());	
		}
		new Thread() //anonymous inner type
		{
			public void run()
			{
				mapView.setSKPMapApiKey("20e0098a-451b-3a54-af4f-dba319e1d211");
				mapView.setLanguage(mapView.LANGUAGE_KOREAN);
				
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						
						setUpMap();
					}
				});
			}
		}.start();
	}

	void setUpMap()
	{
		mapView.setCompassMode(true);
		mapView.setTrackingMode(true);
//		mapView.setTrafficInfo(true);
		mapView.setSightVisible(true);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
