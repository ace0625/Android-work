package com.example.appwidgetproject;

import java.lang.annotation.Annotation;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViews.RemoteView;

public class MyAppWidgetProvider extends AppWidgetProvider {
	private static final String TAG = "MainActivity";
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		Log.v(TAG, "onDeleted");
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		Log.v(TAG, "onDisabled");
		// TODO Auto-generated method stub
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		Log.v(TAG, "onEnabled");
		// TODO Auto-generated method stub
		super.onEnabled(context);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) 
	{
		Log.v(TAG, "onUpdated");
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget);
		views.setTextViewText(R.id.textView1, "change text");
		
		PendingIntent pendingIntent = null;
		views.setOnClickPendingIntent(R.id.textView1, pendingIntent);
		
		for(int appWidgetId: appWidgetIds)
		{
			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
		
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

	
	

}
