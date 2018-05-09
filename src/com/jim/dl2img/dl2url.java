package com.jim.dl2img;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class dl2url {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
	// TODO Auto-generated method stub
	download("http://www.ygjj.com/bookpic2/2014-12-16/new522941-20141216155732235778.JPG", "2.jpg", "e:\\work\\2016\\10000img");
    }

    public static void download(String urlString, String filename, String savePath) throws Exception {
	// ����URL
	URL url = new URL(urlString);
	// ������
	URLConnection con = url.openConnection();
	// ��������ʱΪ5s
	con.setConnectTimeout(5 * 1000);
	// ������
	InputStream is = con.getInputStream();

	// 1K�����ݻ���
	byte[] bs = new byte[1024];
	// ��ȡ�������ݳ���
	int len;
	// ������ļ���
	File sf = new File(savePath);
	if (!sf.exists()) {
	    sf.mkdirs();
	}
	OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
	// ��ʼ��ȡ
	while ((len = is.read(bs)) != -1) {
	    os.write(bs, 0, len);
	}
	// ��ϣ��ر���������
	os.close();
	is.close();
    }

}
