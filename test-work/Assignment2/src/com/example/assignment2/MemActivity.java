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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MemActivity extends Activity {

	private static final String TAG = "MainActivity";
	Activity activity = null;
	EditText metName, metId, metPw, metTel, metAddress, metComment;
	ProgressDialog pDialog = null;
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
				Intent intent = new Intent(MemActivity.this, UserActivity.class);
				startActivity(intent);
				break;
			case 444:
				Toast.makeText(MemActivity.this, "해당아이디 존재", Toast.LENGTH_SHORT).show();
				metId.setText("");
				break;
			case 222:
				Toast.makeText(MemActivity.this, "사용가능",Toast.LENGTH_SHORT).show();
				break;
			case 888:
				break;
			}
			super.handleMessage(msg);
		}
	};
	
	class SignThread extends Thread
	{
		public void run()
		{
			String url = "http://192.168.201.169/webData/member";
			String name = metName.getText().toString().trim();
			String id = metId.getText().toString().trim();
			String pw = metPw.getText().toString().trim();
			String tel = metTel.getText().toString().trim();
			String address = metAddress.getText().toString().trim();
			String comment = metComment.getText().toString().trim();	
			String action = "insert";
			
			Log.v(TAG, "URL: " +url);
			
			HttpClient client = null;
			HttpPost request = null;
			HttpResponse response = null;
			
			BufferedReader br = null;
			StringBuilder sb = null;
			final Message msg = handler.obtainMessage();
			int code = 0;
			
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("name", name));
			params.add(new BasicNameValuePair("id", id)); //객체생성
			params.add(new BasicNameValuePair("pw", pw));
			params.add(new BasicNameValuePair("tel", tel));
			params.add(new BasicNameValuePair("adrress", address));
			params.add(new BasicNameValuePair("comment", comment));
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
					
					if(res.equals(sb.toString().trim()))
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
	class IdcheckThread extends Thread
	{
		public void run()
		{
			String url = "http://192.168.201.169/webData/member";
			String id = metId.getText().toString().trim();
			String action = "check";
			
			Log.v(TAG, "URL: " +url);
			
			HttpClient client = null;
			HttpPost request = null;
			HttpResponse response = null;
			
			BufferedReader br = null;
			StringBuilder sb = null;
			Message msg = handler.obtainMessage();
			int code = 0;
			
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("id", id)); 
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
					String tx = "fail";
					while((temp = br.readLine()) != null)
					{
						sb.append(temp).append("\n");
					}
					Log.v(TAG, sb.toString());
					
					if(tx.equals(sb.toString().trim()))
					{
						msg.what = 444; 
						msg.obj = "해당아이디 존재";
						
					}		
					else
					{
						msg.what = 222;
						msg.obj = "사용가능";
						
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
			Log.v(TAG,"msgwhat: "+ msg.what);
			handler.sendMessage(msg);
		}		
	}
	
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.btnsearch:
				pDialog = ProgressDialog.show(MemActivity.this, "", "체크중...");
				new IdcheckThread().start();
				break;
			case R.id.mbtnsign:
				pDialog = ProgressDialog.show(MemActivity.this, "", "가입중...");
				new SignThread().start();
				break;
			}
			
		}
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.member);
	
	    metName = (EditText)findViewById(R.id.metName);
	    metId = (EditText)findViewById(R.id.metID);
	    metPw = (EditText)findViewById(R.id.metPw);
	    metTel = (EditText)findViewById(R.id.metTel);
	    metAddress = (EditText)findViewById(R.id.metAddress);
	    metComment = (EditText)findViewById(R.id.metComment);
	    
	    findViewById(R.id.btnsearch).setOnClickListener(bHandler);
	    findViewById(R.id.mbtnsign).setOnClickListener(bHandler);
	}

}
