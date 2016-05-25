package com.example.pt_widget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;

public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "MainActivity";
	Melon data;
	Context context;
	RemoteViews views;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		views = new RemoteViews(context.getPackageName(), R.layout.melonwidget);
		String action = intent.getAction();
		this.context = context; 
		Log.v(TAG, "멜론받기 시작");
		if("com.example.pt_widget.intent.action.UPDATE".equals(action)){
			new JobTask().execute("http://apis.skplanetx.com/melon/charts/realtime?count=10&page=1&version=1&appKey=461cb22d-5082-377b-869d-f6c3d2b0156c&format=json");	
		}else if("com.example.pt_widget.intent.action.NEXT".equals(action)){
			nextRank(1);
		}else if("com.example.pt_widget.intent.action.PREV".equals(action)){
			nextRank(-1);
		}
	}
	void setData(Melon result, int current){
		views = new RemoteViews(context.getPackageName(), R.layout.melonwidget);
		views.setTextViewText(R.id.textView2, result.getSongs().get(current).getCurrentRank()+"");
		views.setTextViewText(R.id.textView3, result.getSongs().get(current).getArtists().get(0).getArtistName());
		views.setTextViewText(R.id.TextView01, result.getSongs().get(current).getAlbumName());
		views.setTextViewText(R.id.TextView02, result.getSongs().get(current).getSongName());
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		ComponentName cName = new ComponentName(context, MelonWidget.class);
		manager.updateAppWidget(cName, views);
	}
	
	void nextRank(int i){
		SharedPreferences sp = context.getSharedPreferences("melon", 0);
		String currentRank = sp.getString("cr", null);
		String max = sp.getString("max", null);
		Log.v(TAG,currentRank + " " + max +" " + i);
		int cur = 0, maxR = 0;
		if(max != null && currentRank != null){
			cur = Integer.parseInt(currentRank);
			maxR = Integer.parseInt(max);
		}else{
			return;
		}
		
		if( cur == 0 && i == -1){
			return;
		}else if(cur == maxR-1 && i == 1){
			return;
		}else{
			cur += i;
			SharedPreferences.Editor editor = sp.edit();
			editor.putString("cr", cur+"");
			editor.commit();
			setData(doReadFile(), cur);
		}
	}
	
	class JobTask extends AsyncTask<String, Void, Melon>{
		@Override
		protected void onPostExecute(Melon result) {
			Log.v(TAG, "result : " + result);
			setData(result, 0);
			AppWidgetManager manager = AppWidgetManager.getInstance(context);
			ComponentName cName = new ComponentName(context, MelonWidget.class);
			manager.updateAppWidget(cName, views);
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Melon doInBackground(String... params) {
			data = null;
			String url = params[0];
			HttpClient client = null;
			HttpGet request = null;
			HttpResponse response = null;
			BufferedReader br = null;
			int code = 0;
			try {
				client = NetManager.getHttpClient();
				request = NetManager.getGet(url);
				response = client.execute(request);
				code = response.getStatusLine().getStatusCode();
				switch(code){
				case 200 :
					br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
					StringBuilder sb = new StringBuilder();
					String sTemp = "";
					while((sTemp = br.readLine()) != null){
						Log.v(TAG, "aa");
						sb.append(sTemp).append("\n");
					}
					
					
					
					
					data = MyJsonParser.parse(sb.toString());
					if(data != null){
						doWriteFile("rank.dat",data);
					}
					SharedPreferences sp = context.getSharedPreferences("melon", 0);
					SharedPreferences.Editor editor = sp.edit();
					editor.putString("cr", 0+"");
					editor.putString("max", data.getSongs().size()+"");
					editor.commit();
					
					
					String currentRank = sp.getString("cr", null);
					String max = sp.getString("max", null);
					Log.v(TAG,currentRank + " " + max);
					
					Log.v(TAG, "===========================");
					Log.v(TAG, "melon : " + data);
					Log.v(TAG, "===========================");
					
					for (Song song : data.getSongs()) {
						Log.v(TAG,song.toString());
					}
					Log.v(TAG, "===========================");
					break;
				default :
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
			}
			return data;
		}
		
		
	}
	protected void doWriteFile(String fName, Melon data) {
		ObjectOutputStream oo = null;
		try {
			oo = new ObjectOutputStream(context.openFileOutput(fName, 0));
			oo.writeObject(data);
			oo.flush();
			Log.v(TAG, fName + "파일 저장 성공");
		} catch (IOException e) {
			Log.v(TAG, "파일 저장 실패" + e);
		} finally {
			if(oo != null)
				try {
					oo.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	protected Melon doReadFile() {
		ObjectInputStream oi = null;
		Melon data = null;
		try {
			oi = new ObjectInputStream(context.openFileInput("rank.dat"));
			data = (Melon) oi.readObject();
			Log.v(TAG,"체크 : " + data.getPage());
		} catch (Exception e) {
			Log.v(TAG, "File Read error : " + e);
		}finally{
			if(oi != null) {
				try {
					oi.close();
				} catch (IOException e) {}
			}
		}
		return data;
	}

}
