package com.jim.util;

public class Md5 {
    public static long ELFHash(String strUri) {
	long hash = 0;
	long x = 0;
	for (int i = 0; i < strUri.length(); i++) {
	    hash = (hash << 4) + strUri.charAt(i);
	    if ((x = hash & 0xF0000000L) != 0) {
		hash ^= (x >> 24);
		hash &= ~x;
	    }
	}
	return (hash & 0x7FFFFFFF);
    }
}
