package com.jim.httpclient;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * @author Stony Zhang (Mybeautiful)
 * @MSN stonyz@live.com
 * @QQ 55279427
 *
 */
public class HttpCommon {
    private HttpClient httpClient = new HttpClient();

    public static void main(String[] args) {
	String serverURL = "http://10.0.2.16:8081/utry_ckms/login_login.do";
	HttpCommon httpCommon = new HttpCommon();
	Properties pdata = new Properties();
	pdata.put("login_id", "admin2");
	pdata.put("password", "123123");
	System.out.println(httpCommon.postPage(serverURL, pdata));
    }

    public String postPage(String page) {
	PostMethod postMethod = new PostMethod(page);
	return this.post(postMethod);
    }

    public String postPage(String serverURL, Properties pdata) {
	PostMethod postMethod = new PostMethod(serverURL);
	NameValuePair[] data = new NameValuePair[pdata.size()];
	Enumeration keys = pdata.keys();
	int i = 0;
	for (; keys.hasMoreElements();) {
	    String k = (String) keys.nextElement();
	    String v = (String) pdata.get(k);
	    data[i] = new NameValuePair(k, v);
	    i++;
	}
	postMethod.setRequestBody(data);
	return this.post(postMethod);
    }

    private String post(PostMethod postMethod) {
	String result = "";
	try {
	    int statusCode = httpClient.executeMethod(postMethod);
	    System.out.println("statusCode:" + statusCode);
	    // 301 or 302
	    if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
		Header locationHeader = postMethod.getResponseHeader("location");
		String location = null;
		if (locationHeader != null) {
		    location = locationHeader.getValue();
		    System.out.println("The page was redirected to:" + location);
		} else {
		    System.err.println("Location field value is null.");
		}
	    }
	    result = postMethod.getResponseBodyAsString();
	} catch (HttpException e) {
	    e.printStackTrace();
	} catch (IllegalArgumentException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	System.out.println(result);
	return result;

    }

}
