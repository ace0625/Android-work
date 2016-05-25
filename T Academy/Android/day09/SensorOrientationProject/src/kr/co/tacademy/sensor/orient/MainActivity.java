package kr.co.tacademy.sensor.orient;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.sax.RootElement;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
    SensorManager manager = null;
    MyView myView = null;
      
    SensorEventListener sListener = new SensorEventListener() {
		
		public void onSensorChanged(SensorEvent event) {
			float[] values = event.values;
			switch (event.sensor.getType()) {
			case Sensor.TYPE_ORIENTATION :
				
				myView.setNewData(values);

				break;
			}
			
		}
		
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	};
    @Override
	protected void onPause() {
		manager.unregisterListener(sListener);
		super.onPause();
	}



	@Override
	protected void onResume() {
		manager.registerListener(sListener, manager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_UI);
		super.onResume();
	}

	

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        myView = (MyView)findViewById(R.id.myView1);
        
        
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }
}





