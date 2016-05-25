package org.kimjw.navermovie;

import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class SubListActivity extends Activity {
	private static final String TAG = "MainActivity";
	String key;
	String genre;
	
	MyAdapter adapter;  
    ListView list;
    ArrayList<MyItem> data = null;
    ProgressDialog dialog;
    boolean flag;
    //http://openapi.naver.com/search?key=test&query=%EB%B2%A4%ED%97%88&display=10&start=1&target=movie
    //URLEncoder.encode("홍길동전", "UTF-8")
    String url1 = "http://openapi.naver.com/search?key=ced66d15b36088c3c147f1c9d0ec3bc0&query=";
    String url2 = "";
    String url3 = "&display=10&start=1&target=movie";
    String url;
    
    Channel channel = new Channel();

	View.OnClickListener bHandler = new View.OnClickListener() {
		public void onClick(View v) {
			finish();			
		}
	};
	class ReadThread extends Thread{
		public void run(){
			HttpGet get = null;
			HttpClient client = null;
			HttpResponse response = null;
			HttpEntity entity = null;
			
			try{
				if(!genre.equals("0")){
					url2 = "&genre="+genre;
				}
				url = url1 + URLEncoder.encode(key, "UTF-8") + url2 + url3;
				client = NetManager.getHttpClient();
				Log.v(TAG, "url : " + url);
				get = NetManager.getGet(url);
				response = client.execute(get);
				int code = response.getStatusLine().getStatusCode();
				switch(code){
				case 200 :
					channel = MyMovieParser.parser(response.getEntity());
					adapter.setData(channel);
					break;
				}
			}catch(Exception e){
				Log.v(TAG, "로딩 오류 : " + e);
			}
			handler.sendEmptyMessage(0);
		}
	}
	
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			dialog.dismiss();
			adapter.notifyDataSetChanged();
		}		
	};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sublist);
		findViewById(R.id.btnclose).setOnClickListener(bHandler);
		Intent intent = getIntent();
		key = intent.getStringExtra("key");
		genre = intent.getStringExtra("genre");
		Log.v(TAG, "genre : " + genre);
		list = (ListView)findViewById(R.id.mylist);
		adapter = new MyAdapter(this, R.layout.myitem, channel );
		list.setAdapter(adapter);
		
		dialog = ProgressDialog.show(SubListActivity.this, "영화 검색", "다운로드중...");
		new ReadThread().start();
	}
}
