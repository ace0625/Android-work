package com.example.xmlparsehomework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import com.example.xmlparsehomework.MainActivity.LoginThread;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends Activity {
	private static final String TAG="MainActivity";
	//ProgressDialog pDialog=null;
	View.OnClickListener bHandler =new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
		switch(v.getId()){
		case R.id.check:
			new IdCheckThread().start();
			//pDialog= ProgressDialog.show(MainActivity.this, "", "로그인중");
			break;
		case R.id.insert:
			new InsertThread().start();
			
			Intent intent2= new Intent(InsertActivity.this,ListActivity.class);
			startActivity(intent2);
			break;
		}
			
		}
	};

	EditText etname,etid,etpw,etphone,etaddress,etcomment;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.insert);
			findViewById(R.id.check).setOnClickListener(bHandler);
			findViewById(R.id.insert).setOnClickListener(bHandler);
			etname=(EditText)findViewById(R.id.etname);
			etid=(EditText)findViewById(R.id.etid);
			etpw=(EditText)findViewById(R.id.etpw);
			etphone=(EditText)findViewById(R.id.etphone);
			
			etaddress=(EditText)findViewById(R.id.etaddress);
			etcomment=(EditText)findViewById(R.id.etcomment);
			
		}
		class IdCheckThread extends Thread{
			public void run(){
				String url="http://192.168.201.169/webData/member";//웹서버에서 리턴되는 문서에 \까지 같이 오는게 있다. //get방식은 주소 만들어서 던지면 끝파라미터는 ?로
				String id=etid.getText().toString().trim();//trim 은 좌우의 공백을 배제 하는것.
				
				String action="check";
				HttpClient client =null;
				HttpPost request =null;
				HttpResponse response =null;
				int code=0;
				BufferedReader br=null;
				Message msg=handler.obtainMessage();
				
				List<BasicNameValuePair> params =new ArrayList<BasicNameValuePair>();
				params.add(new BasicNameValuePair("id", id));//파라미터, 전송하기위하 실제데이터
				params.add(new BasicNameValuePair("action", action));
				
				UrlEncodedFormEntity entity=null;
				try{
					 entity=new UrlEncodedFormEntity(params);
					client =NetManager.getHttpClient();
					request =NetManager.getPost(url);
					request.setEntity(entity);//포스트에 해당
					response =client.execute(request);
					code=response.getStatusLine().getStatusCode();
					switch(code){
						case 200:
							br=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));//뽑아낸값 처리하는 것. 
							StringBuilder b =new StringBuilder();
							String temp="";
							while((temp=br.readLine())!=null){
								b.append(temp).append("\n");
													}
							Log.v(TAG,"check"+b.toString());//데이터를 짤라내야한다.
					
							String c="fail";
							Log.v(TAG,"Bool TEST"+c.equals(b.toString().trim()));
							//MemberResult obj=MyLoginParser.parse(sb.toString());
							if(c.equals(b.toString().trim())){
								msg.what=333;
							}
							else{
								msg.what=666;
							}
								break;
								default:
									msg.what=888;
									msg.arg1=code;
												
							}
					
				}catch(Exception e){
					msg.what=777;
					msg.obj=e;
				}finally{
					if(br!=null){
						try{
							br.close();
						}catch(IOException e){
							Log.v(TAG, ""+e);
						}
					
						}
					}
				
				handler.sendMessage(msg);
			}
		}

		class InsertThread extends Thread{
			public void run(){
				String url="http://192.168.201.169/webData/member";//웹서버에서 리턴되는 문서에 \까지 같이 오는게 있다. //get방식은 주소 만들어서 던지면 끝파라미터는 ?로
				String id=etid.getText().toString().trim();//trim 은 좌우의 공백을 배제 하는것.
				String pw=etpw.getText().toString().trim();//trim 은 좌우의 공백을 배제 하는것.
				String name=etname.getText().toString().trim();//trim 은 좌우의 공백을 배제 하는것.
				String tel=etphone.getText().toString().trim();//trim 은 좌우의 공백을 배제 하는것.
				String address=etaddress.getText().toString().trim();//trim 은 좌우의 공백을 배제 하는것.
				String comment=etcomment.getText().toString().trim();//trim 은 좌우의 공백을 배제 하는것.
				
				String action="insert";
				HttpClient client =null;
				HttpPost request =null;
				HttpResponse response =null;
				int code=0;
				BufferedReader br=null;
				Message msg=handler.obtainMessage();
				
				List<BasicNameValuePair> params =new ArrayList<BasicNameValuePair>();
				params.add(new BasicNameValuePair("id", id));//파라미터, 전송하기위하 실제데이터
				params.add(new BasicNameValuePair("pw", pw));//파라미터, 전송하기위하 실제데이터
				params.add(new BasicNameValuePair("name", name));//파라미터, 전송하기위하 실제데이터
				params.add(new BasicNameValuePair("tel", tel));//파라미터, 전송하기위하 실제데이터
				params.add(new BasicNameValuePair("address", address));//파라미터, 전송하기위하 실제데이터
				params.add(new BasicNameValuePair("comment", comment));//파라미터, 전송하기위하 실제데이터
				
				params.add(new BasicNameValuePair("action", action));
				
				UrlEncodedFormEntity entity=null;
				
				try{
					 entity=new UrlEncodedFormEntity(params);
					client =NetManager.getHttpClient();
					request =NetManager.getPost(url);
					request.setEntity(entity);//포스트에 해당
					response =client.execute(request);
					code=response.getStatusLine().getStatusCode();
					switch(code){
						case 200:
							br=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));//뽑아낸값 처리하는 것. 
							StringBuilder sb =new StringBuilder();
							String temp="";
							while((temp=br.readLine())!=null){
								sb.append(temp).append("\n");
													}
							Log.v(TAG,"insert"+sb.toString());//데이터를 짤라내야한다.
					
							String a="fail";
							//MemberResult obj=MyLoginParser.parse(sb.toString());
							
							if(a.equals(sb.toString())){
								msg.what=555;
								msg.obj="실패";
							}else{
								msg.what=999;
							
							/*	*/
								}
								
								break;
								default:
									msg.what=888;
									msg.arg1=code;
												
							}
					
				}catch(Exception e){
					msg.what=777;
					msg.obj=e;
				}finally{
					if(br!=null){
						try{
							br.close();
						}catch(IOException e){
							Log.v(TAG, ""+e);
						}
					
						}
					}
				
				handler.sendMessage(msg);
			}
		}
		Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg){
			/*	if(pDialog!=null){
					pDialog.cancel();
				}*/
				switch(msg.what){
				case 666:
					Toast.makeText(InsertActivity.this, "사용해도 좋아요!", Toast.LENGTH_SHORT).show();
					break;
				case 999: case 777:
					//etResult.setText(msg.obj.toString());
					Intent intent1= new Intent(InsertActivity.this,ListActivity.class);
					startActivity(intent1);
					break;
				case 888:
					//etResult.setText("coode:"+msg.arg1);
					break;
				case 333:
					Toast.makeText(InsertActivity.this, "해당아이디가 있습니다", Toast.LENGTH_SHORT).show();
					etid.setText("");
					break;
				case 555:
					Toast.makeText(InsertActivity.this, "실패", Toast.LENGTH_SHORT).show();
					break;
				}
				super.handleMessage(msg);
			}
		};

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}

	}

