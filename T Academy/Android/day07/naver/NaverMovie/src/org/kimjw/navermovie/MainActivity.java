package org.kimjw.navermovie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	private static final String TAG = "MainActivity";
	EditText et;
	Spinner sp;
	View.OnClickListener bHandler = new View.OnClickListener(){
		public void onClick(View view) {
			Intent intent = new Intent(MainActivity.this, SubListActivity.class);
			intent.putExtra("key", et.getText().toString());
			intent.putExtra("genre", sp.getSelectedItemId() + "");
			startActivity(intent);
		}		
	};
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        et = (EditText)findViewById(R.id.etkey);
        sp = (Spinner)findViewById(R.id.spgenre);
        sp.setSelection(-1, true);
        findViewById(R.id.button1).setOnClickListener(bHandler);
    }
}