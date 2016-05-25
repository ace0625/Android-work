package com.example.corsorproject;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyHelper extends SQLiteOpenHelper {
	private static final String TAG = "MainActivity";
	Context context;
	
	public MyHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		this.context = context;
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) {
		Log.v(TAG, "DB open");
		super.onOpen(db);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.v(TAG, "DB onCreate");
		String sql = "create table tMember(" +
				"_id integer primary key autoincrement," +
				"fName text, lName text, age integer, phoneNumber text, bigo integer);";
		
		try {
			db.execSQL(sql);
			db.execSQL("insert into tMember(fName, lName, age, phoneNumber, bigo) values('hyunchan','kim', 26, '123456', 0 );");
			db.execSQL("insert into tMember(fName, lName, age, phoneNumber, bigo) values('sungil','jeong', 36, '123456', 1 );");
			db.execSQL("insert into tMember(fName, lName, age, phoneNumber, bigo) values('kwanghyun','kwang', 30, '123456', 2 );");
			db.execSQL("insert into tMember(fName, lName, age, phoneNumber, bigo) values('hyunji','kim', 20, '123456', 1 );");
			db.execSQL("insert into tMember(fName, lName, age, phoneNumber, bigo) values('hyunji','kim', 40, '123456', 1 );");
			db.execSQL("insert into tMember(fName, lName, age, phoneNumber, bigo) values('hyunji','kim', 30, '123456', 2 );");
			Log.v(TAG, "success");
		} catch (SQLException e) {
			Log.v(TAG, "create error: " + e);
		}
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.v(TAG, String.format("update oldversion %d newversion %d ", oldVersion,newVersion));
		
		try {
			db.execSQL("drop table if exists tMember ");
			onCreate(db);
		} catch (SQLException e) {
		}
	}
	

}
