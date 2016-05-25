package com.example.myappwidgetproject2;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MyAppWidgetProvider1 extends AppWidgetProvider {
	private static final String TAG = "MainActivity";

	
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		Log.v(TAG, "action : " + action);
		if(action.equals("aaa.bbb.ActionStart")){
			doChangeImage(context, R.drawable.icon01);
			Intent intent1 = new Intent(context, MyService.class);			
			context.startService(intent1);
			return;
		}else if( action.equals("aaa.bbb.ActionStop") ){
			doChangeImage(context, R.drawable.icon02);
			Intent intent1 = new Intent(context, MyService.class);
			context.stopService(intent1);
			return;
		}else if( action.equals("aaa.bbb.actionCount") ){
			doChangeText(context, intent.getIntExtra("cnt", 1));
			return;
		}
		super.onReceive(context, intent);
	}
	void doChangeImage(Context context, int srcId){
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		ComponentName cName = new ComponentName(context, MyAppWidgetProvider1.class);
		
		
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
		
		views.setImageViewResource(R.id.imageView1, srcId);
		
		
		manager.updateAppWidget(cName, views);
		
	}
	void doChangeText(Context context, int cnt){
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		ComponentName cName = new ComponentName(context, MyAppWidgetProvider1.class);
		
		
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
		
		views.setTextViewText(R.id.textView1, "cnt : " + cnt);
		
		manager.updateAppWidget(cName, views);
		
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Log.v(TAG, "onUpdate call'");
		
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
		PendingIntent pIntent1, pIntent2;
		
		Intent intent1 = new Intent("aaa.bbb.ActionStart");
		
		pIntent1 = PendingIntent.getBroadcast(context, 0, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
		
		Intent intent2 = new Intent("aaa.bbb.ActionStop");
		
		pIntent2 = PendingIntent.getBroadcast(context, 0, intent2, PendingIntent.FLAG_CANCEL_CURRENT);
	
		
		views.setOnClickPendingIntent(R.id.button1, pIntent1);
		views.setOnClickPendingIntent(R.id.button2, pIntent2);
		
		
		
		
		
		appWidgetManager.updateAppWidget(appWidgetIds, views);
		
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	
}
