package com.example.naverproject;

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
	ArrayList<Item> data;
	Item item;
	MyAdapter adapter = null;
	
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			pDialog = ProgressDialog.show(MainActivity.this, "", "downloading...");
			new JobThread().start();
			
		}
	};
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg) 
		{	
			if(pDialog != null)
			{
				pDialog.cancel();
			}
			switch(msg.what)
			{
			case 999:
				Log.v(TAG, "code error: " + msg.arg1);
				break;
			case 888:
				Log.v(TAG, "error e: " + msg.obj);
			case 777:
				adapter.notifyDataSetChanged();
				Log.v(TAG, "adapter success");
				break;
			}
		}
		
	};
	
	
	class JobThread extends Thread
	{
		public void run()
		{
			HttpURLConnection http = null;
			URL url = null;
			Message msg = handler.obtainMessage();
//			BufferedReader br = null;
			int code = 0;
			try {
				url = new URL("http://openapi.naver.com/search?key=ced66d15b36088c3c147f1c9d0ec3bc0&query=%ED%99%8D%EA%B8%B8%EB%8F%99&display=10&start=1&target=movie");
//				url = new URL("http://openapi.naver.com/search?key=ced66d15b36088c3c147f1c9d0ec3bc0&target=rank&query=nexearch");
				
				http = (HttpURLConnection)url.openConnection();
				http.setRequestMethod("GET");
				http.setConnectTimeout(10000);
				code = http.getResponseCode();
				switch(code)
				{
				case 200:
//					String data = MyUtil.getData(http.getInputStream());
					Channel channel = MyParser1.parse(http.getInputStream());
					//출력
					Log.v(TAG, "channel title: " +channel.getTitle());
					for(Mitem item : channel.getItemList())
					{
						Log.v(TAG, item.getTitle() + " " + item.getActor());
					}
//					MyParser.parse(http.getInputStream(), data);
					Log.v(TAG, "data: " + data);
					msg.what = 777;
					msg.obj = data;
//					String temp = "";
//					StringBuilder sb = new StringBuilder();
//					br = new BufferedReader(new InputStreamReader(http.getInputStream()));
//					while((temp = br.readLine()) != null)
//					{
//						sb.append(temp).append("\n");
//					}
					break;
				default:
					msg.what = 999; //code error처리
					msg.arg1 = code;
					break;
				}
				
			} catch (Exception e) {
				msg.what = 888; //Exception 처리
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
		
		list = (ListView)findViewById(R.id.listView1);
		findViewById(R.id.button1).setOnClickListener(bHandler);
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
