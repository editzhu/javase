package com.jim.util;

import java.io.UnsupportedEncodingException;

public class encode {
    public static void main(String[] args) throws UnsupportedEncodingException {
	String chinese = "����";// java�ڲ�����
	String gbkChinese = new String(chinese.getBytes("GBK"), "ISO-8859-1");// ת����gbk����
	String unicodeChinese = new String(gbkChinese.getBytes("ISO-8859-1"), "GBK");// java�ڲ�����
	System.out.println(unicodeChinese);// ����
	String utf8Chinese = new String(unicodeChinese.getBytes("UTF-8"), "ISO-8859-1");// utf--8����
	System.out.println(utf8Chinese);// ����
	unicodeChinese = new String(utf8Chinese.getBytes("ISO-8859-1"), "UTF-8");// java�ڲ�����
	System.out.println(unicodeChinese);// ����
    }
}
