package com.jim.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/*
 * ��ȡָ����ʽ���ļ�,��������״Ŀ¼�ṹ
 * ��һ���Ƕ���Ŀ¼
 * �������һ��Ŀ¼,����Ŀ¼,������tab���,����Ŀ¼����伸��tab
 */

public class readTreeInFile {
    // ����һ��LIST,����ļ�
    private List<String> l = new ArrayList<String>();
    // ��������Ŀ¼�ṹ,��ű�׼Ϊ<id,parentId>
    private Map<Integer, Integer> m = new HashMap<Integer, Integer>();
    // ��������id+String,��ű�׼Ϊ<id,String>
    private Map<Integer, String> m1 = new HashMap<Integer, String>();
    // ��¼��ÿ��ĵ�ǰ�ڵ�(δ���ڵ�)
    private List<Integer> currLevel = new ArrayList<Integer>();

    readTreeInFile() {
	// �������ļ���list
	l.add("1");
	l.add("\t11");
	l.add("\t\t111");
	l.add("\t\t112");
	l.add("\t\t\t1121");
	l.add("\t\t\t1122");
	l.add("\t12");

    }

    readTreeInFile(String fileName) {
	File file = new File(fileName);
	BufferedReader reader = null;
	try {
	    reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf8"));
	    String tempString = null;
	    // int line = 1;
	    // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
	    while ((tempString = reader.readLine()) != null) {
		// ��ʾ�к�
		// System.out.println("line " + line + ": " + tempString);
		// line++;
		l.add(tempString);
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
    }

    public static void main(String[] args) {
	readTreeInFile r = new readTreeInFile("d:\\yan.txt");
	int beforeTab = 0;// ǰһ�е�tab����,Ĭ�ϵ�һ�о���0
	int line = 0;// ���е�id
	int thisTab = 0;// ��ǰ�е�tab����

	for (int i = 0; i < r.l.size(); i++) {
	    // ���ɱ��е�ID
	    line++;
	    // ��֤���ݺϷ���:��һ��Ҫ�󶥸���tab,�ӵڶ��п�ʼ��1��N��tab,ÿ�е�tab�������ô���ǰһ������+1
	    thisTab = getCountTab(r.l.get(i));
	    if (i == 0) {
		r.currLevel.add(0, 1);
		r.m.put(1, 0);
		r.m1.put(1, r.l.get(i));
		if (thisTab != 0) {
		    System.out.println("error in file ,0000");
		    return;
		}
	    } else {
		if (thisTab < 0) {
		    System.out.println("error in file ,1111");
		    return;
		}
		if ((thisTab - beforeTab) > 1) {
		    System.out.println("error in file ,2222");
		    return;
		}
		beforeTab = thisTab;
		r.currLevel.add(thisTab, line);
		r.m.put(line, r.currLevel.get(thisTab - 1));
		r.m1.put(line, r.l.get(i).substring(thisTab));
		System.out.println(thisTab);

	    }
	}
	System.out.println(r.m);
	System.out.println(r.m1);
	String jsonString = "{'node':[";
	for (Integer a : r.m.keySet()) {
	    jsonString = jsonString + "{'id':'" + a + "','parentId':'" + r.m.get(a) + "'},";
	}
	jsonString = jsonString + "]}";
	System.out.println(jsonString);
	System.out.println(JSON.toJSONString(r.m));
	JSONObject j = JSON.parseObject(JSON.toJSONString(r.m));
	System.out.println(j);
    }

    static int getCountTab(String s) {
	int countTab = 0;
	char[] c = s.toCharArray();
	for (int a : c) {
	    if (a == 9)// tab����ֵ����9
		countTab++;
	    else
		break;
	}
	return countTab;
    }

}
