package com.jim.bianchengzhuji;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.jim.util.log;

/**
 * 文件A中有最多10000000条的记录,每条记录为小于10000000的正整数,且不重复 输入文件B, 内容为排序后的文件A 条件:使用1M的内存
 */
public class c01 {
    public static void main(String[] args) {
	log log = new log(true);
	// 判断文件A是否存在
	String fileName="D:/A.txt";
	File f = new File(fileName);
	if (f.exists())
	    log.p("OK");
	else
	{
	    log.p("NOT");
	    // 创建文件A
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
		// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
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
	// 采用第一种第二种第三种方法
	sort1();
    }

    // 采用位图存储法,因为整数不重复
    public static void sort1() {
	int[] bit = new int[100000];
	for (int i = 0; i < 100000; i++) {
	    bit[i] = 0;
	}
	File file = new File("d:/A.txt");
	BufferedReader reader = null;
	try {
	    System.out.println("以行为单位读取文件内容，一次读一整行：");
	    reader = new BufferedReader(new FileReader(file));
	    String tempString = null;
	    int line = 0;
	    // 一次读入一行，直到读入null为文件结束
	    while ((tempString = reader.readLine()) != null) {
		// 显示行号
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
	    // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
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
