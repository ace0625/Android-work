package com.example.corsorproject;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	ListView list;
	Cursor c;
	MyHelper helper = null;
	SQLiteDatabase db = null;
//	SimpleCursorAdapter adapter = null;
//	myCursorAdapter adapter = null;
	MyAdapter adapter = null;
	ArrayList<MemberVO> data = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		list = (ListView)findViewById(R.id.listView1);
		
		helper  = new MyHelper(this, "sam.db", null, 1);
		db = helper.getReadableDatabase();
		
		db.execSQL("insert into aaa(aa, bb, cc) values(?,?,?)",
				new String[]{"가", "ㅁㅁ", 123 + ""});
		
		data = new ArrayList<MemberVO>();
		try {
			
			c = db.query("tMember", null, null, null, null, null, null, "0,10");
			MemberVO member = null;
			while(c.moveToNext())
			{
				member  = new MemberVO();
				member.set_id(c.getInt(0));
				member.setfName(c.getString(1));
				member.setlName(c.getString(2));
				member.setAge(c.getInt(3));
				member.setPhoneNumber(c.getString(4));
				member.setBigo(c.getInt(5));
				data.add(member);
			}
			c.close();
		} catch (SQLException e) {
			Log.v(TAG, "loading error : " + e);
		}
		
		adapter = new MyAdapter(this, R.layout.item1, data);
//		startManagingCursor(c);//계속 물고 있어야함
//		adapter = new myCursorAdapter(this, c, R.layout.item1);
		
//		adapter = new SimpleCursorAdapter(this, R.layout.item, c, new String[]{"fName", "age", "bigo"}, //컬럼갯수와 아이디 갯수 일치해야함 
//				new int[]{R.id.textView1, R.id.textView2, R.id.textView3}, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		
//		adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, c, new String[]{"fName", "age"}, 
//				new int[]{android.R.id.text1, android.R.id.text2}, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		list.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
