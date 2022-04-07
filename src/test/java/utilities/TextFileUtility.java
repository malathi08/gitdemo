package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TextFileUtility {
	//create method with String array as return type since it takes multiple arguments so use String[] 
	
	public static String[] getValueInTextFile(String filepath,int linenumber) throws Exception
	{
		//Access data from text file by providing filepath and linenumber and read line by line
		File f=new File(filepath);
		FileReader fr=new FileReader(f);
		BufferedReader br=new BufferedReader(fr);
		for(int i=0;i<linenumber;i++)
		{
			br.readLine();
		}
		String temp=br.readLine();
		String output[]=temp.toString().split(",");
		br.read();
		fr.read();
		return(output);
	}
	public static int getLinescount(String fp) throws Exception
	{
		File f=new File(fp);
		FileReader fr=new FileReader(f);
		BufferedReader br=new BufferedReader(fr);
		int count=0;
		while(br.readLine()!=null)
		{
			count++;
		}
		br.close();
		fr.close();
		return(count);
	}
	
	public static void main(String[] args) throws Exception
	{
		String fp=System.getProperty("user.dir")+"\\src\\test\\resources\\datafiles\\testdata.txt";
		int c=TextFileUtility.getLinescount(fp);
		System.out.println(c);
		for(int i=0;i<c;i++)
		{
			String y[]=TextFileUtility.getValueInTextFile(fp, i);
			for(int j=0;j<y.length;j++)
			{
				System.out.println(y[j]);
			}
		}
		
	}

}
