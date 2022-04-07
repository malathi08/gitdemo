package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility 
{
	//Properties
		private Workbook wb;
		private File f;
		private FileInputStream fi;
		private FileOutputStream fo;
		private Sheet sh;
		//Construcor methods
		
		public ExcelFileUtility()
		{
			f=null;
			wb=null;
			fi=null;
			fo=null;
		}
		
		public void OpenExcelFile(String filepath) throws Exception
		{
			f=new File(filepath);
			fi=new FileInputStream(f);   //open file
			wb=WorkbookFactory.create(fi);  //open a work sheet
			fo=new FileOutputStream(f);
		}
		//operational methods
		//1.open a sheet if exists
		public void opensheet(String sheetname)
		{
			sh=wb.getSheet(sheetname);
		}
		//2.get row count
		public int getRowCount()
		{
			int rowcount=sh.getPhysicalNumberOfRows();
			return(rowcount);
		}
		//3.get column number count
		public int getColumnsCount(int rowindex)
		{
			int colcount=sh.getRow(rowindex).getLastCellNum();
			return(colcount);
		}
		//4.create result column i.e create a new column
		public void createResultsColumn(int colindex)
		{
			SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
			Date dt=new Date();
			//create results column in first row by default
			Cell rc=sh.getRow(0).createCell(colindex);
			rc.setCellValue(sf.format(dt));
			sh.autoSizeColumn(colindex);
		}
		//5.get cell value by default DataFormatter is a class ,method will take cell value as string only
		public String getCellValue(int rowindex,int colindex)
		{
			DataFormatter df=new DataFormatter();
			String value=df.formatCellValue(sh.getRow(rowindex).getCell(colindex));
			return(value);
		}
		//6.set cell value by given text
		public void setCellValue(int rowindex,int colindex,String x)
		{
			Cell c=sh.getRow(rowindex).createCell(colindex);
			//Cell c=sh.createRow(rowindex).createCell(colindex);
			c.setCellValue(x);
			sh.autoSizeColumn(colindex);
		}
		//7.save and close excel file
		public void saveAndClose() throws Exception
		{
			wb.write(fo);
			wb.close();
			fo.close();
			fi.close();
		}
}
