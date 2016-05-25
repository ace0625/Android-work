package AppWidget.test;




import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class TestAppWidgetProvider extends AppWidgetProvider {
	private static final String TAG = "AppWidget";
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		Log.v(TAG, "onDeleted");
		
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
		Log.v(TAG, "onDisabled");
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		Log.v(TAG, "onEnabled");
	}

	@Override
	public void onReceive(Context context, Intent intent1) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent1);
		String action = intent1.getAction();
		Log.v(TAG, "onReceive action :  " + action);
		if(action.equals("kimjw.test.action")){
//			Toast.makeText(context, "kimjw", Toast.LENGTH_SHORT).show();
			AppWidgetManager appWidgetManager;
			ComponentName cn = new ComponentName(context, TestAppWidgetProvider.class);
			appWidgetManager = AppWidgetManager.getInstance(context);
			Intent intent = new Intent();	          //날씨클릭시 웨더퍼스트 엑티비티 이동
	        intent.addCategory(Intent.CATEGORY_BROWSABLE); 
	        intent.setComponent(new ComponentName("AppWidget.test", "AppWidget.test.SetActivity"));
//	        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[appWidgetIds.length-1]);
	        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
	        
	        RemoteViews remoteViews, remoteViews1; 
	        remoteViews = new RemoteViews( context.getPackageName(), R.layout.appwidget_provider);
	        
	        remoteViews.setOnClickPendingIntent(R.id.appbtn, pendingIntent);        
	        appWidgetManager.updateAppWidget(cn,remoteViews);
	        int cnt = intent1.getIntExtra("cnt", 0);
	        remoteViews.setTextViewText( R.id.apptxt," number " + cnt  );
	        appWidgetManager.updateAppWidget(cn,remoteViews);
		}
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.v(TAG, "onUpdate : " + appWidgetIds.length);
		int widgetId = 0;
		for(int aa : appWidgetIds){
			Log.v(TAG, "idaaaa : " + aa);
		}
		RemoteViews remoteViews, remoteViews1; 
//        //if (appWidgetIds == null) {
//            appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(
//                    context, TestAppWidgetProvider.class));
//        //}
            Log.v(TAG ,"=====");
    		for(int aa : appWidgetIds){
    			Log.v(TAG, "idxxxxx : " + aa);
    			widgetId = aa;
    		}

		for(int i = 0; i < appWidgetIds.length; i++){
			Intent intent = new Intent();	          //날씨클릭시 웨더퍼스트 엑티비티 이동
	        intent.addCategory(Intent.CATEGORY_BROWSABLE); 
	        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
	        intent.setComponent(new ComponentName("AppWidget.test", "AppWidget.test.Set1Activity"));
	        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
	        
	        remoteViews = new RemoteViews( context.getPackageName(), R.layout.appwidget_provider);
	        
	        remoteViews.setOnClickPendingIntent(R.id.appbtn, pendingIntent);        
	        appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);
	        
	        remoteViews.setTextViewText( R.id.apptxt, "테스트" );
	        appWidgetManager.updateAppWidget(appWidgetIds[i],remoteViews);
	 
	        String str = intent.getStringExtra("str");
	        if(str == null || str.length() == 0){
	        	str = "기본문자열";
	        }
		}
//   		SharedPreferences prefs = context.getSharedPreferences("testAppWidget", 0);
//        SharedPreferences.Editor prefe = prefs.edit();
//        String msg = prefs.getString("title", "android");
//        int prefInt = prefs.getInt("num", 0);
//        
//        Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();
//
//		Intent intent = new Intent();	          //날씨클릭시 웨더퍼스트 엑티비티 이동
//        intent.addCategory(Intent.CATEGORY_BROWSABLE); 
//        intent.setComponent(new ComponentName("AppWidget.test", "AppWidget.test.SetActivity"));
//        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//        
//        remoteViews = new RemoteViews( context.getPackageName(), R.layout.appwidget_provider);
//        
//        remoteViews.setOnClickPendingIntent(R.id.appbtn, pendingIntent);        
//        appWidgetManager.updateAppWidget(appWidgetIds[0],remoteViews);
//
//        remoteViews.setTextViewText( R.id.apptxt, msg + " count : " + prefInt );
//        appWidgetManager.updateAppWidget(new ComponentName(context, TestAppWidgetProvider.class), remoteViews);
//        
//        prefe.putString("title", msg);
//        prefe.putInt("num", prefInt+1);
//        prefe.commit();
        

	}
	
}
