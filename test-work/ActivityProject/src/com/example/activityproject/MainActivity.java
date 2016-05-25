package com.example.activityproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final int PROM_EXIT = 999;
	private static final String TAG = "MainActivity";
	EditText et1, et2, et3, et4;
	View.OnClickListener BHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				//명시 Intent, 묵시적 Intent
				Intent intent = new Intent(MainActivity.this, Sub1Activity.class);
//				String str = et1.getText().toString();
//				intent.putExtra("data", str);
//				intent.putExtra("cnt", 120);
				
				MyData data = MyData.getInstance();
				data.setStr(et1.getText().toString());
				data.setCnt(120);
				
//				startActivity(intent);
				startActivityForResult(intent, 100);
				break;
			case R.id.button2:
				Intent intent1 = new Intent(MainActivity.this, Sub2Activity.class);
				String str1 = et2.getText().toString();
				intent1.putExtra("data", str1);
				intent1.putExtra("cnt", 300);
//				startActivity(intent1);
				startActivityForResult(intent1, 200);
				break;
			case R.id.button3:
				finish();
				break;
			}
		}
	};                                                                                                                                                           
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
//		Log.v(TAG, "request code : " + requestCode);
		switch(resultCode)
		{
		case RESULT_OK:
			switch(requestCode)
			{
			case 100:
				MyData a = MyData.getInstance();
				et3.setText(a.getStr());
//				et3.setText(data.getStringExtra("result"));
				
				break;
			case 200:
				break;
			}
			break;
		case PROM_EXIT:
			setResult(PROM_EXIT);
			finish();
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		et1 = (EditText)findViewById(R.id.editText1);
		et2 = (EditText)findViewById(R.id.editText2);
		et3 = (EditText)findViewById(R.id.editText3);
		et4 = (EditText)findViewById(R.id.editText4);
		
		findViewById(R.id.button1).setOnClickListener(BHandler);
		findViewById(R.id.button2).setOnClickListener(BHandler);
		findViewById(R.id.button3).setOnClickListener(BHandler);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
