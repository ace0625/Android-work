package com.example.assignment2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;

import android.R.plurals;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class UserActivity extends Activity {
	private static final String TAG = "MainActivity";
	TextView account;
	EditText etsearch;
	ListView list;
	pList plist;
	ProgressDialog pDialog = null;
	RadioButton rtotal, rutil, rgame;
	ArrayList<Item> sdata;
	Item item;
	MyAdapter adapter = null;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.btnsearch:
				pDialog = ProgressDialog.show(UserActivity.this, "", "Searching...");
				new SearchThread().start();
				break;
			}
			
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
	class SearchThread extends Thread
	{
		public void run()
		{
			HttpURLConnection http = null;
			URL url = null;
			Message msg = handler.obtainMessage();
			BufferedReader br = null;
			int code = 0;
			try {
//				url = new URL("http://192.168.201.169/webData/product?");
				String serv = "http://192.168.201.169/webData/product?key=";
				String key = etsearch.getText().toString().trim();
				String check = "";
				if(rtotal.isChecked())
				{
					check = "0";
				}
				else if(rutil.isChecked())
				{
					check = "2";
				}
				else if(rgame.isChecked())
				{
					check = "1";
				}
				else if("".equals(key))
				{
					key = "empty";
				}
				serv += key;
				serv += "&category=";
				serv += check;
				Log.v(TAG, "url : " +serv);
				url = new URL(serv);
				http = (HttpURLConnection)url.openConnection();
				http.setRequestMethod("GET");
				http.setConnectTimeout(10000);
				code = http.getResponseCode();
				switch(code)
				{
				case 200:
					
					plist = MyParser.parse(http.getInputStream());
					adapter.
					Log.v(TAG, "title: " +plist.getItemList());
//					String temp = "";
//					StringBuilder sb = new StringBuilder();
//					br = new BufferedReader(new InputStreamReader(http.getInputStream()));
//					while((temp = br.readLine()) != null)
//					{
//						sb.append(temp).append("\n");
//					}
//					
					for (Item item : plist.getItemList())
					{
						Log.v(TAG, item.getTitle().toString() + " " +item.getImage().toString());
					}
					Log.v(TAG, "data: " + plist);
					msg.what = 777;
					msg.obj = plist;
					
//					byte[] brr = IOUtils.toByteArray()

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
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.user);
	   
	    list = (ListView)findViewById(R.id.listView1); 
	    account = (TextView)findViewById(R.id.txAccount);
	    etsearch = (EditText)findViewById(R.id.etSearch);
	    findViewById(R.id.btnsearch).setOnClickListener(bHandler);
	    rtotal = (RadioButton)findViewById(R.id.Rtotal);
	    rutil = (RadioButton)findViewById(R.id.Rutil); 
	    rgame = (RadioButton)findViewById(R.id.Rgame);
	    plist = new pList();
	    adapter = new MyAdapter(UserActivity.this, R.layout.item, plist);
	    list.setAdapter(adapter);
	    
	}

}
