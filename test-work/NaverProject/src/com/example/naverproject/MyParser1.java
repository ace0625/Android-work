package com.example.naverproject;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.util.Log;

public class MyParser1 {
	private static final String TAG = "MainActivity";
	public static Channel parse(InputStream is)
	{
		SAXParserFactory sFactory = null;
		SAXParser parser = null;
		MyHandler1 handler = null;
		Channel channel = null;
		try {
			sFactory = SAXParserFactory.newInstance();
			parser = sFactory.newSAXParser();
			handler = new MyHandler1();
			parser.parse(is, handler);
			
			channel = handler.getChannel();
			Log.v(TAG, "parse success");
		} catch (Exception e) {
			Log.v(TAG,"parser error : " + e);
		}
		
		return channel;
	}
}
