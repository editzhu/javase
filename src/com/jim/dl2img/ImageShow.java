package com.jim.dl2img;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

public class ImageShow {
    public static String getimg(HttpClient http, String url) {
	try {
	    HttpGet get = new HttpGet(url);
	    HttpResponse hr = http.execute(get);
	    HttpEntity he = hr.getEntity();// 哈哈
	    if (he != null) {
		String charset = EntityUtils.getContentCharSet(he);
		InputStream is = he.getContent();
		return IOUtils.toString(is, charset);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;

    }

    public static byte[] getimage(HttpClient http, String url) {
	try {
	    HttpGet hg = new HttpGet(url);
	    HttpResponse hr = http.execute(hg);
	    HttpEntity he = hr.getEntity();
	    if (he != null) {
		InputStream is = he.getContent();
		return IOUtils.toByteArray(is);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
}
