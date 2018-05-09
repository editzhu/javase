package com.jim.dl2img;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static String readFileByLines(String fileName) {
	File file = new File(fileName);
	BufferedReader reader = null;
	String s = null;
	try {
	    reader = new BufferedReader(new FileReader(file));
	    String tempString = null;
	    // 一次读入一行，直到读入null为文件结束
	    while ((tempString = reader.readLine()) != null) {
		s = s + tempString;
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
	return s;
    }
}
