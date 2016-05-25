package com.example.countproject;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.ViewStub;
import android.widget.RemoteViews;

public class CountProvider extends AppWidgetProvider {
	private static final String TAG = "MainActivity";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if("aaa.bbb.text".equals(action)) //원하는 일 
		{
			doProcess(context, intent);
		}
		super.onReceive(context, intent);
	}
	void doProcess(Context context, Intent intent)
	{
		RemoteViews views  = new RemoteViews(context.getPackageName(), R.layout.appwidget);
		
//		SharedPreferences sp = context.getSharedPreferences("myData", 0);
//		int cnt = sp.getInt("cnt", 0);
//		cnt++;
//		
//		SharedPreferences.Editor editor = sp.edit();
//		editor.putInt("cnt", cnt);
//		editor.commit();
//		views.setTextViewText(R.id.textView1, "cnt: " + cnt);
//		
//		views.setTextViewText(R.id.textView1, intent.getStringExtra("data"));
		views.setTextViewText(R.id.textView1, intent.getStringExtra("cnt"));
		
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		ComponentName cName = new ComponentName(context, CountProvider.class);
		appWidgetManager.updateAppWidget(cName, views);
	}

	int cnt = 0;
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		cnt++;
		Log.v(TAG,"onUpdate cnt : " +cnt);
		RemoteViews views  = new RemoteViews(context.getPackageName(), R.layout.appwidget);
		views.setTextViewText(R.id.textView1, "Tacademy");
		
		
		Intent intent = new Intent("aaa.bbb.START"); //하나로하기
		PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		views.setOnClickPendingIntent(R.id.button1, pIntent);
		
//		Intent intent = new Intent("aaa.bbb.START");
//		PendingIntent pIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//		views.setOnClickPendingIntent(R.id.button1, pIntent);
//		
////		
//		Intent intent1 = new Intent("aaa.bbb.STOP");
//		PendingIntent pIntent1 = PendingIntent.getService(context, 0, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
////		views.setOnClickPendingIntent(R.id.button1, pIntent1);
//		
		
//		Intent intent = new Intent(context, CountService.class);
//		PendingIntent pIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//		Intent intent = new Intent("aaa.bbb.text");
//		intent.putExtra("data","android");
//		PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//		
		Intent intentActivity = new Intent(context, CountActivity.class);
//		intentActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent pIntentActivity = PendingIntent.getActivity(context, 0, intentActivity, PendingIntent.FLAG_CANCEL_CURRENT);
		views.setOnClickPendingIntent(R.id.imageView1, pIntentActivity);
		
		ComponentName cName = new ComponentName(context, CountProvider.class);
		appWidgetManager.updateAppWidget(cName, views);
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
}














