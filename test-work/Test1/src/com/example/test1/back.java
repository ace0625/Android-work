package com.example.test1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class back extends Activity 
{
	View.OnClickListener bHandler = new View.OnClickListener() 
	{
		
		@Override
		public void onClick(View v) 
		{
			Intent intent;
			switch(v.getId())
			{
			case R.id.button1:
				intent = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(intent);
				break;
			}
		}
	};
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.back);
	    
	    findViewById(R.id.button1).setOnClickListener(bHandler);
	    
	
	    // TODO Auto-generated method stub
	}



}