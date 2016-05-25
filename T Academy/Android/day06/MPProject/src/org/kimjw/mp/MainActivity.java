package org.kimjw.mp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
    Animation aniTop, aniBotton, aniTop1, aniBotton1;
    LinearLayout l1, l2, lMain;
    RelativeLayout menuTap = null;
    
    boolean flag = false;
    View.OnTouchListener tHandler = new View.OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction()){
			case MotionEvent.ACTION_DOWN :
				flag = !flag;
				switch(v.getId()){
				case R.id.lmain :
					Log.v(TAG, "aaa");
					if(flag){
						l1.startAnimation(aniTop);
						l2.startAnimation(aniTop1);
						menuTap.setVisibility(View.VISIBLE);
					}else{
						
						l1.startAnimation(aniBotton);
						l2.startAnimation(aniBotton1);
						menuTap.setVisibility(View.VISIBLE);
					}
					break;
				}
				break;
			}
			
			return true;
		}
	};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        aniTop = AnimationUtils.loadAnimation(this, R.anim.anitop);
        aniBotton = AnimationUtils.loadAnimation(this, R.anim.anibottom);
        aniTop1 = AnimationUtils.loadAnimation(this, R.anim.anitop1);
        aniBotton1 = AnimationUtils.loadAnimation(this, R.anim.anibottom1);
        
        l1 = (LinearLayout)findViewById(R.id.ll1);
        l2 = (LinearLayout)findViewById(R.id.ll2);
        
        lMain = (LinearLayout)findViewById(R.id.lmain);
        lMain.setOnTouchListener(tHandler);
        
        menuTap = (RelativeLayout)findViewById(R.id.menutab);
        init();
        
    }
    void init(){
    	menuTap.setVisibility(View.INVISIBLE);
    	
    }
}



