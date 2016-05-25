package com.example.httpproject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MyFragment4 extends Fragment {
	private static final String TAG = "MainActivity";
	Activity activity = null;
	EditText etFile, etResult;
	ImageView img;
	ProgressDialog pDialog = null;
	@Override
	public void onAttach(Activity activity) {
		this.activity = activity;
		super.onAttach(activity);
	}
	class UpLoadThread extends Thread
	{
		public void run()
		{
			String url = "http://mynode.sungback.cloulu.com/upload";
//			String et = etID.getText().toString().trim();
//			String pw = etPw.getText().toString().trim();
		
			Log.v(TAG, "URL: " +url);
			
			HttpClient client = null;
			HttpPost request = null;
			HttpResponse response = null;
			
			BufferedReader br = null;
			StringBuilder sb = null;
			Message msg = handler.obtainMessage();
			int code = 0;
			
			Bitmap bitmap = null;
			
			MultipartEntity entity;
			
			try {
				
				FileInputStream fis = new FileInputStream(path);
				byte[] fileData = IOUtils.toByteArray(fis);
				
				InputStreamBody fupload = new InputStreamBody(new ByteArrayInputStream(fileData), fName); //서버에서 저장할때 fName으로 저장됨
				entity = new MultipartEntity();
				entity.addPart("upfile", fupload);     //upfile 자리에 업로드하기위한 서버 명입력 
				
//				entity = new UrlEncodedFormEntity(params);
				
				client = NetManager.getHttpClient();
				request = NetManager.getPost(url);
				request.setEntity(entity); //post 인경우
				response = client.execute(request);
				code = response.getStatusLine().getStatusCode();
				switch(code)
				{
				case 200:
//					String str = IOUtils.toString(response.getEntity().getContent());
					byte[] brr = IOUtils.toByteArray(response.getEntity().getContent()); 
					Log.v(TAG, "brr: " +brr.length);
					Log.v(TAG, "code: " +code);
					
					
					URL imgURL = new URL("http://mynode.sungback.cloulu.com/load/dog.png");
					bitmap = BitmapFactory.decodeStream(imgURL.openStream());
					img.setImageBitmap(bitmap);
					msg.what = 999;
					msg.obj = "성공";

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
				etResult.setText(msg.obj.toString());
				break;
			case 888:
				etResult.setText("code: " +msg.arg1);
				break;
			case 555:
				Toast.makeText(activity, msg.obj.toString(), 
						Toast.LENGTH_SHORT).show();
				etResult.setText("error: " +msg.obj);
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			switch(v.getId())
			{
			case R.id.button1:
				getFilePath(fName);
				break;
			case R.id.button2:
				pDialog = ProgressDialog.show(activity, "", "이미지업로드중...");
				new UpLoadThread().start();
				break;
//			case R.id.button3:
//				changeImage("http://mynode.sungback.cloulu.com/load/dog.png");
//				break;
			}
			
		}
	};
	
	String fName = "dog.png";
	String path = "";
	void getFilePath(String fName)
	{//mountted unmounted검사
		File sdPath = Environment.getExternalStorageDirectory();
		File f = new File(sdPath, fName);
		if(f.exists())
		{
			path = f.getAbsolutePath();
			etFile.setText(path);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = null;
		v = inflater.inflate(R.layout.frame4, null);
		etFile = (EditText)v.findViewById(R.id.editText1);
		etResult = (EditText)v.findViewById(R.id.editText2);
		img = (ImageView)v.findViewById(R.id.imageView1);
		v.findViewById(R.id.button1).setOnClickListener(bHandler);
		v.findViewById(R.id.button2).setOnClickListener(bHandler);
		v.findViewById(R.id.button3).setOnClickListener(bHandler);
		
		return v;
	}
	
}
