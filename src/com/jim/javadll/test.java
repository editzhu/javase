package com.jim.javadll;

import java.io.UnsupportedEncodingException;

public class test {
    static {
	System.loadLibrary("demo");// ����dll
    }

    // ��native�ؼ������ν�����������ʵ�ֵķ���
    public native static void ini();// ��������

    public native static String LPR(String s);// ��������

    public native static int add(int a, int b);// ��������

    public static void main(String[] args) throws UnsupportedEncodingException {
	// ���ط����ĵ���
	System.out.println("ini\n");
	ini();
	System.out.println("lpr\n");
	String s = LPR(".\\car1.jpg");
	System.out.println("xxxyyy : " + s);
    }

}
