package com.jim.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DES {
    public Key key = setKey("afasdfad");

    public Key setKey(String strKey) {
	try {
	    KeyGenerator _generator = KeyGenerator.getInstance("DES");
	    _generator.init(new SecureRandom(strKey.getBytes()));
	    this.key = _generator.generateKey();
	    _generator = null;
	} catch (Exception e) {
	    throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
	}
	return this.key;
    }

    private byte[] encryptByte(byte[] byteS) {
	byte[] byteFina = null;
	Cipher cipher;
	try {
	    cipher = Cipher.getInstance("DES");
	    cipher.init(Cipher.ENCRYPT_MODE, key);
	    byteFina = cipher.doFinal(byteS);
	} catch (Exception e) {
	    throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
	} finally {
	    cipher = null;
	}
	return byteFina;
    }

    public String encryptStr(String strMing) {
	byte[] byteMi = null;
	byte[] byteMing = null;
	String strMi = "";
	BASE64Encoder base64en = new BASE64Encoder();
	try {
	    byteMing = strMing.getBytes("UTF8");
	    byteMi = this.encryptByte(byteMing);
	    strMi = base64en.encode(byteMi);
	} catch (Exception e) {
	    throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
	} finally {
	    base64en = null;
	    byteMing = null;
	    byteMi = null;
	}
	return strMi;
    }

    private byte[] decryptByte(byte[] byteD) {
	Cipher cipher;
	byte[] byteFina = null;
	try {
	    cipher = Cipher.getInstance("DES");
	    cipher.init(Cipher.DECRYPT_MODE, key);
	    byteFina = cipher.doFinal(byteD);
	} catch (Exception e) {
	    throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
	} finally {
	    cipher = null;
	}
	return byteFina;
    }

    public String decryptStr(String strMi) {
	BASE64Decoder base64De = new BASE64Decoder();
	byte[] byteMing = null;
	byte[] byteMi = null;
	String strMing = "";
	try {
	    byteMi = base64De.decodeBuffer(strMi);
	    byteMing = this.decryptByte(byteMi);
	    strMing = new String(byteMing, "UTF8");
	} catch (Exception e) {
	    throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
	} finally {
	    base64De = null;
	    byteMing = null;
	    byteMi = null;
	}
	return strMing;
    }

    public static void main(String[] args) {
	DES des = new DES();
	String str1 = " 要加密的字符串 test";
	// DES 加密字符串
	String str2 = des.encryptStr(str1);
	// DES 解密字符串
	String deStr = des.decryptStr(str2);
	System.out.println(" 加密前： " + str1);
	System.out.println(" 加密后： " + str2);
	System.out.println(" 解密后： " + deStr);
    }
}
