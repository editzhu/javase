package com.jim.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClient {
    public static String getResponse(String url) {
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
		System.out.println("--------------------------------------");
		// ��ӡ��Ӧ״̬
		System.out.println(response.getStatusLine());
		if (entity != null) {
		    // ��ӡ��Ӧ���ݳ���
		    System.out.println("Response content length: " + entity.getContentLength());
		    // ��ӡ��Ӧ����
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
	    // �ر�����,�ͷ���Դ
	    try {
		httpclient.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	return str;
    }
}
