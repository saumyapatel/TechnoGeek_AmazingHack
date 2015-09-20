package com.amazon.pooling;

import java.io.InputStream;
import java.util.Properties;

public class PropsLoaderlabel {
	private static Properties prop=null;
	private static String path="/com/amazon/pooling/LabelCommonProps.properties";

	private static void loadProps(String propPath)
	{
		Properties prop1 =null;
		InputStream in =null;
		try {
			prop1 = new Properties();
			in = PropsLoaderlabel.class.getResourceAsStream(propPath);
			prop1.load(in);
			in.close();
			prop=prop1;
			System.out.println("Props Loaded..");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{in=null;prop1=null;}
	}
	public static Properties loadProperty(String propPath)
	{
		Properties prop1 =null;
		InputStream in =null;
		try {
			prop1 = new Properties();
			in = PropsLoaderlabel.class.getResourceAsStream(propPath);
			prop1.load(in);
			in.close();
			//	System.out.println(prop.getProperty("isLoaded"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{in=null;}
		return prop1;
	}

	public static String getCommonProperty(String key)
	{
		if(prop==null)
			loadProps(path);
		if(prop!=null)
			return prop.getProperty(key);
		else
			return null;
	}
}
