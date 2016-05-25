package com.example.naverproject;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler1 extends DefaultHandler {
	private Channel channel;
	private String tName = "";
	private Mitem item;
	int level = 0;
	
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String str  = new String(ch, start, length);
		if(tName.equals("title"))
		{
			switch(level)
			{
			case 0:
				channel.setTitle(str);
				break;
			case 1:
				item.setTitle(str);
				break;	
			}
		}
		else if(tName.equals("link"))
		{
			switch(level)
			{
			case 0:
				channel.setLink(str);
				break;
			case 1:
				item.setLink(str);
			}
		}
		else if(tName.equals("description"))
		{
			switch(level)
			{
			case 0:
				channel.setDecription(str);
				break;
				
			}
		}
		else if(tName.equals("lastBuildDate"))
		{
			channel.setLastBuildDate(str);
		}
		else if(tName.equals("total"))
		{
			channel.setTotal(str);
		}
		else if(tName.equals("start"))
		{
			channel.setStart(str);
		}
		else if(tName.equals("display"))
		{
			channel.setDisplay(str);
		}
		
		else if(tName.equals("image"))
		{
			item.setImage(str);
		}
		else if(tName.equals("subtitle"))
		{
			item.setSubtitle(str);
		}
		else if(tName.equals("pubDate"))
		{
			item.setPubDate(str);
		}
		else if(tName.equals("director"))
		{
			item.setDirector(str);
		}
		else if(tName.equals("actor"))
		{
			item.setActor(str);
		}
		else if(tName.equals("userRating"))
		{
			item.setUserRating(str);
		}
		
		
		super.characters(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(localName.equals("item"))
		{
			channel.getItemList().add(item);
			level--;
		}
		tName = "";
		super.endElement(uri, localName, qName);
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if(localName.equals("channel"))
		{
			channel = new Channel();
			channel.setItemList(new ArrayList<Mitem>());
		}
		else if (localName.equals("item"))
		{
			item = new Mitem();
			level++;
		}
		tName = localName;
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
	}


}
