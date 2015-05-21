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
 * 读取指定格式的文件,解析出树状目录结构
 * 第一行是顶级目录
 * 后面跟着一级目录,二级目录,行首以tab填充,几级目录就填充几个tab
 */

public class readTreeInFile {
    // 定义一个LIST,替代文件
    private List<String> l = new ArrayList<String>();
    // 解析出的目录结构,存放标准为<id,parentId>
    private Map<Integer, Integer> m = new HashMap<Integer, Integer>();
    // 解析出的id+String,存放标准为<id,String>
    private Map<Integer, String> m1 = new HashMap<Integer, String>();
    // 记录树每层的当前节点(未完结节点)
    private List<Integer> currLevel = new ArrayList<Integer>();

    readTreeInFile() {
	// 填充替代文件的list
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
	    // 一次读入一行，直到读入null为文件结束
	    while ((tempString = reader.readLine()) != null) {
		// 显示行号
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
	int beforeTab = 0;// 前一行的tab数量,默认第一行就是0
	int line = 0;// 本行的id
	int thisTab = 0;// 当前行的tab数量

	for (int i = 0; i < r.l.size(); i++) {
	    // 生成本行的ID
	    line++;
	    // 验证数据合法性:第一行要求顶格无tab,从第二行开始有1到N个tab,每行的tab数量不得大于前一行数量+1
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
	    if (a == 9)// tab键的值等于9
		countTab++;
	    else
		break;
	}
	return countTab;
    }

}
