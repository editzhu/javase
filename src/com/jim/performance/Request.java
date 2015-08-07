package com.jim.performance;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Request {
    private static final String url = "http://36kr.com";
    private static long duration;

    public static long get() {
	long start = System.currentTimeMillis();
	CloseableHttpClient httpclient = HttpClients.createDefault();
	String str = null;
	try {
	    // 创建httpget.
	    HttpGet httpget = new HttpGet(url);
	    System.out.println("executing request " + httpget.getURI());
	    // 执行get请求.
	    CloseableHttpResponse response = httpclient.execute(httpget);
	    try {
		// 获取响应实体
		HttpEntity entity = response.getEntity();
		// System.out.println("--------------------------------------");
		// 打印响应状态
		System.out.println(response.getStatusLine().getStatusCode());
		if (response.getStatusLine().getStatusCode() != 200) {

		    System.out.println("*********************************************");
		    System.out.println("*********************************************");
		    System.out.println("*********************************************");
		    System.out.println("*********************************************");
		    System.out.println("*********************************************");
		    System.out.println("*********************************************");
		    System.out.println("*********************************************");
		    System.out.println("*********************************************");
		    System.out.println("*********************************************");
		    System.out.println("*********************************************");
		    System.out.println("*********************************************");
		    System.out.println("*********************************************");
		    System.out.println("*********************************************");
		    System.out.println("*********************************************");
		    System.out.println("*********************************************");
		    return -1;
		}
		if (entity != null) {
		    // 打印响应内容长度
		    // System.out.println("Response content length: " +
		    // entity.getContentLength());
		    // 打印响应内容
		    // System.out.println("Response content: " +
		    // EntityUtils.toString(entity));
		}
		System.out.println("------------------------------------");
		str = EntityUtils.toString(entity);
		System.out.println(str);
		System.out.println("------------------------------------");
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
	long end = System.currentTimeMillis();
	duration = end - start;
	return duration;
    }

}
