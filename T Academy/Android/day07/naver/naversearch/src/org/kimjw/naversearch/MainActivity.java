package org.kimjw.naversearch;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    MyAdapter adapter;
    ListView list;
    ArrayList<MyItem> data = null;
    ProgressDialog dialog;
    boolean flag;
    String url = "http://openapi.naver.com/search?"+
    "key=ced66d15b36088c3c147f1c9d0ec3bc0&target=rank&query=nexearch";
    View.OnClickListener bHandler = new View.OnClickListener() {
		public void onClick(View v) {
			dialog = ProgressDialog.show(MainActivity.this,
					"실시간검색어 검색", "다운로드중...");
			new ReadThread().start();			
		}
	};
	
	class ReadThread extends Thread{
		HttpGet get = null;
		HttpClient httpClient = null;
		HttpResponse response;
		HttpEntity entity;
		
		public void run(){
			try{
				httpClient = NetManager.getHttpClient();
				get = NetManager.getGet(url);
				response = httpClient.execute(get);
				int state = response.getStatusLine().getStatusCode();
				if(state == 200){	
					Log.v(TAG, "success");
					entity = response.getEntity();
					data.clear();
					data = MyNaverSearchParser.parser(entity);
					adapter.setData(data);
					handler.sendEmptyMessage(0);
					flag = true;
				}else{
					flag = false;
				}
			}catch(Exception e){
				flag = false;
				Log.v(TAG, "로딩 오류 :" + e);
			}
		}

	}
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			dialog.dismiss();
			adapter.notifyDataSetChanged();
		}		
	};
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        list = (ListView)findViewById(R.id.searchlist);
        data = new ArrayList<MyItem>();
        MyItem item = new MyItem();
        item.setK("aaa");
        item.setS("+");
        item.setV("20");
        data.add(item);
        adapter = new MyAdapter(this, R.layout.myitem, data);        
        list.setAdapter(adapter);
        findViewById(R.id.button1).setOnClickListener(bHandler);
    }
}