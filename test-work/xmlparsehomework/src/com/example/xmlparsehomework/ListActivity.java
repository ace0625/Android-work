package com.example.xmlparsehomework;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;



import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class ListActivity extends Activity {
private static final String TAG="MainActivity";
ListView list;
pList data;
ArrayList<item> Sdata;
MyAdapter adapter = null;
TextView name;
EditText search;
RadioButton total,game,util;
View.OnClickListener bHandler=new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.serch:
			new JobThread().start();
		break;
		}
	}
};
Handler handler = new Handler()
{
	@Override
	public void handleMessage(Message msg) 
	{	
		
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
		URL url1 = null;
		Message msg = handler.obtainMessage();
//		BufferedReader br = null;
		int code = 0;
		try {
			
			//url = new URL("http://192.168.201.169/webData/product?");
//			url = new URL("http://openapi.naver.com/search?key=ced66d15b36088c3c147f1c9d0ec3bc0&target=rank&query=nexearch");
			String url="http://192.168.201.169/webData/product?key=";//웹서버에서 리턴되는 문서에 \까지 같이 오는게 있다. //get방식은 주소 만들어서 던지면 끝파라미터는 ?로
			String keyword=search.getText().toString().trim();
			String select="";
			if(total.isChecked()){
				select="0";
			}else if(game.isChecked()){
				select="1";
			}else if(util.isChecked()){
				select="2";
			}
			if("".equals(keyword)){
				keyword="empty";
			}
			url+=keyword;
			url+="&category=";
			url+=select;
			Log.v(TAG,"URL"+url);
			url1 = new URL(url);
//			
			http = (HttpURLConnection)url1.openConnection();
			http.setRequestMethod("GET");
			http.setConnectTimeout(10000);
			code = http.getResponseCode();
			switch(code)
			{
			case 200:
//				//					String data = MyUtil.getData(http.getInputStream());
				data = MyParser1.parse(http.getInputStream());
				adapter.setData(data);
				//출력
				Log.v(TAG, "channel title: " +data.getItemList());
				for(item item : data.getItemList())
				{
					Log.v(TAG, item.getTitle() + " " + item.getCategory()+ " " + item.getCount()+ " " + item.getPrice()+ " " + item.getImage());
				}
//				MyParser.parse(http.getInputStream(), data);
				Log.v(TAG, "data: " + data);
				msg.what = 777;
				msg.obj = data;
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

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    // TODO Auto-generated method stub
	    setContentView(R.layout.list);
	    findViewById(R.id.serch).setOnClickListener(bHandler);
	    name=(TextView)findViewById(R.id.name);
	    search=(EditText)findViewById(R.id.id);
	    total=(RadioButton)findViewById(R.id.total);
	    game=(RadioButton)findViewById(R.id.game);
	    util=(RadioButton)findViewById(R.id.util);
	    list = (ListView)findViewById(R.id.listView1);
	
		data = new pList();
//		Sdata = new ArrayList<Song>();
		adapter = new MyAdapter(this, R.layout.listview, data);
		list.setAdapter(adapter);
	
}
}
	
	
	
	
	

