package com.jim.FileCopy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class test {
    private static Map<Integer, Integer> mapFileId = new HashMap<Integer, Integer>();
    private static Map<Integer, File> mapFileName = new HashMap<Integer, File>();

    public static void main(String[] args) {
	File path = new File("D:\\CKMS产品部\\数据和资源\\easypr-resources\\train\\ann");
	File[] files = path.listFiles();
	for (File f : files) {
	    if (f.isDirectory()) {
		// getChildDir(f, id);
	    }
	}

    }

}
