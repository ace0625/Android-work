package org.kimjw.navermovie;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.xml.sax.InputSource;

import android.util.Log;

public class MyMovieParser {
	private static final String TAG = "MainActivity";
	static SAXParserFactory sFactory;
	static SAXParser parser;
	public static Channel parser(HttpEntity entity){
		Channel data = null;
		InputSource is = null;
		MyMovieHandler handler = new MyMovieHandler();
		try{
			sFactory = SAXParserFactory.newInstance();
			parser = sFactory.newSAXParser();
			is = new InputSource(entity.getContent());
			parser.parse(is, handler);
			data = handler.getData();
		}catch(Exception e){
			Log.v(TAG, "parser error : " + e);
		}
		return data;
	}
}
