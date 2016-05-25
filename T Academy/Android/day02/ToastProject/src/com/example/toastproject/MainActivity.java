package com.example.toastproject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	View.OnClickListener bHandler = new View.OnClickListener() 
	{
		public void onClick(View v) 
		{
			switch (v.getId())
			{
				case R.id.btn1:
					//Toast.makeText(MainActivity.this, "pressed", Toast.LENGTH_SHORT).show();
					//showToast("Pressed");
					Mytoast.showToast(MainActivity.this, "PPressed");
					break;
				case R.id.btn2:
					showToast1("사용자 정의 토스트입니다");
					break;
			}
		}
	};
	void showToast1(String text)
	{
		View view = View.inflate(this, R.layout.mytoast, null);
		TextView tv = (TextView)view.findViewById(R.id.textView1);
		tv.setText(text);
		Toast toast = new Toast(this);
		toast.setView(view);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.TOP|Gravity.LEFT, 20, 400);
		toast.show();
	}
	
	void showToast(String text)
	{
		Toast.makeText(MainActivity.this, "pressed", Toast.LENGTH_SHORT).show();
	}
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btn1).setOnClickListener(bHandler);
		findViewById(R.id.btn2).setOnClickListener(bHandler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
