package com.jim.mypush;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jim.httpclient.HttpClient;
import com.jim.mypush.DataBase.info;
import com.jim.util.AppendToFile;

public class GuardianProcess {
    public static void main(String[] agrs) {
	while (true) {
	    new DataBase();// 初始化请求url
	    getInfo();// 获取满足条件的商品(第一页)
	    Mail.send();// 发邮件
	    DataBase.beginLog();// 记录日志;
	    try {
		Thread.sleep(3600 * 1000);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }// 睡眠3600秒
	}
    }

    private static void getInfo() {
	for (String email : DataBase.myRule.keySet()) {
	    get(email, DataBase.myRule.get(email));
	}
	for (int i = 0; i < DataBase.infoList.size(); i++) {
	    // System.out.println(DataBase.infoList.get(i));
	}
    }

    private static void get(String email, String url) {
	String infoUrl = null;
	String infoContent = null;
	String infoPrice = null;
	Document doc = null;
	// doc = Jsoup.parse(input, "UTF-8");
	String response = HttpClient.getResponse(url);
	// System.out.println(response);
	doc = Jsoup.parseBodyFragment(response);
	Elements links = doc.getElementsByTag("tr");
	String strToFile = "";
	for (Element link1 : links) {
	    infoUrl = "";
	    infoContent = "";
	    infoPrice = "";
	    String s = "";
	    double price = 0;
	    Elements linktds = link1.getElementsByTag("td");
	    for (Element link : linktds) {

		// System.out.println(link.attr("class"));
		if ("t".equals(link.attr("class"))) {

		    String linkText = link.text();
		    if (linkText.length() > 0) {
			s += linkText + "\n";
			infoContent = linkText;
		    }
		    // System.out.println(linkText);

		    // 提取td中的a标签中的href
		    Elements tdlinks = link.getElementsByTag("a");
		    if (tdlinks.size() > 0) {
			String linkHref = tdlinks.first().attr("href");
			if (linkHref.length() > 0) {
			    s += linkHref + "\n";
			    infoUrl = linkHref;
			}
			// System.out.println(linkHref);
		    }
		}
		if ("tc".equals(link.attr("class"))) {
		    if (link.getElementsByTag("b").size() > 0) {
			String strTmp1 = link.getElementsByTag("b").first().text();
			// System.out.println(strTmp1);
			infoPrice = strTmp1;
			price = Double.valueOf(strTmp1);
			s += strTmp1 + "\n";
		    }
		}
	    }
	    System.out.println("------------------------------\n");
	    if (price < 1000000 && infoUrl.length() > 0 && infoUrl.length() < 100) {

		DataBase.infoList.add(new info(email, infoUrl, infoContent, infoPrice));
		// System.out.println(s);
		strToFile += s;
		strToFile += "------------------------------\n";
	    }
	}
	String fileName = "d:\\58giantresult.txt";
	AppendToFile.appendMethodC(fileName, strToFile, false);

    }

}
