package XmlToExcel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlSaxParser {
	
	public static HashMap<Integer,ArrayList<Object>> getObj(String s) throws Exception{
		SAXParserFactory spf =  SAXParserFactory.newDefaultInstance();
		SAXParser sp = spf.newSAXParser();
		MyXMLHandler han = new MyXMLHandler();
		sp.parse(s+".xml",han);
		
		
		return han.getObj();
		
	}
	
}


class MyXMLHandler extends DefaultHandler implements Cloneable{
	
	private static MyXMLHandler myHandle;
	
	
	
	public HashMap<Integer,ArrayList<Object>> map=new HashMap<Integer,ArrayList<Object>>();
	ArrayList<Object> list = null;
	
	int flag = 0;
	int i = 0;
	String attr = null;
	
	
	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("starts");
	}
	
	
	public  MyXMLHandler getclone() throws Exception{
		return (MyXMLHandler)super.clone();
	}
	
	public static MyXMLHandler createObject() throws Exception {
		if(myHandle==null) {
			myHandle=new MyXMLHandler();
			MyXMLHandler obj= myHandle.getclone();
			return obj;
		}
		else {
			return myHandle.getclone();
		}
	}
	
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println("ends");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if(qName == "item") {
			list = new  ArrayList<Object>();
		}
		attr = qName;
		
		
		//System.out.println("<"+qName+">");
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName == "item")
			map.put(i++, list);
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		String s=new String(ch,start,length);
		if(list == null) {
			map.put(i++, new ArrayList<Object>(Arrays.asList(s)));
		}else {
		
		list.add(s);
		}
		
		System.out.println(s+" "+attr);
		System.out.println(map);
	}
	
	public HashMap<Integer,ArrayList<Object>> getObj() {
		return(map);
	}
}


