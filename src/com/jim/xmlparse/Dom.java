package com.jim.xmlparse;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author hongliang.dinghl DOM生成与解析XML文档 <?xml version="1.0" encoding="UTF-8"
 *         standalone="no"?> <gpx version="1.1" creator="GPX Editor 1.3.77.1486"
 *         xmlns="http://www.topografix.com/GPX/1/1"
 *         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 *         xsi:schemaLocation=
 *         "http://www.topografix.com/GPX/1/1 http://www.topografix.com/GPX/1/1/gpx.xsd"
 *         > <trk> <name>行者骑行软件</name> <trkseg> <trkpt lat="29.865725"
 *         lon="118.613481"> <time>2014-08-13T11:20:04Z</time> </trkpt> <trkpt
 *         lat="29.865996" lon="118.612652"> <time>2014-08-13T11:20:05Z</time>
 *         </trkpt>
 */
public class Dom {
    private Document document;
    private String fileName;

    public void init() {
	try {
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    this.document = builder.newDocument();
	} catch (ParserConfigurationException e) {
	    System.out.println(e.getMessage());
	}
    }

    public void createXml(String fileName) {
	Element root = this.document.createElement("employees");
	this.document.appendChild(root);
	Element employee = this.document.createElement("employee");
	Element name = this.document.createElement("name");
	name.appendChild(this.document.createTextNode("丁宏亮"));
	employee.appendChild(name);
	Element sex = this.document.createElement("sex");
	sex.appendChild(this.document.createTextNode("m"));
	employee.appendChild(sex);
	Element age = this.document.createElement("age");
	age.appendChild(this.document.createTextNode("30"));
	employee.appendChild(age);
	root.appendChild(employee);
	TransformerFactory tf = TransformerFactory.newInstance();
	try {
	    Transformer transformer = tf.newTransformer();
	    DOMSource source = new DOMSource(document);
	    transformer.setOutputProperty(OutputKeys.ENCODING, "gb2312");
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
	    StreamResult result = new StreamResult(pw);
	    transformer.transform(source, result);
	    System.out.println("生成XML文件成功!");
	} catch (TransformerConfigurationException e) {
	    System.out.println(e.getMessage());
	} catch (IllegalArgumentException e) {
	    System.out.println(e.getMessage());
	} catch (FileNotFoundException e) {
	    System.out.println(e.getMessage());
	} catch (TransformerException e) {
	    System.out.println(e.getMessage());
	}
    }

    public void parserXml(String fileName) {
	try {
	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    Document document = db.parse(fileName);
	    NodeList employees = document.getChildNodes();
	    Node gpx = employees.item(0);
	    System.out.println(gpx);
	    NodeList trks = gpx.getChildNodes();
	    Node trk = trks.item(1);
	    System.out.println(trk);
	    NodeList trksegs = trk.getChildNodes();
	    Node trkseg = trksegs.item(3);
	    System.out.println(trkseg);
	    NodeList trkpts = trkseg.getChildNodes();

	    for (int i = 0; i < trkpts.getLength(); i++) {
		Node trkpt = trkpts.item(i);
		if (trkpt.getNodeType() == 1) {
		    Element element = (Element) trkpt;
		    // System.out.println(trkpt.getNodeName());
		    System.out.println(element.getAttribute("lat"));
		    // System.out.println(element.getAttribute("lon"));
		    // System.out.println(trkpt.getChildNodes().item(1).getTextContent());
		}
	    }

	    System.out.println("解析完毕");
	} catch (FileNotFoundException e) {
	    System.out.println(e.getMessage());
	} catch (ParserConfigurationException e) {
	    System.out.println(e.getMessage());
	} catch (SAXException e) {
	    System.out.println(e.getMessage());
	} catch (IOException e) {
	    System.out.println(e.getMessage());
	}
    }

    public static void main(String[] args) {
	Dom dom = new Dom();
	dom.init();
	dom.parserXml("d:/lushu-413251.gpx");
    }
}
