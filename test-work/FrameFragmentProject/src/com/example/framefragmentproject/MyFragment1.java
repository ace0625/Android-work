package com.example.framefragmentproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.EditText;

public class MyFragment1 extends Fragment {
	Activity activity;
	@Override
	public void onAttach(Activity activity) {
		this.activity = activity;
		super.onAttach(activity);
	}

	EditText et;
	int cnt = 0;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			cnt++;
			et.setText("cnt: " +cnt);
			
		}
	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = null;
		v = inflater.inflate(R.layout.frame1,null);
		
		et = (EditText)v.findViewById(R.id.editText1);
		v.findViewById(R.id.button1).setOnClickListener(bHandler);
		return v;
	}
	

}
