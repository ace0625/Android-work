package com.example.compassproject;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;

public class MainActivity extends Activity {
	SensorManager manager = null;
	CompassView mView;
	float azimuth, pitch, roll;
	
	@SuppressLint("ServiceCast")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		manager = (SensorManager)getSystemService(Context.SEARCH_SERVICE);
		mView = (CompassView)findViewById(R.id.compassView1);
	}

	SensorEventListener listener = new SensorEventListener() {
		
		@Override
		public void onSensorChanged(SensorEvent event) 
		{
			float[] v = event.values;
			switch (event.sensor.getType()) 
			{
			case Sensor.TYPE_ORIENTATION:
				if (azimuth != v[0] || pitch != v[1] || roll != v[2]) 
				{		
					azimuth = v[0];
					pitch = v[1];
					roll = v[2];
					
					
					mView.setValue(azimuth, pitch, roll);
				}
				break;
			}
			
			
		}
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) 
		{
			// TODO Auto-generated method stub
			
		}
	};
	public float getAzimuth()
	{
		return azimuth;
	}
	public float getPitch()
	{
		return pitch;
	}
	
	public float getRoll()
	{
		return roll;
	}
	
	
	@Override
	protected void onPause() {
		
		super.onPause();
		manager.unregisterListener(listener);
		
	}


	@Override
	protected void onResume() {
		super.onResume();
		manager.registerListener((SensorEventListener)listener, manager.getDefaultSensor(Sensor.TYPE_ORIENTATION), 
				SensorManager.SENSOR_DELAY_UI);

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
