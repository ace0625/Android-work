package com.example.sensorproject;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	EditText et1, et2, et3;
	int cnt1, cnt2, cnt3;
	
	SensorManager manager = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et1 = (EditText)findViewById(R.id.editText1);
		et2 = (EditText)findViewById(R.id.editText2);
		et3 = (EditText)findViewById(R.id.editText3);
		
		manager = (SensorManager)getSystemService(SENSOR_SERVICE);
		List<Sensor> list = manager.getSensorList((Sensor.TYPE_ALL));
		
		for(Sensor s : list)
		{
			Log.v(TAG, s.getName() + "" + s.getVendor() + s.getPower());
		}
	}

	
	@Override
	protected void onPause() {
		manager.unregisterListener(listener);
		super.onPause();
	}
	@Override
	protected void onResume() {
		manager.registerListener(listener, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
		manager.registerListener(listener, manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_UI);
		manager.registerListener(listener, manager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_UI);
		super.onResume();
	}


	SensorEventListener listener = new SensorEventListener() {
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			switch(event.accuracy)
			{
			case SensorManager.SENSOR_STATUS_UNRELIABLE:
				Log.v(TAG, "이상한 값");
//				return;
			}
			switch(event.sensor.getType())
			{
			case Sensor.TYPE_LIGHT:
				cnt1++;
				et1.setText(cnt1 + "조도값: " + event.values[0]);
				Log.v(TAG, cnt1 + "조도값: " + event.values[0]);
				break;
			case Sensor.TYPE_GYROSCOPE:
				cnt2++;
				et2.setText(cnt2 + "X값: "+event.values[0]+  "Y값: " + event.values[1]);
				Log.v(TAG, cnt2 + "X값: "+event.values[0]+  "Y값: " + event.values[1]);
				break;
			case Sensor.TYPE_ACCELEROMETER:
				cnt3++;
				et3.setText(cnt3 + "X값: "+event.values[0]+  "Y값: " + event.values[1]);
				Log.v(TAG, cnt3 + "X값: "+event.values[0]+  "Y값: " + event.values[1] + "Z값: " +event.values[2]);
				break;
				
			}
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	};
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
