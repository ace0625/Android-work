package com.example.xmlparsehomework;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.util.Log;

public class MyParser1 {
	private static final String TAG = "MainActivity";
	public static pList parse(InputStream is)
	{
		SAXParserFactory sFactory = null;
		SAXParser parser = null;
		MyHandler1 handler = null;
		pList plist = null;
		try {
			sFactory = SAXParserFactory.newInstance();
			parser = sFactory.newSAXParser();
			handler = new MyHandler1();
			parser.parse(is, handler);
			
			plist = handler.getpList();
			Log.v(TAG, "parse success");
		} catch (Exception e) {
			Log.v(TAG,"parser error : " + e);
		}
		
		return plist;
	}
}
