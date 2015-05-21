package com.jim.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/*遍历指定的目录
 * 重新定义输出
 **/

public class ForEachDir {
    private static int id = 0;
    private static int level = 0;
    private static Map<Integer, Integer> mapFileId = new HashMap<Integer, Integer>();
    private static Map<Integer, File> mapFileName = new HashMap<Integer, File>();

    public static void main(String[] args) {
	File path = new File("D:\\test");
	id++;
	mapFileId.put(id, 0);
	mapFileName.put(id, path);
	getChildDir(path, id);

	for (Map.Entry<Integer, Integer> entry : mapFileId.entrySet()) {
	    System.out.println(entry.getKey() + ":" + entry.getValue());
	}

	for (Map.Entry<Integer, File> entry : mapFileName.entrySet()) {
	    System.out.println(getPre(entry.getKey()) + entry.getValue().getName());
	}

    }

    static void getChildDir(File file, int parentId) {

	File[] files = file.listFiles();
	for (File f : files) {
	    id++;
	    mapFileId.put(id, parentId);
	    mapFileName.put(id, f);
	    if (f.isDirectory()) {
		getChildDir(f, id);
	    }
	}
    }

    static String getPre(int mid) {
	level = 0;
	String s = "";
	int oo = getLevel(mid);
	for (int i = 0; i < oo; i++)
	    s += "--";
	return s;
    }

    static int getLevel(int i) {
	for (Map.Entry<Integer, Integer> entry : mapFileId.entrySet()) {
	    if (entry.getKey() == i) {
		if (entry.getValue() == 0) {
		    return level;
		} else {
		    level++;
		    getLevel(entry.getValue());
		}
	    }
	}
	return level;
    }
}
