package com.example.activityproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Sub1Activity extends Activity {
	private static final int PROM_EXIT = 999;
	EditText et, et2;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			switch(v.getId())
			{
			case R.id.button1:
				//종료
				setResult(PROM_EXIT);
				finish();
				break;
			case R.id.button2:
//				Intent data = new Intent();
//				data.putExtra("result", et2.getText().toString());
//				
				MyData data = MyData.getInstance();
				data.setStr(et2.getText().toString());
				data.setCnt(120);
				//setResult(Activity.RESULT_OK,data);
				setResult(Activity.RESULT_OK);
				finish();
				break;
			}
		}
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.sub1);
	    
	    findViewById(R.id.button1).setOnClickListener(bHandler);
	    findViewById(R.id.button2).setOnClickListener(bHandler);
	    
//	    Intent intent = getIntent();
//	    String str = intent.getStringExtra("data");
//	    
//	    int cnt = intent.getIntExtra("cnt", 100);
	   
	    et = (EditText)findViewById(R.id.editText1);
	    et2 = (EditText)findViewById(R.id.editText2);
	    
	  //  et.setText(str + "  " + cnt);
	   
	    MyData data = MyData.getInstance();
	    String str = data.getStr();
	    int cnt = data.getCnt();
	    
	}

}
