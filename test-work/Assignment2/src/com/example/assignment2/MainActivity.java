package com.example.assignment2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	EditText etID, etPw;
	ProgressDialog pDialog = null;
	Activity activity;
	class LoginThread extends Thread
	{
		public void run()
		{
			String url = "http://192.168.201.169/webData/member";
			String id = etID.getText().toString().trim();
			String pw = etPw.getText().toString().trim();
			String action = "login";
			
			Log.v(TAG, "URL: " +url);
			
			HttpClient client = null;
			HttpPost request = null;
			HttpResponse response = null;
			
			BufferedReader br = null;
			StringBuilder sb = null;
			Message msg = handler.obtainMessage();
			int code = 0;
			
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("id", id)); //객체생성
			params.add(new BasicNameValuePair("pw", pw));
			params.add(new BasicNameValuePair("action", action));
			
			UrlEncodedFormEntity entity = null;
			
			try {
				entity = new UrlEncodedFormEntity(params);
				client = NetManager.getHttpClient();
				request = NetManager.getPost(url);
				request.setEntity(entity); //post 인경우
				response = client.execute(request);
				code = response.getStatusLine().getStatusCode();
				switch(code)
				{
				case 200:
					br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
					sb = new StringBuilder();
				
					String temp = "";
					String res = "fail";
					while((temp = br.readLine()) != null)
					{
						sb.append(temp).append("\n");
					}
					Log.v(TAG, sb.toString());
					
					if(sb.toString().equals(res))
					{
						msg.what = 555;
						msg.obj = "Failed";
					}		
					else
					{
						msg.what = 999; //정상적
						msg.obj = "success";
						
					}
										
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
				if(br!=null)
				{
					try {
						br.close();
					} catch (Exception e2) {
						
					}
					
				}
			}
			
			handler.sendMessage(msg);
		}
	}
	
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
			case 777:
				Intent intent = new Intent(MainActivity.this, UserActivity.class);
				startActivity(intent);
				break;
			case 888:
//				Toast.makeText(MainActivity.this, msg.arg1, Toast.LENGTH_SHORT).show();
				break;
			case 555:
//				Toast.makeText(MainActivity.this, msg.obj.toString(), 
//						Toast.LENGTH_SHORT).show();
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.btnlogin:
				pDialog = ProgressDialog.show(MainActivity.this, "", "로그인중...");
				new LoginThread().start();
				break;
			case R.id.btnsignin:
				Intent intent = new Intent(MainActivity.this, MemActivity.class);
				startActivity(intent);
				break;
			}
			
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etID = (EditText)findViewById(R.id.etID);
		etPw = (EditText)findViewById(R.id.etPw);
		findViewById(R.id.btnlogin).setOnClickListener(bHandler);
		findViewById(R.id.btnsignin).setOnClickListener(bHandler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
