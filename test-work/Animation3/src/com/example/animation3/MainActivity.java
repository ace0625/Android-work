package com.example.animation3;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends Activity {
	ImageView img;
	CheckBox cb, ca, crp, crv;
	RadioGroup rg;
	Spinner sp;
	View.OnClickListener bHandler = new View.OnClickListener() 
	{
		public void onClick(View v) 
		{
			switch(v.getId())
			{
			case R.id.btnstart:
				TranslateAnimation trans = new TranslateAnimation(0, 250, 0, 0);
				trans.setDuration(2000);
				trans.setFillBefore(cb.isChecked());
				trans.setFillAfter(ca.isChecked());
				if(crp.isChecked())
				{
					trans.setRepeatCount(1);
					if(crv.isChecked())
					{
						trans.setRepeatMode(Animation.REVERSE);
					}
				}
				switch(sp.getSelectedItemPosition())
				{
				case 0:
					trans.setInterpolator(new LinearInterpolator());
					break;
				case 1:
					trans.setInterpolator(new AccelerateInterpolator());
					break;
				case 2:
					trans.setInterpolator(new DecelerateInterpolator());
					break;
				case 3:
					trans.setInterpolator(new AccelerateDecelerateInterpolator());
					break;
				case 4:
					trans.setInterpolator(new AnticipateInterpolator());
					break;
				case 5:
					trans.setInterpolator(new BounceInterpolator());
					break;
				case 6:
					trans.setInterpolator(new CycleInterpolator(0.5f));
					break;
				case 7:
					trans.setInterpolator(new OvershootInterpolator());
					break;
				case 8:
					trans.setInterpolator(new AnticipateOvershootInterpolator());
					break;
				}
				img.startAnimation(trans);
				break;
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		img = (ImageView)findViewById(R.id.imageView1);
		cb = (CheckBox)findViewById(R.id.btnfillbefore);
		ca = (CheckBox)findViewById(R.id.btnfillafter);
		crp = (CheckBox)findViewById(R.id.btnrepeat);
		crv = (CheckBox)findViewById(R.id.btnreverse);
		
		sp = (Spinner)findViewById(R.id.spininter);
		sp.setPrompt("Select Interpolator");
		
		ArrayAdapter<CharSequence> aspin = ArrayAdapter.createFromResource(this, R., android.R.layout.simple_spinner_item);
		aspin.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		sp.setAdapter(aspin);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
