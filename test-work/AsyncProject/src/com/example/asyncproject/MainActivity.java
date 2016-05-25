package com.example.asyncproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	EditText et;
	ProgressBar pBar;
	ProgressDialog pDialog = null;
	View.OnClickListener bHandler= new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				
				new JobTask().execute(20, 2, 80); //시작값, 증가값, 마지막값
				break;
			}
		}
	};
	
	class JobTask extends AsyncTask<Integer, Integer, String>
	{

		@Override
		protected String doInBackground(Integer... params) {
			int start = params[0];
			int step = params[1];
			int last = params[2];
			int num = 0;
			while(num <= last)
			{
				num += step;
				if (num % 10 == 0)
				{
					publishProgress(num);
				}
				SystemClock.sleep(300);
			}
			return "작업완료";
		}
		

		@Override
		protected void onPostExecute(String result) {
			pDialog.cancel();
			Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			pDialog = ProgressDialog.show(MainActivity.this, "", "작업중...");
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			et.setText(values[0] + "");
			pBar.setProgress(values[0]);
			super.onProgressUpdate(values);
		}
		
	}
	void aa()
	{
		bb();
		bb(1);
		bb(1,2);
		bb(1,2,3);
	}
	void bb(int...a)
	{
		//a 는 배열
//		int num = a[0];
		int tot = 0;
		for (int i=0; i<a.length; i++)
		{
			tot += a[i];
		}
	}
	void bb(int a)
	{
		
	}
	void bb(int a, int b)
	{
		
	}
	void bb(int a ,int b, int c)
	{
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et = (EditText)findViewById(R.id.editText1);
		pBar = (ProgressBar)findViewById(R.id.progressBar1);
		
		findViewById(R.id.button1).setOnClickListener(bHandler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
