package com.jim.javaRunExe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class test {
    public static final int SUCCESS = 0;
    public static final String SUCCESS_MESSAGE = "程序执行成功！";
    public static final String ERROR_MESSAGE = "程序执行出错：";

    public static void main(String[] args) throws IOException, InterruptedException {
	String[] s = { "D:\\workspace-vc\\EasyPR-master\\demo.exe" };
	// String[] s = { "D:\\test1.exe" };

	final Process process = Runtime.getRuntime().exec(s);

	// 打印程序输出
	readProcessOutput(process);

	// 等待程序执行结束并输出状态
	int exitCode = process.waitFor();
	if (exitCode == SUCCESS) {
	    System.out.println(SUCCESS_MESSAGE);
	} else {
	    System.err.println(ERROR_MESSAGE + exitCode);
	}

    }

    private static void readProcessOutput(final Process process) {
	// 将进程的正常输出在 System.out 中打印，进程的错误输出在 System.err 中打印
	read(process.getInputStream(), System.out);
	read(process.getErrorStream(), System.err);
    }

    // 读取输入流
    private static void read(InputStream inputStream, PrintStream out) {
	try {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

	    String line;
	    while ((line = reader.readLine()) != null) {
		out.println(line);
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	} finally {

	    try {
		inputStream.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
}
