package com.example.list2project;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	EditText et;
	ListView list = null;
	ArrayList<String> data = new ArrayList<String>();
	ArrayAdapter<String> adapter = null;
	View.OnClickListener bHander = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				//로직
				String str = et.getText().toString();
				data.add(str);
				//UI변경
				adapter.notifyDataSetChanged();
				break;
			case R.id.button2:
				int position = list.getCheckedItemPosition();
				if(position != ListView.INVALID_POSITION)
				{
					data.remove(position);
					
					list.clearChoices();
					adapter.notifyDataSetChanged();
				}
				break;
			}
		}
	};
	
	AdapterView.OnItemClickListener iHandler = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position,
				long id) {
			Toast.makeText(MainActivity.this, data.get(position), Toast.LENGTH_SHORT).show();
		}
	
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.button1).setOnClickListener(bHander);
		findViewById(R.id.button2).setOnClickListener(bHander);
		list = (ListView)findViewById(R.id.listView1);
		et = (EditText)findViewById(R.id.editText1);
		
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice, data);
		list.setAdapter(adapter);
		
		list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		list.setOnItemClickListener(iHandler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
