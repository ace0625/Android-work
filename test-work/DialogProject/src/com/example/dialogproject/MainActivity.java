package com.example.dialogproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	int[] resId = {R.id.button1, R.id.button2, R.id.button3, R.id.button4};
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				doDialog1();
				break;
			case R.id.button2:
				showDialog(100);
				break;
			case R.id.button3:
				showDialog(200);
				break;
			case R.id.button4:
				showDialog(300);
				break;
			}
		}
	};
	
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id, Bundle args) {
		Log.v(TAG, "create call id : " +id);
		switch(id)
		{
		case 100:
			return new AlertDialog.Builder(this)
			.setTitle("타이틀")
			.setMessage("문자열입니다.")
			.setIcon(R.drawable.ic_launcher)
			.setPositiveButton("예", null)
			.setNeutralButton("중립", null)
			.setCancelable(isRestricted())
			.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(MainActivity.this, "아니오눌림", Toast.LENGTH_SHORT).show();
				}
			})
			.create();
			
		case 200:
			final String[] items = {"aaa", "bbb", "ccc", "ddd", "eee"};
			return new AlertDialog.Builder(this)
			.setTitle("타이틀")
			.setItems(items, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(MainActivity.this, items[which] + " 눌림", Toast.LENGTH_SHORT).show();
				}
			})
			.setIcon(R.drawable.ic_launcher)
			.setPositiveButton("예", null)
			.setNeutralButton("중립", null)
			.setCancelable(isRestricted())
			.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(MainActivity.this, "아니오눌림", Toast.LENGTH_SHORT).show();
				}
			})
			.create();
		
		case 300:
			final View view = View.inflate(this, R.layout.mydialog, null);
			return new AlertDialog.Builder(this)
			.setTitle("커스텀")
			.setView(view)
			.setIcon(R.drawable.ic_launcher)
			.setCancelable(isRestricted())
			.setNegativeButton("닫기", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					EditText et1 = (EditText)view.findViewById(R.id.editText1);
					EditText et2 = (EditText)view.findViewById(R.id.editText2);
					CheckBox cbx = (CheckBox)view.findViewById(R.id.checkBox1);
					Toast.makeText(MainActivity.this, 
							et1.getText().toString() + " " + et2.getText().toString() + 
							(cbx.isChecked()?"선택됨" : "해제됨"), Toast.LENGTH_SHORT).show();
				}
			})
			.create();
			
		}
		return super.onCreateDialog(id, args);
		
	}
	
	int cnt = 0;
	@Override
	@Deprecated
	protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
		Log.v(TAG, "onprepare call id : " +id);
		switch(id)
		{
		case 100:
			cnt++;
			((AlertDialog)dialog).setMessage("cnt : " + cnt);
			break;
		}
		super.onPrepareDialog(id, dialog, args);
	}


	void doDialog1()
	{
		String msg = "문자열입니다.";
		AlertDialog dialog = new AlertDialog.Builder(this)
		.setTitle("타이틀")
		.setMessage(msg)
		.setIcon(R.drawable.ic_launcher)
		.setPositiveButton("예", null)
		.setCancelable(isRestricted())
		.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "아니오눌림", Toast.LENGTH_SHORT).show();
			}
		})
		.create();
		
		dialog.show();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		for(int id : resId)
		{
			findViewById(id).setOnClickListener(bHandler);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
