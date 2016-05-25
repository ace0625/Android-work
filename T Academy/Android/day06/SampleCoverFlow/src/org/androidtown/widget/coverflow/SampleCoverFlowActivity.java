package org.androidtown.widget.coverflow;

import java.io.FileInputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * CoverFlow using Camera
 *
 * @author Mike
 */
public class SampleCoverFlowActivity extends Activity {

	/**
	 * Spacing of this Gallery
	 */
	public static int spacing = -45;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


		CoverFlow coverFlow = (CoverFlow) findViewById(R.id.coverflow);
		ImageAdapter coverImageAdapter = new ImageAdapter(this);
		coverFlow.setAdapter(coverImageAdapter);

		coverFlow.setSpacing(spacing);
		coverFlow.setSelection(2, true);
		coverFlow.setAnimationDuration(3000);

	}

	public class ImageAdapter extends BaseAdapter {

		int itemBackground;
		private Context mContext;
		private FileInputStream outstream;

		private Integer[] mImageIds = { R.drawable.item01, R.drawable.item02,
				R.drawable.item03, R.drawable.item04, R.drawable.item05 };

		private ImageView[] mImages;

		public ImageAdapter(Context c) {
			mContext = c;
			mImages = new ImageView[mImageIds.length];
		}

		public int getCount() {
			return mImageIds.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			ImageView i = new ImageView(mContext);
			i.setImageResource(mImageIds[position]);
			i.setLayoutParams(new CoverFlow.LayoutParams(300, 140));
			i.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

			BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
			drawable.setAntiAlias(true);

			return i;
		}

		public float getScale(boolean focused, int offset) {
			return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
		}

	}
}
