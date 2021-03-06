package com.green5941.sms_tost_project;

import java.util.zip.Inflater;

import com.green5941.sms_tost_project.R.layout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyToast extends BroadcastReceiver{
	private static final String TAG = "MyToast";
	LayoutInflater inflater;
	LinearLayout linear;
	TextView smsTv,adTv;
	public static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
	SmsMessage sms;
	
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		
		@Override
		public void onClick(View v) {
			Toast.makeText(v.getContext(), "123", Toast.LENGTH_LONG).show();
		}
	};
	
	@Override
	public void onReceive(Context context, Intent intent) {
		final String action= intent.getAction();
		Log.v(TAG, "Action : "+ action);
		Button bt = new Button(context);
		bt.setText("button");
		bt.setOnClickListener(bHandler);
		inflater = LayoutInflater.from(context);
		linear =(LinearLayout)inflater.inflate(R.layout.mytoast, null);
		smsTv  =(TextView)linear.findViewById(R.id.smsTV);
		linear.addView(bt);
		adTv = (TextView)linear.findViewById(R.id.adTV);
		
		if(action.equals(SMS_RECEIVED)){
			Object[] pdus = (Object[])intent.getExtras().get("pdus");
			for(Object p : pdus){
				Log.v(TAG, p.toString());
				sms = SmsMessage.createFromPdu((byte[])p);
				Log.i(TAG, "Sms: "+sms.getDisplayMessageBody());
			}
		}
		smsTv.setText(sms.getDisplayMessageBody());
		Toast toast = new Toast(context);
		toast.setDuration(10000);
		toast.setView(linear);
		toast.show();
		
		}
}
	
	
