package com.example.notiproject;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	Vibrator vi = null;
	NotificationManager manager;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
//				vi.vibrate(50000);
//				vi.cancel();
				vi.vibrate(new long[]{1000, 2000, 500, 1000, 2000, 2000}, -1); // 진동, 쉼, 진동, 쉼, 진동
				break;
			case R.id.button2:
				doNoti();
				break;
			}
			
		}
	};
	
	void doNoti()
	{
		Notification notification = null;
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher); //large 아이콘은 비트맵으로 
//		notification = new Notification(icon, tickerText, when)
		Intent intent = new Intent(this, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP); //창이 하나만 생성 
		intent.putExtra("data", "Tacademy");
//		Intent intent = new Intent(this, MyService.class); //서비스를 시작하는 인텐트
//		Intent intent = new Intent("aaa.bbb.ccc"); //방송
		
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT); //pending intent로는 스탑 불가
		notification = new Notification.Builder(this)
		.setContentText("텍스트 입니다")
		.setContentTitle("TITLE")
		.setTicker("Ticker..") //위에 달빅창
		.setSmallIcon(R.drawable.ic_launcher) //상단의 이미지
		.setLargeIcon(bitmap) //노티의 큰 이미지
		.setWhen(System.currentTimeMillis()) //시간 자동처리
		.setContentIntent(pIntent)
		.setAutoCancel(true) //터치하면 노치 사라짐
		.build();
		
		//이자리 if문...띄우고자하는 창이 최상위에 있을때 없을때
		manager.notify(100, notification);
		
//		manager.cancel(100);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		vi = (Vibrator)getSystemService(VIBRATOR_SERVICE);
		manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		
		Intent intent = getIntent();
//		String data = intent.getStringExtra("data");
//		if(data == null)
//		{
//			Log.v(TAG, "일반적으로 실행");
//		}
//		else
//		{
//			Log.v(TAG,"받아서 실행");
//		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		String data = intent.getStringExtra("data");
		if(data == null)
		{
			Log.v(TAG, "일반적으로 실행");
		}
		else
		{
			Log.v(TAG,"받아서 실행");
		}
		super.onNewIntent(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
