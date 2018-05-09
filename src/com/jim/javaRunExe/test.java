package com.jim.javaRunExe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class test {
    public static final int SUCCESS = 0;
    public static final String SUCCESS_MESSAGE = "����ִ�гɹ���";
    public static final String ERROR_MESSAGE = "����ִ�г���";

    public static void main(String[] args) throws IOException, InterruptedException {
	String[] s = { "D:\\workspace-vc\\EasyPR-master\\demo.exe" };
	// String[] s = { "D:\\test1.exe" };

	final Process process = Runtime.getRuntime().exec(s);

	// ��ӡ�������
	readProcessOutput(process);

	// �ȴ�����ִ�н��������״̬
	int exitCode = process.waitFor();
	if (exitCode == SUCCESS) {
	    System.out.println(SUCCESS_MESSAGE);
	} else {
	    System.err.println(ERROR_MESSAGE + exitCode);
	}

    }

    private static void readProcessOutput(final Process process) {
	// �����̵���������� System.out �д�ӡ�����̵Ĵ�������� System.err �д�ӡ
	read(process.getInputStream(), System.out);
	read(process.getErrorStream(), System.err);
    }

    // ��ȡ������
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
