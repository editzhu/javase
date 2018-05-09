package com.jim.dl2img;

import org.apache.commons.io.FilenameUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;

public class ImageDownload {

    /**
     * @param args
     */
    public static void main(String[] args) {
	HttpClient http = new DefaultHttpClient();
	try {

	    // String html = ImageShow.getimg(http, "http://www.baidu.com");
	    String html = ReadFile.readFileByLines("E:\\1.txt");
	    System.out.println(html);
	    Parser p = new Parser();// ����������
	    p.setInputHTML(html);// ����html
	    NodeList nl = p.parse(new NodeClassFilter(ImageTag.class));// ѡ��image��ǩ
	    for (int i = 0; i < nl.size(); i++) {
		ImageTag image = (ImageTag) nl.elementAt(i);
		String imageurl = image.getImageURL();// ���ͼƬsrc����ֵ
		String url = imageurl;
		System.out.println(url);
		String jpg = FilenameUtils.getName(url);// ͼƬ��
		// byte[] im = ImageShow.getimage(http, url);
		// IOUtils.write(im, new FileOutputStream("E:/temp/" + jpg));
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

}
