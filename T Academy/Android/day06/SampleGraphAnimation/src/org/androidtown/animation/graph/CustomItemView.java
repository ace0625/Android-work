package org.androidtown.animation.graph;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class CustomItemView extends LinearLayout {

	/**
	 * Icon
	 */
	private ImageView gIcon;

	/**
	 * TextView 01
	 */
	private TextView gText01;

	/**
	 * TextView 02
	 */
	private TextView gText02;

	/**
	 * ProgressBar
	 */
	public ProgressBar gProBar;




	public CustomItemView(Context context, CustomItem aItem) {
		super(context);

		// Layout Inflation
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.customlistitem, this, true);

		// Set Icon
		gIcon = (ImageView) findViewById(R.id.iconItem);
		gIcon.setImageDrawable(aItem.getIcon());

		// Set Text 01 to the product name
		gText01 = (TextView) findViewById(R.id.dataItem01);
		gText01.setText(aItem.getName());

		// Set Text 02 to the remaining time and start countdown;
		gText02 = (TextView) findViewById(R.id.dataItem02);

		long initialDate = aItem.getInitialDate();
		gText02.setText(initialDate + " %");

		gProBar = (ProgressBar) findViewById(R.id.progressBar01);
		//gProBar.setProgress((int)initialDate);
		gProBar.setProgress(100);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		params.width = (int)initialDate;

		gProBar.setLayoutParams(params);



		//gProBar.setAnimation(CustomItemListAdapter.growAnim);


	}

	boolean animated = false;

	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		if (!animated) {
			gProBar.startAnimation(CustomItemListAdapter.growAnim);
			Toast.makeText(getContext(), "onWindowFocusChanged called.", 1000).show();
			animated = true;
		}

	}


	/**
	 * set Text
	 *
	 * @param data
	 */
	public void setText(String data) {
			gText01.setText(data);

	}

	/**
	 * set Icon
	 *
	 * @param icon
	 */
	public void setIcon(Drawable icon) {
		gIcon.setImageDrawable(icon);
	}

	/**
	 * set Long
	 */
	public void setExpired(Long date){

	}

	/**
	 * set Long
	 */
	public void setInital (Long date){

	}

}

