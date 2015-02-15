import java.security.MessageDigest;

public class test {
    static char[] hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static void main(String[] args) {
	try {
	    MessageDigest md5 = MessageDigest.getInstance("MD5");// 申明使用MD5算法
	    md5.update("1发发发发发发发发发发发发发发发发发发发发发发发发发发发发发发".getBytes());//
	    System.out.println("md5(a)=" + byte2str(md5.digest()));

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private static String byte2str(byte[] bytes) {
	int len = bytes.length;
	StringBuffer result = new StringBuffer();
	for (int i = 0; i < len; i++) {
	    byte byte0 = bytes[i];
	    result.append(hex[byte0 >>> 4 & 0xf]);
	    result.append(hex[byte0 & 0xf]);
	}
	return result.toString();
    }
}
