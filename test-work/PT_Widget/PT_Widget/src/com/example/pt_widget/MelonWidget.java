package com.example.pt_widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MelonWidget extends AppWidgetProvider {
	private static final String TAG = "MainActivity";
	
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
	    super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Log.v(TAG, "onUpdate");
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.melonwidget);
		Intent intent = new Intent("com.example.pt_widget.intent.action.UPDATE");
		PendingIntent pIntent = PendingIntent.getBroadcast(context,
				0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		views.setOnClickPendingIntent(R.id.button1, pIntent);
		Intent intent1 = new Intent("com.example.pt_widget.intent.action.NEXT");
		PendingIntent pIntent1 = PendingIntent.getBroadcast(context,
				0, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
		views.setOnClickPendingIntent(R.id.imageButton2, pIntent1);
		Intent intent2 = new Intent("com.example.pt_widget.intent.action.PREV");
		PendingIntent pIntent2 = PendingIntent.getBroadcast(context,
				0, intent2, PendingIntent.FLAG_CANCEL_CURRENT);
		views.setOnClickPendingIntent(R.id.imageButton1, pIntent2);
		ComponentName cName = new ComponentName(context, MelonWidget.class);
		appWidgetManager.updateAppWidget(cName, views);
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
	}
}
