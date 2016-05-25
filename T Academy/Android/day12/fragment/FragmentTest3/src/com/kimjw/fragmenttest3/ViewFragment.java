package com.kimjw.fragmenttest3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewFragment extends Fragment {
	private static final String TAG = "MainActivity";
	int[] imgs = {R.drawable.dream01, R.drawable.dream02, R.drawable.dream03};
	String[] str = null;
	public ViewFragment() {		
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		str = getActivity().getResources().getStringArray(R.array.frageTitle);
		return inflater.inflate(R.layout.fragitem, container, false);
	}
	
	public void setChage(int index){
		View view = getView();
		Log.v(TAG, "view1 : " + view);
		TextView tv = null;
		ImageView img = null;
		if(view != null){
			tv = (TextView)view.findViewById(R.id.txtTitle);
			tv.setText(str[index]);
			img = (ImageView)view.findViewById(R.id.imgView);
			img.setImageResource(imgs[index]);
		}
	}
}
