package com.jim.util;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

public class AppendToFile {
    /**
     * A����׷���ļ���ʹ��RandomAccessFile
     */
    public static void appendMethodA(String fileName, String content) {
	try {
	    // ��һ����������ļ���������д��ʽ
	    RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
	    // �ļ����ȣ��ֽ���
	    long fileLength = randomFile.length();
	    // ��д�ļ�ָ���Ƶ��ļ�β��
	    randomFile.seek(fileLength);
	    randomFile.writeBytes(content);
	    randomFile.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * B����׷���ļ���ʹ��FileWriter
     */
    public static void appendMethodB(String fileName, String content) {
	try {
	    // ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�,false��ʾ��׷��
	    FileWriter writer = new FileWriter(fileName, true);
	    writer.write(content);
	    writer.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static void appendMethodC(String fileName, String content, boolean isappend) {
	OutputStreamWriter out;
	try {
	    out = new OutputStreamWriter(new FileOutputStream(fileName, isappend), "UTF-8");
	    out.write(content);
	    out.flush();
	    out.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public static void main(String[] args) {
	String fileName = "d:\\1.txt";
	String content = "new append!";
	// ������A׷���ļ�
	AppendToFile.appendMethodA(fileName, content);
	AppendToFile.appendMethodA(fileName, "append end. \n");
	// ��ʾ�ļ�����
	ReadFromFile.readFileByLines(fileName);
	// ������B׷���ļ�
	AppendToFile.appendMethodB(fileName, content);
	AppendToFile.appendMethodB(fileName, "append end. \n");
	// ��ʾ�ļ�����
	ReadFromFile.readFileByLines(fileName); // ������B׷���ļ�
	AppendToFile.appendMethodB(fileName, content);
	AppendToFile.appendMethodB(fileName, "append end. \n");
	// ��ʾ�ļ�����
	ReadFromFile.readFileByLines(fileName);
    }
}
