package com.example.naverrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	ProgressDialog pDialog = null;
	ListView list;
	ArrayList<Item> data = null;
	Item item;
	MyAdapter adapter = null;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				pDialog = ProgressDialog.show(MainActivity.this, "", "DOWNLADING...");
//				pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
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
			case 777:
				adapter.notifyDataSetChanged();
				Log.v(TAG, "adapter success");
				break;
			case 888:
				Log.v(TAG, "error e: " +msg.obj);
				break;
			case 999:
				Log.v(TAG, "code error: " + msg.arg1);
				break;
			}
		
		}
		
	};
	
	class JobThread extends Thread
	{
		public void run()
		{
			URL url = null;
			HttpURLConnection connection = null;
			int code = 0;
			Message msg = handler.obtainMessage();
			BufferedReader br = null;
			StringBuilder sb = null;
//			String str = "";
			try {
				url = new URL("http://openapi.naver.com/search?key=ced66d15b36088c3c147f1c9d0ec3bc0&target=rank&query=nexearch");
				connection = (HttpURLConnection)url.openConnection();
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				connection.setDoOutput(false);
				connection.setConnectTimeout(5000);
				code = connection.getResponseCode();
				
				Log.v(TAG, "code: " +code);
				switch(code)
				{
				case 200:
					MyParser.parse(connection.getInputStream(), data);
					String data = MyUtil.getData(connection.getInputStream());
					Log.v(TAG, "DATA : " + data);
					msg.what = 777;
					msg.obj = data;
//					sb = new StringBuilder();
//					br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//					
//					while((str = br.readLine()) != null)
//					{	
//						Log.v(TAG,"readline");
//						sb.append(str).append("\n");
//					}
//					
					break;
				default:
					msg.what = 999;
					msg.arg1 = code;
					break;
				}
			} catch (Exception e) {
				msg.what = 888;
				msg.obj = e;
			}finally{
				
			}
			handler.sendMessage(msg);
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button1).setOnClickListener(bHandler);
		list = (ListView)findViewById(R.id.listView1);
		data = new ArrayList<Item>();
		adapter = new MyAdapter(this, R.layout.item, data);
		list.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
