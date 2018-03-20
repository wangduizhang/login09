package cn.wp.utils;
import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.dom4j.Document;

import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLUtils {
	private static String filepath;
	static{
	   try {
		filepath	= URLDecoder.decode(XMLUtils.class.getClassLoader().getResource("Users.xml").getPath(),"UTF-8");
	} catch (UnsupportedEncodingException e) {
		// TODO 自动生成的 catch 块
		throw new RuntimeException(e);
	}
	}
	
	public static Document getDocument() throws Exception{
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(filepath));
		return document;
	}
	public static void write2XML(Document document) throws IOException{
		
        // Pretty print the document to System.out
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter(new FileOutputStream(new File(filepath)), format);
        writer.write( document );
        writer.close();
	}
}
