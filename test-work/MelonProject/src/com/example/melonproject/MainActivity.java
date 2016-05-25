package com.example.melonproject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	ProgressDialog pDialog = null;
	ListView list;
	Melon data;
	ArrayList<Song> Sdata;
	MyAdapter adapter = null;
	View.OnClickListener bHandler = new View.OnClickListener() {
	
		@Override
		public void onClick(View v) {
			new JobTask().execute("http://apis.skplanetx.com/melon/charts/realtime?count=10&page=1&version=1&appKey=461cb22d-5082-377b-869d-f6c3d2b0156c&format=json");
			
		}
	};
	
	class JobTask extends AsyncTask<String, Void, Melon>
	{

		@Override
		protected Melon doInBackground(String... params) {
			Melon data = null;
			String url = params[0];
			
			HttpClient client = null;
			HttpGet request = null; //go catch
			HttpResponse response = null;
			BufferedReader br = null;
			int code = 0;
			try {
				client = NetManager.getHttpClient();
				request = NetManager.getGet(url);
				response = client.execute(request);
				code = response.getStatusLine().getStatusCode();
				switch(code)
				{
				case 200:
					br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
					StringBuilder sb = new StringBuilder();
					String sTemp = "";
					while((sTemp = br.readLine()) != null)
					{
						Log.v(TAG, "aa");
						sb.append(sTemp).append("\n");
					}
					data = MyJsonParser.parse(sb.toString());
					adapter.setData(data);
					
//					data = sb.toString();
					
					Log.v(TAG, "=======================================================================================");
					Log.v(TAG, "melon : " +data);
					Log.v(TAG,"===============================================================================================");
					
					for(Song song: data.getSongs())
					{
						Log.v(TAG, song.toString());
					}
					Log.v(TAG, "=============================================================================================");
					break;
				default:
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				
			}
			
			return data;
		}

		@Override
		protected void onPostExecute(Melon result) {
			if(pDialog != null)
			{
				pDialog.cancel();
			}
			adapter.notifyDataSetChanged();
			Log.v(TAG, "result : " + result);
			
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			pDialog = ProgressDialog.show(MainActivity.this, "", "Downloading...");
			super.onPreExecute();
		}
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		list = (ListView)findViewById(R.id.listView1);
		findViewById(R.id.button1).setOnClickListener(bHandler);
		data = new Melon();
//		Sdata = new ArrayList<Song>();
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
