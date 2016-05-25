package org.kimjw.httptest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private static final String TAG = "MainActivity";
	
	LinearLayout liner1,liner2,liner3,liner4;
	EditText etout;
	
	EditText ename, eaddr;
	
	EditText eid, epw;
	
	EditText epname, eprice;
	ImageView img;
	
	ProgressDialog pDialog;
	String data = "";
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			etout.setText(data);
//			etout.setText((String)(msg.obj));
			pDialog.dismiss();
		}
		
	};
	
	class ReadDataProcess extends Thread{
		View v;
		ReadDataProcess(View v){
			this.v = v;
		}
		public void run(){
			switch(v.getId()){
			case R.id.btnhttp1 :
				getHttp1();
				break;
			case R.id.btnhttp2 :
				getHttp2();
				break;
			case R.id.btnlogin :
				ProcessLogin();
				break;
			case R.id.btninsert :
				postFileUpload();
				break;
			case R.id.btnget :
				processGet();
				break;
			}
		}
	}
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			pDialog = ProgressDialog.show(MainActivity.this, "Http 테스트", "다운로드중...");
			Log.v(TAG, "click");
			new ReadDataProcess(v).start();
			
		}
	};
	private void getHttp1(){
		String url = "http://www.google.co.kr";
//		String url = "http://192.168.0.31/test.html";
		HttpClient client = NetManager.getHttpClient();
		HttpGet get = NetManager.getGet(url);
		HttpResponse response = null;
		BufferedReader br = null;
		StringBuffer sb = null;
		
		String line = "";
		try{
			Log.v(TAG, "process");
			response = client.execute(get);
			int code = response.getStatusLine().getStatusCode();
			Log.v(TAG, "process code : " + code);
			switch(code){
			case 200 :
//				br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"euc-kr"));
				br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				sb = new StringBuffer();
				while((line = br.readLine())!= null){
					sb.append(line + "\n");
				}
				Log.v(TAG, "process msg : " + sb);
				data = sb.toString();
				handler.sendEmptyMessage(0);
//				Message msg = Message.obtain();
//				msg.obj = sb.toString();
//				handler.handleMessage(msg);
				break;
			default :
				//
			}
			
		}catch(Exception e){
			Log.v(TAG, "google error : " + e);
		}finally{
			try{
				br.close();
			}catch(Exception e){}
		}
	}
	private void getHttp2(){
		String url = "http://192.168.0.21/test.html";
	}
	private void processGet(){
		String url = "http://192.168.0.31/testget.jsp";
		
		Vector<NameValuePair> nameValue = new Vector<NameValuePair>() ;   

		nameValue.add( new BasicNameValuePair( "aname", ename.getText().toString() ) ) ;   
		nameValue.add( new BasicNameValuePair( "addr", eaddr.getText().toString() ) ) ;   
		
		url = url + "?" + URLEncodedUtils.format( nameValue, "UTF-8" ) ; 
		
		HttpClient client = NetManager.getHttpClient();
		HttpGet get = NetManager.getGet(url);
		HttpResponse response = null;
		BufferedReader br = null;
		StringBuffer sb = null;
		
		String line = "";
		try{
			Log.v(TAG, "process");
			response = client.execute(get);
			int code = response.getStatusLine().getStatusCode();
			Log.v(TAG, "process code : " + code);
			switch(code){
			case 200 :
//				br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"euc-kr"));
				br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				sb = new StringBuffer();
				while((line = br.readLine())!= null){
					sb.append(line + "\n");
				}
				Log.v(TAG, "process msg : " + sb);
				data = sb.toString();
				handler.sendEmptyMessage(0);
//				Message msg = Message.obtain();
//				msg.obj = sb.toString();
//				handler.handleMessage(msg);
				break;
			default :
			}
			
		}catch(Exception e){
			Log.v(TAG, "google error : " + e);
		}finally{
			try{
				br.close();
			}catch(Exception e){}
		}
	}
	private void ProcessLogin(){
		String url = "http://192.168.0.31/testlogin.jsp";
		
		List<NameValuePair> nameValue = new ArrayList<NameValuePair>() ;   

		nameValue.add( new BasicNameValuePair( "aid", eid.getText().toString() ) ) ;   
		nameValue.add( new BasicNameValuePair( "apw", epw.getText().toString() ) ) ;   
		
		
		HttpClient client = NetManager.getHttpClient();
		
		HttpPost post = NetManager.getPost(url);
		
		HttpResponse response = null;
		BufferedReader br = null;
		StringBuffer sb = null;
		
		String line = "";
		try{
			Log.v(TAG, "process");
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValue);
			post.setEntity(entity);
			response = client.execute(post);
			int code = response.getStatusLine().getStatusCode();
			Log.v(TAG, "process code : " + code);
			switch(code){
			case 200 :
//				br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"euc-kr"));
				br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				sb = new StringBuffer();
				while((line = br.readLine())!= null){
					sb.append(line + "\n");
				}
				Log.v(TAG, "process msg : " + sb);
				data = sb.toString();
				handler.sendEmptyMessage(0);
//				Message msg = Message.obtain();
//				msg.obj = sb.toString();
//				handler.handleMessage(msg);
				break;
			default :
			}
			
		}catch(Exception e){
			Log.v(TAG, "google error : " + e);
		}finally{
			try{
				br.close();
			}catch(Exception e){}
		}
	}
	private void postFileUpload(){
		String url = "http://192.168.0.31/fileupload.jsp";
		
		
		HttpClient client = NetManager.getHttpClient();
		
		HttpPost post = NetManager.getPost(url);
		
		HttpResponse response = null;
		BufferedReader br = null;
		StringBuffer sb = null;
		
		String line = "";
		try{
			Log.v(TAG, "process");
			Log.v(TAG, "process");
			FileInputStream fis = new FileInputStream
				(new File("/mnt/sdcard/bg_logo.png"));
			byte[] byteData = IOUtils.toByteArray(fis);
			
			InputStreamBody ibody = 
				new InputStreamBody(new ByteArrayInputStream
						(byteData),"lg.jpg");
			
			StringBody sb1 = new StringBody(epname.getText().toString(), Charset.forName("UTF-8"));
			StringBody sb2 = new StringBody(eprice.getText().toString());
			

			MultipartEntity entity = new MultipartEntity();
			
			entity.addPart("fname", ibody);
			
			entity.addPart("pname", sb1);
			entity.addPart("price", sb2);
			
			post.setEntity(entity);
			
			response = client.execute(post);
			int code = response.getStatusLine().getStatusCode();
			Log.v(TAG, "process code : " + code);
			switch(code){
			case 200 :
//				br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),"euc-kr"));
				br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				sb = new StringBuffer();
				while((line = br.readLine())!= null){
					sb.append(line + "\n");
				}
				Log.v(TAG, "process msg : " + sb);
				data = sb.toString();
				handler.sendEmptyMessage(0);
//				Message msg = Message.obtain();
//				msg.obj = sb.toString();
//				handler.handleMessage(msg);
				break;
			default :
			}
			
		}catch(Exception e){
			Log.v(TAG, "google error : " + e);
		}finally{
			try{
				br.close();
			}catch(Exception e){}
		}
	}
	View.OnTouchListener touchListener = new View.OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()){
			case MotionEvent.ACTION_DOWN :
				linearClear();
				switch(v.getId()){
				case R.id.tab1 :
					liner1.setVisibility(View.VISIBLE);
					break;
				case R.id.tab2 :
					liner2.setVisibility(View.VISIBLE);
					break;
				case R.id.tab3 :
					liner3.setVisibility(View.VISIBLE);
					break;
				case R.id.tab4 :
					liner4.setVisibility(View.VISIBLE);
					break;
				}
				break;				
			}
			return true;
		}
	};
	void linearClear(){
		liner1.setVisibility(View.INVISIBLE);
		liner2.setVisibility(View.INVISIBLE);
		liner3.setVisibility(View.INVISIBLE);
		liner4.setVisibility(View.INVISIBLE);		
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);        
        setUI();
        
    }
    private void setUI(){
    	liner1 = (LinearLayout)findViewById(R.id.liner1);
        liner2 = (LinearLayout)findViewById(R.id.liner2);
        liner3 = (LinearLayout)findViewById(R.id.liner3);
        liner4 = (LinearLayout)findViewById(R.id.liner4);
        
        findViewById(R.id.tab1).setOnTouchListener(touchListener);
        findViewById(R.id.tab2).setOnTouchListener(touchListener);
        findViewById(R.id.tab3).setOnTouchListener(touchListener);
        findViewById(R.id.tab4).setOnTouchListener(touchListener);
        
        etout = (EditText)findViewById(R.id.etout);
        
        ename = (EditText)findViewById(R.id.etaname);
        eaddr = (EditText)findViewById(R.id.etaddr);
        
        eid = (EditText)findViewById(R.id.etpid);
        epw = (EditText)findViewById(R.id.etpwd);
        
        
        epname = (EditText)findViewById(R.id.etpname);
        eprice = (EditText)findViewById(R.id.etprice);
        
        img = (ImageView)findViewById(R.id.img1);
        img.setImageURI(Uri.fromFile(new File("/mnt/sdcard/lg.jpg")));
        
        findViewById(R.id.btnget).setOnClickListener(bHandler);
        findViewById(R.id.btnhttp1).setOnClickListener(bHandler);
        findViewById(R.id.btnhttp2).setOnClickListener(bHandler);
        findViewById(R.id.btninsert).setOnClickListener(bHandler);
        findViewById(R.id.btnlogin).setOnClickListener(bHandler);
    }
}