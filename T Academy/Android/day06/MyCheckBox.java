package org.kimjw.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MyCheckBox extends TextView {
	private static final String TAG = "Mainactivity";
	boolean flag = false;
	String msg = "수신여부";
	Paint mPaint = null;
	public Bitmap[]  mBitmap = new Bitmap[2];
	
	
	Context mContext;
	public MyCheckBox(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		init(false, "수신여부");
	}

	public MyCheckBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		attrs.getAttributeCount();
		String name, value;
		for (int i=0;i<attrs.getAttributeCount();i++) {

			name = attrs.getAttributeName(i);

			value = attrs.getAttributeValue(i);
			if(name.equalsIgnoreCase("text")){
				msg = value;
			}
			Log.v(TAG, name + " : " + value);
 
		}


		init(false, msg);
	}

	public MyCheckBox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init(false, "");
	}
	void init(boolean flag, String msg){
		this.flag= flag;
		this.msg = msg;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(40);
        
        
        mBitmap[0]  = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.checkbox_off);
        mBitmap[1]  = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.checkbox_on);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawColor(0xFF000000);
		if(mBitmap[0]!= null){
			canvas.drawBitmap(mBitmap[flag?1:0], 10, 10, mPaint);
		}

        mPaint.setColor(0xffffffff);
        
//		canvas.drawText("수신여부", 60, 45, mPaint);
//		canvas.drawText(msg, mBitmap[0].getWidth() + 20, 65, mPaint);
		canvas.drawText(getText().toString(), mBitmap[0].getWidth() + 20, 60, mPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN :
			flag = !flag;
			this.invalidate();
			break;
		}
		return true;		
	}
}

