package com.example.flag;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SubActivity extends Activity {
	private static final String TAG = "MainActivity";
	EditText et;
	View.OnClickListener bHandler = new View.OnClickListener() {
	
	int cnt = 0;
		@Override
		public void onClick(View v) {
			switch (v.getId())
			{
			case R.id.button1:
				Intent intent = new Intent(SubActivity.this, SubActivity.class);
				intent.putExtra("cnt", cnt);
//				intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //다 지워버리고 새로운창 생성
				startActivity(intent);
				break;
			case R.id.button2:
				cnt++;
				et.setText("cnt : " +cnt);
				break;
			case R.id.button3:
//				finish();
				Intent intent1 = new Intent(SubActivity.this, MainActivity.class);
				startActivity(intent1);
			}
		}
	};
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.sub);
	    
	    findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		findViewById(R.id.button3).setOnClickListener(bHandler);
		
		et = (EditText)findViewById(R.id.editText1);
		
		Intent intent = getIntent();
		String data = intent.getStringExtra("data");
		
		MyData item = (MyData)intent.getSerializableExtra("item");
		
		Log.v(TAG, "item.name : " + item.getName());
		Log.v(TAG, "item.age : " + item.getAge());
		
		Log.v(TAG, "SubActivity create data: " + data);
	}
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}

}
