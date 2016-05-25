package com.example.countproject;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ViewStub;
import android.widget.RemoteViews;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		RemoteViews views  = new RemoteViews(context.getPackageName(), R.layout.appwidget);
		
		Intent service = new Intent(context, CountService.class);
		Intent command = new Intent();
		PendingIntent pIntent = null;
		if("aaa.bbb.START".equals(action))
		{
			views.setImageViewResource(R.id.imageView1, R.drawable.icon01);
			views.setTextViewText(R.id.button1, "STOP");
			command.setAction("aaa.bbb.STOP");
			context.startService(service);
		}
		else if("aaa.bbb.STOP".equals(action))
		{
			views.setImageViewResource(R.id.imageView1, R.drawable.icon02);
			views.setTextViewText(R.id.button1, "START");
			command.setAction("aaa.bbb.START");
			context.stopService(service);
		}
		
		pIntent  = PendingIntent.getBroadcast(context, 0, command, PendingIntent.FLAG_CANCEL_CURRENT);
		views.setOnClickPendingIntent(R.id.button1, pIntent);
		
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		ComponentName cName = new ComponentName(context, CountProvider.class);
		appWidgetManager.updateAppWidget(cName, views);
	}

}
