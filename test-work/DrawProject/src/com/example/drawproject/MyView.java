package com.example.drawproject;

import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View 
{
	private static final String TAG = "MainActivity";
	Context context;
	Paint paint = null;
	Paint paint1= null;
	
	int vW = -1, vH;
	int l, r, t, b;
	Canvas canvas;
	
	Path path = null;
	
	Bitmap bitmap = null;
	
	
	void init(Context context)
	{
		paint = new Paint();
		this.context = context;
		paint.setColor(Color.RED);
		paint.setAntiAlias(true); //곡선처리
		paint.setDither(true); //곡선부드럽게
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(5);
//		paint.setARGB(128, 0, 0, 255);
//		DashPathEffect effect = new DashPathEffect(new float[]{5,10},1);
//		paint.setPathEffect(effect);
		
		paint1 = new Paint();
		paint1.set(paint);
		paint1.setColor(Color.GREEN);
		paint1.setStyle(Paint.Style.FILL);
		
		path = new Path();
		path.moveTo(20, 20);
		path.lineTo(120, 20);
		path.rLineTo(40, 70);
		path.rLineTo(20, -10);
		path.rLineTo(20, 40);
		
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test2);
		
		setDrawingCacheEnabled(true);
		Bitmap capture = getDrawingCache(); //View 캡쳐
	}
	

	
	public void doLeft(int step)
	{
		l -= step;
		r -= step;
		
		invalidate();
	}
	protected void onDraw(Canvas canvas) 
	{
		Log.v(TAG, "draw");
		if(vW == -1)
		{
			vW = getWidth();
			vH = getHeight();
			l = (vW - 100)/2;
			r = l + 100;
			this.canvas = canvas;
		}	
		myDrawA();
		super.onDraw(canvas);
	}
	
	void myDrawA()
	{
		canvas.drawColor(Color.YELLOW);
		
		if(bitmap != null)
		{
//			canvas.drawBitmap(bitmap, null, new Rect(20,20,50,50), paint);//섬네일에 적용
//			canvas.drawBitmap(bitmap, null, new Rect(20,20,350,350), paint);
//			canvas.drawBitmap(bitmap, new Rect(30,30,60,60), new Rect(20,20,bitmap.getWidth() + 50, bitmap.getHeight() + 50), paint); //왼쪽보기 오른쪽보기 망원경
////			
//			Bitmap bm = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);  //썸네일
//			Canvas c = new Canvas(bm);
//			c.drawBitmap(bitmap, null, new Rect(0, 0, 100, 100), null);
//			try{
//				bm.compress(CompressFormat.PNG, 100, new FileOutputStream("/mnt/sdcard/aaa.png"));
//			}catch(Exception e)
//			{
//				
//			}
			
			canvas.drawBitmap(bitmap, 20, 20, paint);
			
			Matrix m  = new Matrix();
			m.setScale(-1, 1);
			
			Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, false);
			canvas.drawBitmap(bitmap, 20, 20, paint);
			canvas.drawBitmap(bm, 20, 200, paint);
		}
		
		
		
//		Path p1 = new Path();
//		p1.moveTo(20, 20);
////		p1.quadTo(70, 120, 250, 100);
//		p1.cubicTo(70, 120, 250, 100, 330, 220);
//		
//		canvas.drawPath(p1, paint);
//		paint.setTextSize(30);
//		canvas.drawTextOnPath("티아카데미 안드로이드", p1, 10, 10, paint);
//		canvas.drawPath(p1, paint);
//		path.offset(0, 120);
//		canvas.drawPath(path, paint);
//		path.offset(0, 120);
//		canvas.drawPath(path, paint);
//		path.offset(0, 120);
//		canvas.drawPath(path, paint);
//		path.offset(0, 120);
//		canvas.drawPath(path, paint);
//		path.offset(0, 120);
		
		
//		int l = (vW - 100)/2;
//		int r = l + 100;
//		paint.setStrokeWidth(30);
//		paint.setStrokeCap(Paint.Cap.ROUND);
//		canvas.drawLine(20, 20, 200, 20, paint);
//		
////		canvas.drawRect(l, t, r, b, paint);
//		canvas.drawCircle(100, 100, 50, paint);
//		paint.setTextSize(30);
//		canvas.drawText("안드로이드 TAcademy 안드로이드 TAcademy", 100, 100, paint);
//		
//		float size = paint.measureText("A");
//		Log.v(TAG, "A의 넓이 : " +size);
//		size = paint.measureText("가");
//		Log.v(TAG, "가의 넓이 : " +size);
//		size = paint.measureText("9");
//		Log.v(TAG, "9의 넓이 : " +size);
		
//		paint.setTextAlign(Paint.Align.CENTER);
//		canvas.drawText("안드로이드 TAcademy", 100, 150, paint);
//		paint.setTextAlign(Paint.Align.RIGHT);
//		canvas.drawText("안드로이드 TAcademy", 100, 200, paint);
//		canvas.drawArc(new RectF(100, 100,  300, 300), 30, 80, true, paint1);
//		paint1.setColor(Color.BLUE);
//		canvas.drawArc(new RectF(100, 100,  300, 300), 110, 60, true, paint1);
//		paint1.setColor(Color.GRAY);
//		canvas.drawArc(new RectF(100, 100,  300, 300), 170, 120, true, paint1);
//		paint1.setColor(Color.CYAN);
//		canvas.drawArc(new RectF(100, 100,  300, 300), 290, 100, true, paint1);
		
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
//			event.getPointerCount() //멀티터치 사용시
			l = (int)event.getX();
			r = l + 100;
			t = (int)event.getY();
			b = t + 100;
			invalidate();
			break;
		}
		return true;
	}
	public MyView(Context context) 
	{
		super(context);
		init(context);
	}

	public MyView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		init(context);
	}

	public MyView(Context context, AttributeSet attrs, int defStyle) 
	{
		super(context, attrs, defStyle);
		init(context);
	}

}
