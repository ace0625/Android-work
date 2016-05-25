package AppWidget.test;

import java.util.Random;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.RemoteViews;

public class MyService extends Service {
	Context context;
	boolean onAir = false;
	Random ran;
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		context = getApplicationContext();
		SystemClock.sleep(3000);
		
		ComponentName thisWidget = new ComponentName("AppWidget.test", "AppWidget.test.TestAppWidgetProvider");
		AppWidgetManager appWidgetManager;
		RemoteViews remoteViews;
		
   		SharedPreferences prefs = context.getSharedPreferences("testAppWidget", 0);
        SharedPreferences.Editor prefe = prefs.edit();
        String msg = prefs.getString("title", "android");
        
		remoteViews = new RemoteViews( getPackageName(), R.layout.appwidget_provider);
		appWidgetManager = AppWidgetManager.getInstance(context);
        remoteViews.setTextViewText( R.id.apptxt, msg );
        appWidgetManager.updateAppWidget(thisWidget,remoteViews);
        
		Intent intent1 = new Intent();	          
		intent1.addCategory(Intent.CATEGORY_BROWSABLE); 
		intent1.setComponent(new ComponentName("AppWidget.test", "AppWidget.test.SetActivity"));
        
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        
        remoteViews = new RemoteViews( context.getPackageName(), R.layout.appwidget_provider);
        
        remoteViews.setOnClickPendingIntent(R.id.appbtn, pendingIntent);        
        appWidgetManager.updateAppWidget( thisWidget, remoteViews );
       
        ran = new Random();
        onAir = true;
        new AniThread().start();
        return super.onStartCommand(intent, flags, startId);
//        context.stop
	}
	class AniThread extends Thread{
		int cnt;
		public void run(){
			while(onAir){
			SystemClock.sleep(3000);
			cnt = ran.nextInt();
			Intent intent = new Intent();
			intent.putExtra("cnt", cnt);
			intent.setAction("kimjw.test.action");
			
			sendBroadcast(intent);

			}
		}
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		onAir = false;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
