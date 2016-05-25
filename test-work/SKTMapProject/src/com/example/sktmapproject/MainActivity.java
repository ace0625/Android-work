package com.example.sktmapproject;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.skp.Tmap.TMapCircle;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapData.TMapPathType;
import com.skp.Tmap.TMapInfo;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	TMapView tmap = null;
	LocationManager manager = null;
	Location location = null;
	boolean flag = false;
	TMapPoint point = null;
	TMapPoint start = new TMapPoint(37.56468648536046,126.98217734415019);
	TMapPoint end = new TMapPoint(35.17883196265564,129.07579349764512);
	
	@Override
	protected void onResume() {
		manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 1, lListener);
		super.onResume();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tmap = (TMapView)findViewById(R.id.tMap);
		manager = (LocationManager)getSystemService(LOCATION_SERVICE);
		boolean gpsProvider = 
				manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		if(location != null)
		{
			point = new TMapPoint(location.getLatitude(), location.getLongitude());	
		}
		new Thread() //anonymous inner type
		{
			public void run()
			{
				tmap.setSKPMapApiKey("20e0098a-451b-3a54-af4f-dba319e1d211");
				tmap.setLanguage(tmap.LANGUAGE_KOREAN);
				
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						flag = true;
						setUpMap();
					}
				});
			}
		}.start();
	}
	
	void setUpMap()
	{
		tmap.setCompassMode(true);
		tmap.setTrackingMode(true);
		tmap.setTrafficInfo(true);
		tmap.setSightVisible(true);
		tmap.setOnClickListenerCallBack(clickCallBack);
		tmap.setZoomLevel(12);   //7~19
		tmap.setOnCalloutRightButtonClickListener(rightCallBack);
//		point = new TMapPoint(37.4553343, 126.9602441);
//		setTmapLocation(point);
	}
	TMapView.OnClickListenerCallback clickCallBack = new TMapView.OnClickListenerCallback() {
		
		@Override
		public boolean onPressUpEvent(ArrayList<TMapMarkerItem> arg0,
				ArrayList<TMapPOIItem> arg1, TMapPoint arg2, PointF arg3) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean onPressEvent(ArrayList<TMapMarkerItem> markers,
				ArrayList<TMapPOIItem> pois, TMapPoint tpoint, PointF pointf) {
				Log.v(TAG, "위도 : " +tpoint.getLatitude() + "경도 : " +tpoint.getLongitude());
				Log.v(TAG, "좌표 x : " +pointf.x + "좌표 y : " +pointf.y);
				if(markers != null)
				{
					for(TMapMarkerItem item : markers)
					{
						Log.v(TAG, item.getCalloutTitle());
					}
				}
				if(pois != null)
				{
					for(TMapPOIItem item : pois)
					{
						Log.v(TAG, item.name + "거리: " +item.distance);
					}
				}
			return true;
		}
	};
	void setTmapLocation(TMapPoint point)
	{
		tmap.setLocationPoint(point.getLongitude(), point.getLatitude());
		tmap.setCenterPoint(point.getLongitude(), point.getLatitude());
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.i_location);
		tmap.setIcon(bitmap);
		tmap.setIconVisibility(true);
		
	}	
	LocationListener lListener = new LocationListener() {
	
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
		if(flag)
		{
			point = new TMapPoint(location.getLatitude(), location.getLongitude());	
			setTmapLocation(point);
			manager.removeUpdates(lListener);
		}
		
	}
};

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case 1:
			tmap.MapZoomIn();
			break;
		case 2:
			tmap.MapZoomOut();
			break;
		case 3:
			addMarker();
			break;
		case 4:
			addCircle();
			addCircle1();
			break;
		case 5:
			new POITask().execute("편의점");
			break;
		case 6:
			new NeviTask().execute(start, end);
			break;
			
		}
		return super.onOptionsItemSelected(item);
	}
	class NeviTask extends AsyncTask<TMapPoint, Void, TMapPolyLine>
	{

		@Override
		protected TMapPolyLine doInBackground(TMapPoint... params) {
			TMapData data = null;
			TMapPolyLine list = null;
			try {
				data = new TMapData();
				list = data.findPathDataWithType(TMapPathType.CAR_PATH, params[0], params[1]);
				
			} catch (Exception e) {
				Log.v(TAG, "nevi error: " +e);
			}
			return list;
		}

		@Override
		protected void onPostExecute(TMapPolyLine result) {
			ArrayList<TMapPoint> aList = result.getLinePoint();
			TMapInfo info = tmap.getDisplayTMapInfo(aList);
			int zoomLevel = info.getTMapZoomLevel();
			
			tmap.setZoomLevel(zoomLevel);
			result.setLineColor(Color.MAGENTA);
			tmap.addTMapPath(result);
			super.onPostExecute(result);
		}
		
	}
	class POITask extends AsyncTask<String, Void, ArrayList<TMapPOIItem>>
	{
		TMapData data = null;
		ArrayList<TMapPOIItem> poiList = null;
		@Override
		protected ArrayList<TMapPOIItem> doInBackground(String... params) {
			
			try {
				TMapPoint point = tmap.getCenterPoint();
				data = new TMapData();
				poiList = data.findAroundNamePOI(point, params[0]);
				
			} catch (Exception e) {
				Log.v(TAG, "POI error : " +e);
			}
			return poiList;
		}

		@Override
		protected void onPostExecute(ArrayList<TMapPOIItem> result) {
			tmap.addTMapPOIItem(result);
			
			super.onPostExecute(result);
		}
		
	}
	void addCircle1()
	{
		TMapPoint point1 = tmap.getCenterPoint();
		point1.setLatitude(point1.getLatitude() - 0.005);
		point1.setLongitude(point1.getLongitude() + 0.005);
		TMapCircle circle1 = new TMapCircle();
		circle1.setCenterPoint(point1);
		circle1.setAreaAlpha(155);
		circle1.setAreaColor(Color.YELLOW);
		circle1.setCircleWidth(10);
		circle1.setLineColor(Color.BLACK);
		circle1.setRadius(300);
		
		tmap.addTMapCircle("2", circle1);
	}
	
	void addCircle()
	{
		TMapPoint point = tmap.getCenterPoint();
		TMapCircle circle = new TMapCircle();
		circle.setCenterPoint(point);
		circle.setAreaAlpha(128);
		circle.setAreaColor(Color.RED);
		circle.setCircleWidth(3);
		circle.setLineColor(Color.BLUE);
		circle.setRadius(200);
		
		tmap.addTMapCircle("1", circle);
	}
	TMapView.OnCalloutRightButtonClickCallback rightCallBack = new TMapView.OnCalloutRightButtonClickCallback() {
		
		@Override
		public void onCalloutRightButton(TMapMarkerItem item) {
			Toast.makeText(MainActivity.this, "id: " + item.getID(), Toast.LENGTH_SHORT).show();
			
		}
	};
	void addMarker()
	{
		TMapPoint point = tmap.getCenterPoint();
		point.setLatitude(point.getLatitude() + 0.0003);
		point.setLongitude(point.getLongitude() + - 0.0003);
		TMapMarkerItem item = new TMapMarkerItem();
		item.setTMapPoint(point);
		item.setPosition(0.3f, 1f);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pin_tevent);
		item.setIcon(bitmap);
		
		Bitmap bLeft = BitmapFactory.decodeResource(getResources(),R.drawable.end);
		Bitmap bRight = BitmapFactory.decodeResource(getResources(),R.drawable.i_go);
		item.setCalloutLeftImage(bLeft);
		item.setCalloutRightButtonImage(bRight);
		item.setCalloutTitle("낙성대");
		item.setCalloutSubTitle("subtitle");
		item.setCanShowCallout(true);
		
	
		item.setID("100");
		tmap.addMarkerItem("100", item);
		
//		tmap.removeMarkerItem("100"); //마커지우기
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, "줌인");
		menu.add(0, 2, 0, "줌아웃");
		menu.add(0, 3, 0, "마커 추가하기");
		menu.add(0, 4, 0, "원추가하기");
		menu.add(0, 5, 0, "POI검색");
		menu.add(0, 6, 0, "navi");
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
