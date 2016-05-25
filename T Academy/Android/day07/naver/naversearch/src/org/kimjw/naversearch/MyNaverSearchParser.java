package org.kimjw.naversearch;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.xml.sax.InputSource;

import android.util.Log;

public class MyNaverSearchParser {
	private static final String TAG = "MainActivity";
	static SAXParserFactory sFactory;
	static SAXParser parser;
	
	public static ArrayList<MyItem> parser(HttpEntity entity){
		ArrayList<MyItem> data = null;
		InputStream is;
		MyNaverSearchHandler handler = null;
		try{
			sFactory = SAXParserFactory.newInstance();
			parser = sFactory.newSAXParser();
			is = entity.getContent();
			handler = new MyNaverSearchHandler();
			parser.parse(new InputSource(is), handler);
			data = handler.getData();
			Log.v(TAG, "size : " + data.size());
		}catch(Exception e){
			Log.v(TAG, "parser error " + e);
		}		
		return data;
	}
}
