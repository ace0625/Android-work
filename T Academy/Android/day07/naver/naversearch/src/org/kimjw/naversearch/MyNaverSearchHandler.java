package org.kimjw.naversearch;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class MyNaverSearchHandler extends DefaultHandler {
	ArrayList<MyItem> data;
	MyItem item;
	String tName;
	
	public ArrayList<MyItem> getData(){
		return data;
	}
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(tName.equals("K")){
			item.setK(new String(ch, start, length));
		}else if(tName.equals("S")){
			item.setS(new String(ch, start, length));
		}else if(tName.equals("V")){
			item.setV(new String(ch, start, length));
		}
	}
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(tName.equals("V")){
			data.add(item);
		}
		tName = "";
	}
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if(localName.equals("result")){
			data = new ArrayList<MyItem>();
		}else if(localName.equals("K")){
			item = new MyItem();			
		}
		tName = localName;
	}
}
