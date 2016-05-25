package com.kimjw.fragmenttest3;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TitlesFragment extends ListFragment {
	private static final String TAG = "MainActivity";
	int index;
	private ListItemSelectionListener listener = null;
	Activity activity = null;
	public TitlesFragment() {
	}

	public interface ListItemSelectionListener{
		public void onListItemSelected(int index);
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		index = position;
		listener.onListItemSelected(position);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		// ListFlagment data setting
		setListAdapter(ArrayAdapter.createFromResource(getActivity(), R.array.frageTitle, android.R.layout.simple_list_item_1) );
		if(savedInstanceState != null){
			index = savedInstanceState.getInt("index", 0);
			listener.onListItemSelected(index);
		}
		super.onActivityCreated(savedInstanceState);
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt("index", index);
		super.onSaveInstanceState(outState);
	}
	@Override
	public void onAttach(Activity activity) {
		this.activity = activity;
		try{
			listener = (ListItemSelectionListener)activity;
		}catch(Exception e){
			Log.v(TAG, "casting exception : " + e);
		}
		super.onAttach(activity);
	}
}
