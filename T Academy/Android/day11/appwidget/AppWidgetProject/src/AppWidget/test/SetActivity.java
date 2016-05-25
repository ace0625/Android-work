package AppWidget.test;


import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RemoteViews;

public class SetActivity extends Activity {
	private static final String TAG = "AppWidget";
	EditText et;
	int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
	
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
	   		SharedPreferences prefs = getSharedPreferences("testAppWidget", 0);
	        SharedPreferences.Editor prefe = prefs.edit();
	        prefe.putString("title", et.getText().toString());
	        prefe.commit();
	        String msg = prefs.getString("title", "android");

	        
			Intent intent1 = new Intent(SetActivity.this, MyService.class);
			intent1.putExtra("test", et.getText().toString());
			startService(intent1);
			

//            finish();
			
			ComponentName thisWidget = new ComponentName(SetActivity.this,TestAppWidgetProvider.class);
			AppWidgetManager appWidgetManager;
			RemoteViews remoteViews;
			remoteViews = new RemoteViews( getPackageName(), R.layout.appwidget_provider);
			appWidgetManager = AppWidgetManager.getInstance(SetActivity.this);
	        remoteViews.setTextViewText( R.id.apptxt, msg );
	        
	        appWidgetManager.updateAppWidget(thisWidget,remoteViews);
//	        appWidgetManager.updateAppWidget(mAppWidgetId,remoteViews);
			
			Intent intent = new Intent();	          //����Ŭ���� �����۽�Ʈ ��Ƽ��Ƽ �̵�
	        intent.addCategory(Intent.CATEGORY_BROWSABLE); 
	        intent.setComponent(new ComponentName("AppWidget.test", "AppWidget.test.SetActivity"));
	        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
	        PendingIntent pendingIntent = PendingIntent.getActivity(SetActivity.this, 0, intent, 0);
	        
	        remoteViews = new RemoteViews( getPackageName(), R.layout.appwidget_provider);
	        
	        remoteViews.setOnClickPendingIntent(R.id.appbtn, pendingIntent); 
	        appWidgetManager.updateAppWidget(thisWidget,remoteViews);
//	        appWidgetManager.updateAppWidget(mAppWidgetId,remoteViews);
	        Intent resultValue = new Intent();
	        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
	        setResult(RESULT_OK, resultValue);
	        finish();
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setResult(RESULT_CANCELED);
		
		setContentView(R.layout.appset);
		findViewById(R.id.setbtn).setOnClickListener(bHandler);
		et = (EditText)findViewById(R.id.settext);
		
		//���� �׼��� �߻��� AppWidget id ���� ��� ��
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        Log.v(TAG, "id xxx : " +mAppWidgetId );
        // id�� ��ȿ���� ���� ���̵��̸� �⺻ ���� ��Ƽ��Ƽ ����.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }
	}

}
