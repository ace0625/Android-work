package com.example.assignment2;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {
	
	private ArrayList<Item> data;
	private Item item = null;
	private String tName = "";
	private pList plist;
	int level = 0;
	
	public pList getPlist() {
		return plist;
	}
	public void setPlist(pList plist) {
		this.plist = plist;
	}
//	public MyHandler(ArrayList<Item> data) {
//		this.data = data;
//		data.clear();
//	}
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String str = new String(ch, start, length);
		if(tName.equals("title"))
		{
			item.setTitle(str);
		}
		else if(tName.equals("count"))
		{
			item.setCount(str);
		}
		else if(tName.equals("price"))
		{
			item.setPrice(str);
		}
		else if(tName.equals("image"))
		{
			item.setImage(str);
		}
		else if(tName.equals("category"))
		{
			item.setCategory(str);
		}
		super.characters(ch, start, length);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(localName.equals("category"))
		{
			data.add(item);
			level--;
		}
		tName = "";
		
		super.endElement(uri, localName, qName);
	}
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if(localName.equals("pList"))
		{
			plist = new pList();
			plist.setItemList(new ArrayList<Item>());
		}
		else if (localName.equals("item"))
		{
			item = new Item();
			level++;
		}
		tName = localName;
		super.startElement(uri, localName, qName, attributes);
	}

}
