package org.androidtown.events.multitouch;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.LinearLayout;

/**
 * MultiTouch event processing on ImageView
 *
 * @author Mike
 */
public class SampleMultiTouchActivity extends Activity {

	LinearLayout viewerContainer;
	ImageDisplayView displayView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        init();
    }

    private void init() {
    	viewerContainer = (LinearLayout) findViewById(R.id.viewerContainer);
       	Bitmap sourceBitmap = loadImage();
		if (sourceBitmap != null) {
	        displayView = new ImageDisplayView(this);

	        displayView.setImageData(sourceBitmap);
        	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        			LinearLayout.LayoutParams.FILL_PARENT,
        			LinearLayout.LayoutParams.FILL_PARENT);

	        viewerContainer.addView(displayView, params);
		}
    }

	private Bitmap loadImage() {
		Resources res = getResources();
		Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.beach);

		return bitmap;
	}

}