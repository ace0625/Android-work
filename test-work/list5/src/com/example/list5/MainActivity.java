package com.example.list5;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	EditText et;
	ListView list;
	ArrayList<CharSequence> Items;
	
	ArrayAdapter<CharSequence> Adapter;
	View.OnClickListener bHandler = new View.OnClickListener() 
	{
		public void onClick(View v) 
		{
			switch(v.getId())
			{
				case R.id.button1:
					String text = et.getText().toString();
					if(text.length() != 0)
					{
						Items.get(Adapter(R.array.country));
						Items.add(text);
						
						et.setText("");
						Adapter.notifyDataSetChanged();
					}
					break;
				case R.id.button2:
//					int pos;
//					pos = list.getCheckedItemPositions();
//					if(pos != ListView.INVALID_POSITION)
//					{
//						
//						Adapter.remove(pos);
//						list.clearChoices();
//						Adapter.notifyDataSetChanged();
//					}
					break;
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		
		et = (EditText)findViewById(R.id.editText1);
		
		Adapter = ArrayAdapter.createFromResource(this, R.array.country, android.R.layout.simple_list_item_1);
		Items = new ArrayList<CharSequence>();
		
		
		list = (ListView)findViewById(R.id.listView1);
		list.setAdapter(Adapter);
		list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
	}

	protected int Adapter(int country) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
