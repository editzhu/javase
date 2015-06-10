package com.jim.mypush;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jim.util.AppendToFile;

public class DataBase {
    // 基本信息email+urlAndPrice
    public static Map<String, String> myRule = new HashMap<String, String>();

    // 存放符合条件的信息
    public static List<DataBase.info> infoList = new ArrayList<DataBase.info>();

    // 以发送的信息
    public static List<String> infoListSend = new ArrayList<String>();

    public static class info {
	public String mail;
	public String url;
	public String content;
	public String price;

	info(String email, String url, String content, String price) {
	    this.mail = email;
	    this.url = url;
	    this.content = content;
	    this.price = price;

	}

	public String toString() {
	    return mail + url + content + price;
	}
    }

    DataBase() {
	myRule.put("yinyong@utry.cn", "http://hz.58.com/zixingche/?minprice=0_651&key=%2525u6377%2525u5B89%2525u7279&sourcetype=5");

    }

    public static void beginLog() {
	for (int i = 0; i < infoListSend.size(); i++) {
	    AppendToFile.appendMethodC("d:\\Guardianprocess.txt", System.currentTimeMillis() + ":" + infoListSend.get(i), true);

	}
    }

    // public static void main(String[] args) {
    // infoList.add(new info("a@c.com", "1", "2", "3"));
    // infoList.add(new info("a@c.com", "4", "5", "6"));
    // for (int i = 0; i < infoList.size(); i++) {
    // System.out.println(infoList.get(i));
    // }
    // }
}
