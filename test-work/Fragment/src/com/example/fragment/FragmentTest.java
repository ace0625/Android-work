package com.example.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentTest extends Fragment {
	private static final String TAG = "MainActivity";
//	View.OnClickListener bHandler = new View.OnClickListener() {
//		
//		@Override
//		public void onClick(View v) {
//			switch (v.getId())
//			{
//			case R.id.button1:
//				break;
//			}
//		}
//	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
}
