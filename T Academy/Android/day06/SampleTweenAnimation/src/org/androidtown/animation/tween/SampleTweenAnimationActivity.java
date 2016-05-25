package org.androidtown.animation.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

/**
 * Tween Animation
 *
 * @author Mike
 */
public class SampleTweenAnimationActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button scaleBtn = (Button) findViewById(R.id.scaleBtn);
        scaleBtn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
        		v.startAnimation(anim);
        	}
        });

        Button scale2Btn = (Button) findViewById(R.id.scale2Btn);
        scale2Btn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale2);
        		v.startAnimation(anim);
        	}
        });

    }

}