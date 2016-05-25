package com.example.fragmentproject1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment 
{
	Activity activity;
	@Override
	public void onAttach(Activity activity) {
		this.activity = activity;
		
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, //여기서 화면 구성
			Bundle savedInstanceState) {
		View v = null;
		v = inflater.inflate(R.layout.test1, null);
		return v;
	}
	
}
