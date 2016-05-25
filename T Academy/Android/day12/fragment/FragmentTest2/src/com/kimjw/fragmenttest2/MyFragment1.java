package com.kimjw.fragmenttest2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class MyFragment1 extends Fragment {
	EditText etText = null;
	int cnt = 0;
	Activity activity = null;
	View.OnClickListener bHandler = new View.OnClickListener() {
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.button1 :
				cnt++;
				etText.setText("cnt : " + cnt);
				break;
			}			
		}
	};
	@Override
	public void onAttach(Activity activity) {
		Log.v("onAttach", "onAttach");
		this.activity = activity;
		super.onAttach(activity);
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt("cnt", cnt);
		super.onSaveInstanceState(outState);
	}	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = View.inflate(getActivity(), R.layout.frage1, null);
		etText = (EditText)view.findViewById(R.id.editText1);
		view.findViewById(R.id.button1).setOnClickListener(bHandler);
		if(savedInstanceState != null){
			cnt = savedInstanceState.getInt("cnt");
			Log.v("MainActivity", "aaaa cnt : " + cnt);
		}
		etText.setText("cnt : " + cnt);
		return view;
	}

}
