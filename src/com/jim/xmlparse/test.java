package com.jim.xmlparse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jim.util.AppendToFile;

public class test {

    public static void main(String[] args) {

	// File input = new File("d:\\58giant.txt");
	Document doc = null;
	// doc = Jsoup.parse(input, "UTF-8");
	// String response =
	// HttpClient.getResponse("http://hz.58.com/zixingche/?minprice=0_1000&key=%2525u6377%2525u5B89%2525u7279&sourcetype=5");
	// System.out.println(response);
	URL url = null;
	try {
	    url = new URL("http://hz.58.com/zixingche/?minprice=0_1000&key=%2525u6377%2525u5B89%2525u7279&sourcetype=5");
	} catch (MalformedURLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	try {
	    doc = Jsoup.parse(url, 3000);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	Elements links = doc.getElementsByTag("tr");
	String strToFile = "";
	for (Element link1 : links) {
	    String s = "";
	    double price = 0;
	    Elements linktds = link1.getElementsByTag("td");
	    for (Element link : linktds) {

		// System.out.println(link.attr("class"));
		if ("t".equals(link.attr("class"))) {

		    String linkText = link.text();
		    if (linkText.length() > 0)
			s += linkText + "\n";
		    // System.out.println(linkText);

		    // 提取td中的a标签中的href
		    Elements tdlinks = link.getElementsByTag("a");
		    if (tdlinks.size() > 0) {
			String linkHref = tdlinks.first().attr("href");
			if (linkHref.length() > 0)
			    s += linkHref + "\n";
			// System.out.println(linkHref);
		    }
		}
		if ("tc".equals(link.attr("class"))) {
		    if (link.getElementsByTag("b").size() > 0) {
			String strTmp1 = link.getElementsByTag("b").first().text();
			System.out.println(strTmp1);
			price = Double.valueOf(strTmp1);
			s += strTmp1 + "\n";
		    }
		}
	    }
	    System.out.println("------------------------------\n");
	    if (price < 1000000) {
		strToFile += s;
		strToFile += "------------------------------\n";
	    }
	}
	String fileName = "d:\\58giantresult.txt";
	AppendToFile.appendMethodC(fileName, strToFile, false);

    }
}
