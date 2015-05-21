package com.jim.httpclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class test {
    private static String s = null;
    private static Map<String, String> m = new HashMap<String, String>();

    private static boolean getMap() {
	int index = s.indexOf("捷安特");
	if (index < 0)
	    return false;
	// System.out.println(index);
	int indexbefore = s.lastIndexOf("\"", index);
	int indexafter = s.indexOf("\"", index);
	// System.out.println(indexbefore);
	// System.out.println(indexafter);
	String one = s.substring(indexbefore, indexafter + 1);
	s = s.substring(indexafter);
	index = s.indexOf("<b class='pri'>");
	if (index < 0)
	    return false;
	indexbefore = s.indexOf("</b>", index);
	if (s.length() < 80)
	    return false;
	String two = s.substring(index + 15, indexbefore);
	s = s.substring(indexbefore);
	System.out.println("标题:" + one + "    价格:" + two);
	m.put(one, two);
	return true;
    }

    public static void main(String[] args) {
	s = get();
	while (getMap()) {

	}

    }

    public static String get() {
	CloseableHttpClient httpclient = HttpClients.createDefault();
	String str = null;
	try {
	    // 创建httpget.
	    HttpGet httpget = new HttpGet("http://hz.58.com/zixingche/?key=%E6%8D%B7%E5%AE%89%E7%89%B9%20atx&cmcskey=%E6%8D%B7%E5%AE%89%E7%89%B9%20atx&jump=3&searchtype=1&sourcetype=5&specialtype=gls");
	    System.out.println("executing request " + httpget.getURI());
	    // 执行get请求.
	    CloseableHttpResponse response = httpclient.execute(httpget);
	    try {
		// 获取响应实体
		HttpEntity entity = response.getEntity();
		System.out.println("--------------------------------------");
		// 打印响应状态
		System.out.println(response.getStatusLine());
		if (entity != null) {
		    // 打印响应内容长度
		    System.out.println("Response content length: " + entity.getContentLength());
		    // 打印响应内容
		    // System.out.println("Response content: " +
		    // EntityUtils.toString(entity));
		}
		System.out.println("------------------------------------");
		str = EntityUtils.toString(entity);
	    } finally {
		response.close();
	    }
	} catch (ClientProtocolException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    // 关闭连接,释放资源
	    try {
		httpclient.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return str;
    }
}
