package org.kimjw.navermovie;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class MyMovieHandler extends DefaultHandler {

	private Channel data;
	private MyItem item;
	private String tName;
	boolean flag = false;
	public Channel getData(){
		return data;
	}
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		String str = new String(ch, start, length);
		Log.v("Handler", "str : " + str);
		if(tName.equals("title")){
			if(flag){
				item.setTitle(str);
			}else{
				data.setTitle(str);
			}
		}else if(tName.equals("link")){
			if(flag){
				item.setLink(str);
			}else{
				data.setLink(str);
			}
		}else if(tName.equals("description")){
			data.setDesc(str);
		}else if(tName.equals("lastBuildDate")){
			data.setLastBuildDate(str);
		}else if(tName.equals("total")){
			data.setTotal(str);
		}else if(tName.equals("start")){
			data.setStart(str);
		}else if(tName.equals("display")){
			data.setDisp(str);
		}else if(tName.equals("image")){
			item.setImage(str);
		}else if(tName.equals("subtitle")){
			item.setSubTitle(str);
		}else if(tName.equals("pubDate")){
			item.setPubDate(str);
		}else if(tName.equals("director")){
			item.setDirector(str);
		}else if(tName.equals("actor")){
			item.setActor(str);
		}else if(tName.equals("userRating")){
			item.setUserRating(str);
		}
	}
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		if(localName.equals("item")){
			data.getData().add(item);
			Log.v("Handler", "item 종료 " );
		}
		tName = "";
	}
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(localName.equals("channel")){
			data = new Channel();
			flag = false;
		}else if(localName.equals("item")){
			flag = true;
			item = new MyItem();
			Log.v("Handler", "item 시작 " );
		}
		tName = localName;
	}
}
