package org.androidtown.animation.graph;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Graph Animation
 *
 * @author Mike
 */
public class SampleGraphAnimationActivity extends Activity {

	LinearLayout mainLayout;
    Resources res;
    Animation growAnim;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        res = getResources();
        growAnim = AnimationUtils.loadAnimation(this, R.anim.grow);
        mainLayout = (LinearLayout)findViewById(R.id.mainLayout);

        addItem("Apple", 80);
        addItem("Orange", 100);
        addItem("Kiwi", 40);

    }

    private void addItem(String name, int value) {

        LinearLayout itemLayout = new LinearLayout(this);
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.WRAP_CONTENT,
        		LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.WRAP_CONTENT,
        		LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.WRAP_CONTENT,
        		LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView textView = new TextView(this);
        textView.setText(name);
        params.width = 80;
        params.setMargins(0, 4, 0, 4);
        itemLayout.addView(textView, params);

        ProgressBar proBar = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        proBar.setIndeterminate(false);
        proBar.setMax(100);
        proBar.setProgress(100);
        proBar.setAnimation(growAnim);
        params2.height = 20;
        params2.width = value * 2;
        params2.gravity = Gravity.LEFT;
        itemLayout.addView(proBar, params2);

        mainLayout.addView(itemLayout, params3);

    }

    public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		Toast.makeText(getApplicationContext(), "onWindowFocusChanged : " + hasFocus, 2000).show();

		if (hasFocus) {
	    	growAnim.start();
		} else {
			growAnim.reset();
		}
	}

}