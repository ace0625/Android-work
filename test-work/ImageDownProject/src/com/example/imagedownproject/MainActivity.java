package com.example.imagedownproject;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	ImageView img;
	
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
//				doImageChange(R.drawable.daum);
//				doImageChange("/aaa/daum.png");
//				doImageChangeWeb("http://icon.daumcdn.net/w/c/12/11/10192021148946703.png");
				doImageChangeWeb1("http://icon.daumcdn.net/w/c/12/11/10192021148946703.png");
				break;
			}
		}
	};
	void doImageChangeWeb1(String ImageUrl)
	{
		//외장메모리에 있나없나 검사해서 있으면 보여주고, 없으면 이미지 다운로드해서 외장메모리를 통해 저장한후 보여주기
		int lPoint = ImageUrl.lastIndexOf("/"); //뒤에서 부터 찾기
		String fName = ImageUrl.substring(lPoint + 1);
		File sdFile = Environment.getExternalStorageDirectory();
		File f1 = new File(sdFile, "/aaa/" + fName);
		Log.v(TAG, f1.getAbsolutePath());
		if(f1.exists())
		{
			Uri uri = Uri.fromFile(f1);
			img.setImageURI(uri);
		}
		else
		{
			new DownTask1().execute(ImageUrl, f1);
		}
//		new DownTask().execute(ImageUrl);
	
	}
	
	class DownTask1 extends AsyncTask<Object, Void, Bitmap>
	{
		ProgressDialog pDialog = null;
		@Override
		protected Bitmap doInBackground(Object... params) { //...은배열
			Bitmap bitmap = null;
			
			HttpClient client = null;
			HttpGet request = null;
			HttpResponse response = null;
			int code = 0;
			InputStream is = null;
			int length = 0;
			byte[] imgArr = null;
					try {
						client = NetManager.getHttpClient();
						request = NetManager.getGet(params[0].toString());
						response = client.execute(request);
						code = response.getStatusLine().getStatusCode();
						switch(code)
						{
						case 200:
							is = response.getEntity().getContent();
							
							length = (int)response.getEntity().getContentLength();
							if(length != -1)
							{
								imgArr = new byte[length];
								DataInputStream dis = new DataInputStream(is);
								dis.readFully(imgArr, 0, length);
							}
							else
							{
								int iData = 0;
								ByteArrayOutputStream baos = new ByteArrayOutputStream();
								
//								int size = 0;
//								int cnt = is.available();
//								byte[] brr = new byte[cnt];
//								while((size = is.read(brr,0,cnt))>0)
//								{
//									baos.write(brr,0, size);
//								}
								while((iData = is.read()) != -1)
								{
									baos.write(iData);
								}
								imgArr = baos.toByteArray();
							}
							bitmap = BitmapFactory.decodeByteArray(imgArr, 0, imgArr.length);
							bitmap.compress(CompressFormat.PNG, 100, 
									new FileOutputStream((File) params[1]));
							Log.v(TAG, "Image Loading success! ");
							break;
						}
					} catch (Exception e) {
						Log.v(TAG, "Image Load error : " +e);
					}finally{
						if(is != null)
						{
							try {
								is.close();
							} catch (IOException e) {
								
							}
						}
						
					}
			return bitmap;
		}
		@Override
		protected void onPostExecute(Bitmap result) {
			pDialog.cancel();
			if(result != null)
			{
				img.setImageBitmap(result);
			}
			else
			{
				Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			pDialog = ProgressDialog.show(MainActivity.this, "", "Loading...");
			super.onPreExecute();
		}
		
	}

	class DownTask extends AsyncTask<String, Void, Bitmap>
	{
		ProgressDialog pDialog = null;
		@Override
		protected Bitmap doInBackground(String... params) { //...은배열
			Bitmap bitmap = null;
			
			HttpClient client = null;
			HttpGet request = null;
			HttpResponse response = null;
			int code = 0;
			InputStream is = null;
			int length = 0;
			byte[] imgArr = null;
					try {
						client = NetManager.getHttpClient();
						request = NetManager.getGet(params[0]);
						response = client.execute(request);
						code = response.getStatusLine().getStatusCode();
						switch(code)
						{
						case 200:
							is = response.getEntity().getContent();
							
							length = (int)response.getEntity().getContentLength();
							if(length != -1)
							{
								imgArr = new byte[length];
								DataInputStream dis = new DataInputStream(is);
								dis.readFully(imgArr, 0, length);
							}
							else
							{
								int iData = 0;
								ByteArrayOutputStream baos = new ByteArrayOutputStream();
								
//								int size = 0;
//								int cnt = is.available();
//								byte[] brr = new byte[cnt];
//								while((size = is.read(brr,0,cnt))>0)
//								{
//									baos.write(brr,0, size);
//								}
								while((iData = is.read()) != -1)
								{
									baos.write(iData);
								}
								imgArr = baos.toByteArray();
							}
							bitmap = BitmapFactory.decodeByteArray(imgArr, 0, imgArr.length);
							Log.v(TAG, "Image Loading success! ");
							break;
						}
					} catch (Exception e) {
						Log.v(TAG, "Image Load error : " +e);
					}finally{
						if(is != null)
						{
							try {
								is.close();
							} catch (IOException e) {
								
							}
						}
						
					}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			pDialog.cancel();
			if(result != null)
			{
				img.setImageBitmap(result);
			}
			else
			{
				Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			pDialog = ProgressDialog.show(MainActivity.this, "", "Loading...");
			super.onPreExecute();
		}
		
	}
	void doImageChange(String name)
	{
		Uri uri = null;
		File sdFile = Environment.getExternalStorageDirectory();
		File f= new File(sdFile, name);
		uri = Uri.fromFile(f);
//		uri = Uri.parse("http://icon.daumcdn.net/w/c/12/11/10192021148946703.png");
		img.setImageURI(uri);
	}
	void doImageChange(int resId)
	{
		img.setImageResource(resId);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.button1).setOnClickListener(bHandler);
		img = (ImageView)findViewById(R.id.imageView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
