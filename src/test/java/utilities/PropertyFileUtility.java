package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtility 
{
	public static String getValueInPropertyFile(String propertyname) throws Exception
	{
		String filepath=System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties";
		FileInputStream fi=new FileInputStream(filepath);
		Properties pro=new Properties();
		pro.load(fi);
		String value=pro.getProperty(propertyname);
		fi.close();
		return(value);
	}
	
	public static void main(String[] args) throws Exception
	{
		String x=PropertyFileUtility.getValueInPropertyFile("url");
		String y=PropertyFileUtility.getValueInPropertyFile("maxwait");
		String z=PropertyFileUtility.getValueInPropertyFile("interval");
		System.out.println(x  +  y  + z);
	}
}
