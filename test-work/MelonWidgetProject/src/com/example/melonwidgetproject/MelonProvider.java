package com.example.melonwidgetproject;

import java.util.ArrayList;

import com.example.melonproject.Melon;
import com.example.melonproject.MyAdapter;
import com.example.melonproject.Song;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MelonProvider extends AppWidgetProvider {
	private static final String TAG  = "MainActivity";
	
	Melon data;
	ArrayList<Song> Sdata;
	MyAdapter adapter = null;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		
		Log.v(TAG, "onUpdate");
		
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget);
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}


}
