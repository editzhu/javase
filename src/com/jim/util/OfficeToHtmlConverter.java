package com.jim.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hssf.converter.ExcelToHtmlConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;

public class OfficeToHtmlConverter {
    public static void convertExcel2Html(String excelFilePath, String htmlFilePath) throws IOException, ParserConfigurationException, TransformerException {
	File excelFile = new File(excelFilePath);
	File htmlFile = new File(htmlFilePath);
	File htmlFileParent = new File(htmlFile.getParent());
	InputStream is = null;
	OutputStream out = null;
	StringWriter writer = null;
	try {
	    if (excelFile.exists()) {
		if (!htmlFileParent.exists()) {
		    htmlFileParent.mkdirs();
		}
		is = new FileInputStream(excelFile);
		HSSFWorkbook workBook = new HSSFWorkbook(is);
		ExcelToHtmlConverter converter = new ExcelToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());

		converter.processWorkbook(workBook);

		writer = new StringWriter();
		Transformer serializer = TransformerFactory.newInstance().newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD, "html");
		serializer.transform(new DOMSource(converter.getDocument()), new StreamResult(writer));
		out = new FileOutputStream(htmlFile);
		out.write(writer.toString().getBytes("UTF-8"));
		out.flush();
		out.close();
		writer.close();
	    }
	} finally {
	    try {
		if (is != null) {
		    is.close();
		}
		if (out != null) {
		    out.close();
		}
		if (writer != null) {
		    writer.close();
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    public static void convertDoc2Html(String docFilePath, String htmlFilePath) throws IOException, TransformerException, ParserConfigurationException {
	// poi-3.9-20121203.jar 、poi-scratchpad-3.9-20121203.jar
	File docFile = new File(docFilePath);
	OutputStream output = null;
	StringWriter writer = null;
	try {
	    if (docFile.exists()) {
		File htmlFile = new File(htmlFilePath);
		File htmlFileParent = new File(htmlFile.getParent());
		if (!htmlFileParent.exists()) {// 如果父目录不存在，则创建父目录
		    htmlFileParent.mkdirs();
		}
		InputStream input = new FileInputStream(docFile);
		HWPFDocument wordDocument = new HWPFDocument(input);
		WordToHtmlConverter converter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());

		// final String prefix = DateTools.getAllInfo(new Date());//
		// 设置图片文件的前缀，以防止文件名重复，这里getAllInfo方法返回的是“年月日时分秒毫秒”
		final String prefix = getContent(20);// 设置图片文件的前缀，以防止文件名重复，这里getAllInfo方法返回的是“年月日时分秒毫秒”
		// 将doc文件中的图片重命名
		converter.setPicturesManager(new PicturesManager() {
		    public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
			return prefix + "_" + suggestedName;
		    }
		});
		converter.processDocument(wordDocument);
		// 将doc中的图片文件写入到与html同级目录中
		List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
		if (pics != null) {
		    for (Picture pic : pics) {
			try {
			    pic.writeImageContent(new FileOutputStream(htmlFileParent.getPath() + "/" + prefix + "_" + pic.suggestFullFileName()));
			} catch (FileNotFoundException e) {
			    e.printStackTrace();
			}
		    }
		}

		writer = new StringWriter();

		Transformer serializer = TransformerFactory.newInstance().newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD, "html");
		serializer.transform(new DOMSource(converter.getDocument()), new StreamResult(writer));
		// 将doc内容写入到html文件中
		output = new FileOutputStream(htmlFile);
		output.write(writer.toString().getBytes("UTF-8"));
		output.flush();
		output.close();
		writer.close();
	    }

	} finally {
	    try {
		if (output != null) {
		    output.close();
		}
		if (writer != null) {
		    writer.close();
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    public static void convertPpt2Html(String pptFilePath, String htmlFilePath) throws IOException {
	// 仅仅完成在指定目录下输出png图片的功能,几张幻灯片输出几张图片
	File pptFile = new File(pptFilePath);
	if (pptFile.exists()) {
	    File htmlFileParent = new File(htmlFilePath);
	    if (!htmlFileParent.exists()) {// 如果父目录不存在，则创建父目录
		htmlFileParent.mkdirs();
	    }
	    InputStream input = new FileInputStream(pptFile);
	    HSLFSlideShow pptDocument = new HSLFSlideShow(input);
	    SlideShow ppt = new SlideShow(pptDocument);
	    Dimension pgsize = ppt.getPageSize();
	    Slide[] slide = ppt.getSlides();
	    for (int i = 0; i < slide.length; i++) {
		BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = img.createGraphics();
		// clear the drawing area
		graphics.setPaint(Color.white);
		graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height)); // render
		slide[i].draw(graphics);
		FileOutputStream out = new FileOutputStream(htmlFileParent.getPath() + "/" + getContent(20) + ".png");
		javax.imageio.ImageIO.write(img, "png", out);
		out.close();
	    }

	}

    }

    public static String getContent(int length) {
	// 产生随机的字符串
	String base = "abcdefghijklmnopqrstuvwxyz0123456789";
	int baseSize = base.length();
	Random random = new Random();
	StringBuffer sb = new StringBuffer();
	for (int i = 0; i < length; i++) {
	    int number = random.nextInt(baseSize);
	    sb.append(base.charAt(number));
	}
	return sb.toString();

    }

    public static void main(String[] args) {
	System.out.println("begin");
	try {
	    convertPpt2Html("d:\\3.ppt", "d:\\");
	    convertExcel2Html("d:\\2.xls", "d:\\2.html");
	    convertDoc2Html("d:\\1.doc", "d:\\1.html");
	} catch (IOException | ParserConfigurationException | TransformerException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	System.out.println("over");

    }
}
