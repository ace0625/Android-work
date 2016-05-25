package com.example.xmlparsehomework;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler1 extends DefaultHandler {
	private pList plist;
	private String tName = "";
	private item item;
	int level = 0;
	
	public pList getpList() {
		return plist;
	}

	public void setpList(pList plist) {
		this.plist = plist;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String str  = new String(ch, start, length);
	
		
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
		if(localName.equals("item"))
		{
			plist.getItemList().add(item);
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
			plist.setItemList(new ArrayList<item>());
		}
		else if (localName.equals("item"))
		{
			item = new item();
			level++;
		}
		tName = localName;
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
	}


}
