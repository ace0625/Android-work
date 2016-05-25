package com.example.menuproject;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		registerForContextMenu(findViewById(R.id.button1));
		registerForContextMenu(findViewById(R.id.editText1));
		registerForContextMenu(findViewById(R.id.myView1));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
//		menu.add(0, 1, 0, "설정").setIcon(R.drawable.ic_launcher);
//		menu.add(0, 2, 0, "설정2").setIcon(R.drawable.ic_launcher);
//		menu.add(0, 3, 0, "설정3").setIcon(R.drawable.ic_launcher);
//		menu.add(0, 4, 0, R.string.app_name);
//		
//		SubMenu sub = menu.addSubMenu("서브메뉴");
//		sub.add(0, 5, 0, "서브1");
//		sub.add(0, 6, 0, "서브2");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
//		switch(item.getItemId())
//		{
//		case 1:
//			Toast.makeText(this,  "설정", Toast.LENGTH_SHORT).show();
//			break;
//		case 2:
//			Toast.makeText(this,  "복사", Toast.LENGTH_SHORT).show();
//			break;
//		case 3:
//			Toast.makeText(this,  "삭제", Toast.LENGTH_SHORT).show();
//			break;
//		case 4:
//			Toast.makeText(this,  "MenuProject", Toast.LENGTH_SHORT).show();
//			break;
//		case 5:
//			Toast.makeText(this,  "서브1", Toast.LENGTH_SHORT).show();
//			break;
//		case 6:
//			Toast.makeText(this,  "서브2", Toast.LENGTH_SHORT).show();
//			break;
//		}
		
		switch(item.getItemId())
		{
		case R.id.action_settings:
			Toast.makeText(this,  "설정", Toast.LENGTH_SHORT).show();
			break;
		case R.id.item1:
			Toast.makeText(this,  "복사", Toast.LENGTH_SHORT).show();
			break;
		case R.id.item2:
			Toast.makeText(this,  "삭제", Toast.LENGTH_SHORT).show();
			break;
		case R.id.item3:
			Toast.makeText(this,  "menuproject", Toast.LENGTH_SHORT).show();
			break;
		case R.id.item4:
			Toast.makeText(this,  "서브1", Toast.LENGTH_SHORT).show();
			break;
		case R.id.item5:
			Toast.makeText(this,  "서브2", Toast.LENGTH_SHORT).show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case 1:
			Toast.makeText(this, "버튼활성화1", Toast.LENGTH_SHORT).show();
		case 2:
			Toast.makeText(this, "버튼활성화2", Toast.LENGTH_SHORT).show();
		case 3:
			Toast.makeText(this, "버튼활성화3", Toast.LENGTH_SHORT).show();
		case 4:
			Toast.makeText(this, "버튼활성화4", Toast.LENGTH_SHORT).show();
		case 5:
			Toast.makeText(this, "버튼활성화5", Toast.LENGTH_SHORT).show();
		case 101:
			Toast.makeText(this, "버튼활성화6", Toast.LENGTH_SHORT).show();
		case 102:
			Toast.makeText(this, "버튼활성화7", Toast.LENGTH_SHORT).show();
		case 103:
			Toast.makeText(this, "버튼활성화8", Toast.LENGTH_SHORT).show();
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		switch(v.getId())
		{
		case R.id.button1:
			menu.add(0, 1, 0, "버튼활성화");
			menu.add(0, 2, 0, "버튼비활성화");
			break;
		case R.id.editText1:
			menu.add(0, 3, 0, "마이 복사");
			menu.add(0, 4, 0, "마이 수정");
			menu.add(0, 5, 0, "마이 삭제");
			break;
		}
		super.onCreateContextMenu(menu, v, menuInfo);
	}

}
