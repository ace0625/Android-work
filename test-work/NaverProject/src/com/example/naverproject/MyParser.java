package com.example.naverproject;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.util.Log;

public class MyParser {
	private static final String TAG = "MainActivity";
	public static void parse(InputStream is, ArrayList<Item> data)
	{
		SAXParserFactory sFactory = null;
		SAXParser parser = null;
		MyHandler handler = null;
		try {
			sFactory = SAXParserFactory.newInstance();
			parser = sFactory.newSAXParser();
			handler = new MyHandler(data);
			parser.parse(is, handler);
			Log.v(TAG, "parse success");
		} catch (Exception e) {
			Log.v(TAG,"parser error : " + e);
		}
	}
}
