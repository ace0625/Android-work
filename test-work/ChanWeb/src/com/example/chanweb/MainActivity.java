package com.example.chanweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity"; 
	EditText et;
	TextView tv;
	
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		String url = et.getText().toString();
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				new MyTask().execute(url);
				break;
			}
				
			
			
		}
	};
	class MyTask extends AsyncTask<String, Integer, String>
	{

		@Override
		protected String doInBackground(String... params) {
			if(params != null && params.length > 0)
			{
				
				URL url = null;
				HttpURLConnection connection = null;
				int code = 0;
				String str = "";
				BufferedReader br = null;
				StringBuilder sb = null;
				String urlString = params[0];
				try {
					url = new URL(urlString);
					connection = (HttpURLConnection)url.openConnection();
					connection.setRequestMethod("GET");
					connection.setDoInput(true);
					connection.setDoOutput(false);
					connection.setConnectTimeout(10000);
					code = connection.getResponseCode();
					Log.v(TAG, "code : " + code);
					switch(code)
					{
					case 200:
						br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						sb = new StringBuilder();
						while((str = br.readLine()) != null)
						{
							sb.append(str).append("\n");
							Log.v(TAG, "read line");
						}
						String body = connection.getResponseMessage();
						return body;
					default:
						break;
						
					}
					
				} catch (Exception e) {
					Log.v(TAG, "read error " +e);
				}finally{
					if(br != null)
					{
						try {
							br.close();
						} catch (IOException e) {
							Log.v(TAG, "IO error" +e);
						}
					}
					if(connection != null)
					{
						connection.disconnect();
					}
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if(result != null)
			{
				tv.setText(result);
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText)findViewById(R.id.editText1);
		tv = (TextView)findViewById(R.id.textView1);
		findViewById(R.id.button1).setOnClickListener(bHandler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
