package com.example.list3project;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
//	ListView list;
	GridView list;
	
	
	ArrayList<MyItem> data = new ArrayList<MyItem>();
	MyItem item = null;
	
	MyAdapter adapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		list = (ListView)findViewById(R.id.listView1);
		list = (GridView)findViewById(R.id.gridView1);
		
		item = new MyItem();
		item.setImageRes(R.drawable.hp);
		item.setName("hp notebook");
		data.add(item);
		
		data.add(new MyItem(R.drawable.sam, "Samsung Notebook"));
		data.add(new MyItem(R.drawable.lg, "LG Notebook"));
		data.add(new MyItem(R.drawable.lg1, "LG1 Notebook"));
		data.add(new MyItem(R.drawable.hp, "hp Notebook"));
		data.add(new MyItem(R.drawable.sam, "Samsung Notebook"));
		data.add(new MyItem(R.drawable.lg, "LG Notebook"));
		data.add(new MyItem(R.drawable.lg1, "LG1 Notebook"));
		data.add(new MyItem(R.drawable.hp, "hp Notebook"));
		data.add(new MyItem(R.drawable.sam, "Samsung Notebook"));
		data.add(new MyItem(R.drawable.lg, "LG Notebook"));
		data.add(new MyItem(R.drawable.lg1, "LG1 Notebook"));
		data.add(new MyItem(R.drawable.hp, "hp Notebook"));
		data.add(new MyItem(R.drawable.sam, "Samsung Notebook"));
		data.add(new MyItem(R.drawable.lg, "LG Notebook"));
		data.add(new MyItem(R.drawable.lg1, "LG1 Notebook"));
		data.add(new MyItem(R.drawable.hp, "hp Notebook"));
		data.add(new MyItem(R.drawable.sam, "Samsung Notebook"));
		data.add(new MyItem(R.drawable.lg, "LG Notebook"));
		data.add(new MyItem(R.drawable.lg1, "LG1 Notebook"));
		data.add(new MyItem(R.drawable.hp, "hp Notebook"));
		
		adapter = new MyAdapter(this, R.layout.item, data);
		list.setAdapter(adapter);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
