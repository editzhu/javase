package com.jim.bianchengzhuji;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.jim.util.log;

/**
 * �ļ�A�������10000000���ļ�¼,ÿ����¼ΪС��10000000��������,�Ҳ��ظ� �����ļ�B, ����Ϊ�������ļ�A ����:ʹ��1M���ڴ�
 */
public class c01 {
    public static void main(String[] args) {
	log log = new log(true);
	// �ж��ļ�A�Ƿ����
	String fileName="D:/A.txt";
	File f = new File(fileName);
	if (f.exists())
	    log.p("OK");
	else
	{
	    log.p("NOT");
	    // �����ļ�A
	    int[] set = new int[100000];
	    boolean isRepeat = false;
	    for (int i = 0; i < 100000; i++) {
		isRepeat = false;
		int tmp = (int) (Math.random() * 100000);
		for (int j = 0; j < i; j++) {
		    if (set[j] == tmp) {
			isRepeat = true;
			break;
		    }
		}
		if (isRepeat)
		    set[i] = 0;
		else
		    set[i] = tmp;
	    }
	    try {
		// ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
		FileWriter writer = new FileWriter(fileName, true);
		for (int i = 0; i < 100000; i++) {
		    if (set[i] != 0) {
			String s = Integer.toString(set[i]);
			writer.write(s);
			writer.write("\n");
		    }
		}
		writer.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }

		
	}
	// ���õ�һ�ֵڶ��ֵ����ַ���
	sort1();
    }

    // ����λͼ�洢��,��Ϊ�������ظ�
    public static void sort1() {
	int[] bit = new int[100000];
	for (int i = 0; i < 100000; i++) {
	    bit[i] = 0;
	}
	File file = new File("d:/A.txt");
	BufferedReader reader = null;
	try {
	    System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
	    reader = new BufferedReader(new FileReader(file));
	    String tempString = null;
	    int line = 0;
	    // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
	    while ((tempString = reader.readLine()) != null) {
		// ��ʾ�к�
		System.out.println("line " + line + ": " + tempString);
		bit[Integer.parseInt(tempString)] = 1;
		line++;
	    }
	    reader.close();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    if (reader != null) {
		try {
		    reader.close();
		} catch (IOException e1) {
		}
	    }

	}
	try {
	    // ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
	    FileWriter writer = new FileWriter("d:/B.txt", true);
	    for (int i = 0; i < 100000; i++) {
		if (bit[i] != 0) {
		    String s = Integer.toString(i);
		    writer.write(s);
		    writer.write("\n");
		}
	    }
	    writer.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
