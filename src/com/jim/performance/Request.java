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
	    // ����httpget.
	    HttpGet httpget = new HttpGet(url);
	    System.out.println("executing request " + httpget.getURI());
	    // ִ��get����.
	    CloseableHttpResponse response = httpclient.execute(httpget);
	    try {
		// ��ȡ��Ӧʵ��
		HttpEntity entity = response.getEntity();
		// System.out.println("--------------------------------------");
		// ��ӡ��Ӧ״̬
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
		    // ��ӡ��Ӧ���ݳ���
		    // System.out.println("Response content length: " +
		    // entity.getContentLength());
		    // ��ӡ��Ӧ����
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
	    // �ر�����,�ͷ���Դ
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
