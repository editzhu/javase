package com.jim.util;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

public class AppendToFile {
    /**
     * A方法追加文件：使用RandomAccessFile
     */
    public static void appendMethodA(String fileName, String content) {
	try {
	    // 打开一个随机访问文件流，按读写方式
	    RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
	    // 文件长度，字节数
	    long fileLength = randomFile.length();
	    // 将写文件指针移到文件尾。
	    randomFile.seek(fileLength);
	    randomFile.writeBytes(content);
	    randomFile.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /**
     * B方法追加文件：使用FileWriter
     */
    public static void appendMethodB(String fileName, String content) {
	try {
	    // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件,false表示不追加
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
	// 按方法A追加文件
	AppendToFile.appendMethodA(fileName, content);
	AppendToFile.appendMethodA(fileName, "append end. \n");
	// 显示文件内容
	ReadFromFile.readFileByLines(fileName);
	// 按方法B追加文件
	AppendToFile.appendMethodB(fileName, content);
	AppendToFile.appendMethodB(fileName, "append end. \n");
	// 显示文件内容
	ReadFromFile.readFileByLines(fileName); // 按方法B追加文件
	AppendToFile.appendMethodB(fileName, content);
	AppendToFile.appendMethodB(fileName, "append end. \n");
	// 显示文件内容
	ReadFromFile.readFileByLines(fileName);
    }
}
