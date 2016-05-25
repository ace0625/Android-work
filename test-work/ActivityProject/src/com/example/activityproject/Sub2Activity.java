package com.example.activityproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Sub2Activity extends Activity {
	EditText et;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			switch(v.getId())
			{
			case R.id.button2:
				finish();
				break;
			}
		}
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    setContentView(R.layout.sub2);
	    
	    findViewById(R.id.button2).setOnClickListener(bHandler);
	    
	    Intent intent = getIntent();
	    String str = intent.getStringExtra("data");
	    
	    int cnt = intent.getIntExtra("cnt", 100);
	   
	    et = (EditText)findViewById(R.id.editText1);
	    et.setText(str + "  " + cnt);
	}
}


