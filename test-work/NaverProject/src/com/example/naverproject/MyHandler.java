package com.example.naverproject;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {
	ArrayList<Item> data;
	Item item = null;
	String tName = "";
	
	public MyHandler(ArrayList<Item> data)
	{
		this.data = data;
		data.clear();
	}
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String str = new String(ch, start, length);
		if(tName.equals("K"))
		{
			item.setK(str);
		}
		else if(tName.equals("S"))
		{
			item.setS(str);
		}
		else if(tName.equals("V"))
		{
			item.setV(str);
		}
		super.characters(ch, start, length);
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(localName.equals("V"))
		{
			data.add(item);
		}
		tName = "";
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if(localName.equals("K"))
		{
			item = new Item();
		}
		else if(localName.equals("local"))
		{
			String stn_id = attributes.getValue("stn_id");
		}
		
		
		tName = localName;
		super.startElement(uri, localName, qName, attributes);
	}
	
}
