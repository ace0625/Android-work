package com.example.surfaceproject;

import java.util.concurrent.SynchronousQueue;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MyView extends SurfaceView implements Callback {
	private static final String TAG = "MainActivity";

	Context context;
	Paint paint = null;;
	SurfaceHolder holder = null;
	
	void init(Context context)
	{
		this.context = context;
		holder = getHolder();  //surface view에 추가
		holder.addCallback(this);
		
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(30);
		paint.setAntiAlias(true);
		paint.setDither(true);
	}
	
	boolean onAir = false;
	AniThread trd = null;
	
	int cnt = 0;
	int state = 0;
	private static final int STOP = 0;
	private static final int PLAY = 1;
	void doAction()
	{
		switch(state)
		{
			case STOP:
				break;
			case PLAY:
				cnt++;
				break;
		}
	}
	void doDraw()
	{
		synchronized (holder) 
		{
			Canvas canvas = holder.lockCanvas();
			
			canvas.drawColor(Color.YELLOW);
			
			canvas.drawText("cnt : " +cnt, 100, 100, paint);
			
			holder.unlockCanvasAndPost(canvas);
		}
		
	}
	class AniThread extends Thread
	{
		public void run()
		{
			while(onAir)
			{
				//로직구현
				doAction();
				
				//UI변경
				doDraw();
				Log.v(TAG, "작업중....");
				SystemClock.sleep(500);
			}
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			switch(state)
			{
			case STOP:
				state = PLAY;
				break;
			case PLAY:
				state = STOP;
				break;
			}
			break;
		} 
		return true;
	}

	public MyView(Context context) {
		super(context);
		init(context);
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public MyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Log.v(TAG, "changed");

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.v(TAG, "create");
		
		if(trd == null)
		{
			onAir = true;
			trd = new AniThread();
			trd.start();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.v(TAG, "destroyed");
		
		if(trd != null)
		{
			onAir = false;
				try {
					trd.join();
					//trd.interrupt();
				} catch (InterruptedException e) {
				}
		}
	}

}
