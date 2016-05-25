package com.tacademy.shread;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MaintActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		String content = "내용부분";
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.putExtra(Intent.EXTRA_SUBJECT, "제목");
		intent.putExtra(Intent.EXTRA_TEXT, content);
		intent.putExtra(Intent.EXTRA_TITLE, "");
		intent.setType("text/plain");    
		MenuItem itme = menu.add(0, 0, 0, "공유");
		itme.setIntent(intent);
		return super.onCreateOptionsMenu(menu);
	}
	
int cnt = 0;

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		cnt++;
		MenuItem item = menu.getItem(0);
		item.getIntent().putExtra(Intent.EXTRA_TEXT, "aaa " +  cnt);
		return super.onPrepareOptionsMenu(menu);
	}



    
}