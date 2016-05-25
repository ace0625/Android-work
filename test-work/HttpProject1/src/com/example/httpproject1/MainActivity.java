package com.example.httpproject1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BufferedHeader;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	EditText etUrl = null;
	TextView result = null;
	ProgressDialog pDialog = null;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				pDialog = ProgressDialog.show(MainActivity.this, "", "");
				new JobThread().start();
				break;
			}
		}
	};
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg) {
			if(pDialog != null)
			{
				pDialog.cancel();
			}
			switch(msg.what)
			{
			case 999:
				result.setText(msg.obj.toString());
				break;
			case 888:
				Toast.makeText(MainActivity.this, msg.arg1 + "코드에러", Toast.LENGTH_SHORT).show();
				break;
			case 777:
				Toast.makeText(MainActivity.this, msg.obj + "코드에러", Toast.LENGTH_SHORT).show();
				break;
			}
		}
		
	};
	class JobThread extends Thread
	{
		public void run()
		{
			HttpClient client = null;
			HttpGet request = null;
			HttpResponse response = null;
			
			int code = 0;
			BufferedReader br = null;
			String str = "";
			StringBuilder sb = null;
			Message msg = null;
			try {
				msg = handler.obtainMessage();
				client = NetManager.getHttpClient();
//				client = new DefaultHttpClient();
				
				request = NetManager.getGet(etUrl.getText().toString());
//				request = new HttpGet(etUrl.getText().toString());
				
				response = client.execute(request); //접속!
				
				code = response.getStatusLine().getStatusCode(); //코드 넘겨줌
				Log.v(TAG, "code : "+code);
				switch(code)
				{
				case 200:
					br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
					sb = new StringBuilder();
					while((str = br.readLine()) != null)
					{
						sb.append(str).append("\n");
					}
					msg.what = 999;
					msg.obj = sb;
					break;
				default:
					msg.what = 888;
					msg.arg1 = code;
					break;
				}
			} catch (Exception e) {
				msg.what = 777;
				msg.obj = e;
			}finally{
				if(br != null)
				{
					try {
						br.close();
					} catch (IOException e) {
					
					}
				}	
			}
			handler.sendMessage(msg);
		}
	}
	
	class JobThread1 extends Thread
	{
		public void run()
		{
			URL url = null;
			HttpURLConnection connection = null;
			int code = 0;
			BufferedReader br = null;
			String str = "";
			StringBuilder sb = null;
			Message msg = null;
			try {
				msg = handler.obtainMessage();
				url = new URL(etUrl.getText().toString());
				connection = (HttpURLConnection)url.openConnection();
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				connection.setDoOutput(false);
				connection.setConnectTimeout(10000);
				code = connection.getResponseCode();
				Log.v(TAG, "code : "+code);
				switch(code)
				{
				case 200:
					br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					sb = new StringBuilder();
					while((str = br.readLine()) != null)
					{
						sb.append(str).append("\n");
					}
					msg.what = 999;
					msg.obj = sb;
					break;
				default:
					msg.what = 888;
					msg.arg1 = code;
					break;
				}
			} catch (Exception e) {
				msg.what = 777;
				msg.obj = e;
			}finally{
				if(br != null)
				{
					try {
						br.close();
					} catch (IOException e) {
					
					}
				}
				if(connection != null)
				{
					connection.disconnect();
				}
				
			}
			handler.sendMessage(msg);
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.button1).setOnClickListener(bHandler);
		etUrl = (EditText)findViewById(R.id.editText1);
		result = (TextView)findViewById(R.id.result);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
