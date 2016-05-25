package com.example.webproject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	WebView view = null;
	EditText et;
	String data = "<html><body>tacademy<br><font size=6>And</font>roid<body></html>";
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				view.loadUrl(et.getText().toString());
				break;
			case R.id.button2:
				if(view.canGoBack())
					view.goBack();
				break;
			case R.id.button3:
				if(view.canGoForward())
					view.goForward();
				break;
			case R.id.button4:
				view.loadData(data, "text/html", "UFT-8");
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
		findViewById(R.id.button3).setOnClickListener(bHandler);
		findViewById(R.id.button4).setOnClickListener(bHandler);
		
		et = (EditText)findViewById(R.id.editText1);
		view = (WebView)findViewById(R.id.webView1);
		
		WebSettings settings = view.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setBuiltInZoomControls(true);
		
		view.setWebViewClient(new MyWebViewClient());
		view.loadUrl("http://m.daum.net");
		
	}
	
	class MyWebViewClient extends WebViewClient
	{

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
