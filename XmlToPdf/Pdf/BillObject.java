package Pdf;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

//public class Convertor {
//
//	public static void main(String[] args) throws Exception{
//		
//		
//		BillObject x = new BillObject();
//		x.convert("bill.xml");
//		System.out.println(x.customername);
//		System.out.println(x.billno);
//		System.out.println(x.billDate);
//		System.out.println(x.item);
//		
//		
//		
//	}
//
//}


//interface Convertor{
//	
//	public void convert()
//	
//}



public class BillObject {
	
	String customername;
	String billno;
	String billDate;
	
	HashMap<Integer,ArrayList<String>> item;
	
	public BillObject convert(String filename) throws Exception{
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		dbf.setValidating(true);//you should have a DTD
		dbf.setIgnoringElementContentWhitespace(true);//you should set validation to true
		DocumentBuilder db=dbf.newDocumentBuilder();
		Document doc=db.parse(filename);
		
		this.item = getObject(doc);
		return this;
		
	}
	
	public HashMap<Integer,ArrayList<String>> getObject(Document doc) {
		
		String s = null;
		
		HashMap<Integer,ArrayList<String>> items = new HashMap<Integer,ArrayList<String>>();
		
		customername = doc.getDocumentElement().getChildNodes().item(0).getChildNodes().item(0).getNodeValue();
		billno = doc.getDocumentElement().getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
		billDate = doc.getDocumentElement().getChildNodes().item(2).getChildNodes().item(0).getNodeValue();
		
//		System.out.println(customername);
//		System.out.println(billno);
//		System.out.println(billDate);
		
		int nl = doc.getDocumentElement().getChildNodes().item(3).getChildNodes().getLength();
		NodeList item = doc.getDocumentElement().getChildNodes().item(3).getChildNodes();
		
		for(int i = 0;i<nl;i++) {	
			ArrayList<String> details = new ArrayList<String>();
			for(int j = 0;j<item.item(i).getChildNodes().getLength();j++) {
				 s = item.item(i).getChildNodes().item(j).getChildNodes().item(0).getNodeValue();
				 details.add(s);
			}
			items.put(i, details);
		}
		System.out.println(items);
		return (items);
		
	}
}