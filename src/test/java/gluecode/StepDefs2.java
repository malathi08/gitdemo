package gluecode;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import io.cucumber.java.en.Then;
import pages.ComposePage;
import utilities.TextFileUtility;

public class StepDefs2
{
	public sharedClass sh;
	public StepDefs2(sharedClass sh)
	{
		this.sh=sh;
	}
	@Then("compose a mail by taking data from text file")
	public void compose_mail_using_textfiledata() throws Exception
	{
		String fp=System.getProperty("user.dir")+"\\src\\test\\resources\\testdatafiles\\composemailto.txt";
		int lines=TextFileUtility.getLinescount(fp);
		sh.cp=new ComposePage(sh.driver,sh.wait);
		for(int i=0;i<lines;i++)
		{
			String p[]=TextFileUtility.getValueInTextFile(fp, i);
			sh.cp.clickCompose();
			sh.cp.fillToaddress(p[0]);
			sh.cp.fillSubject(p[1]);
			sh.cp.fillMessageBody(p[2]);
			sh.cp.attachFile(p[3]);
			sh.cp.sendMail();
			try {
				if(sh.cp.isMailSend())
				{
					sh.s.log("mail has been send");
					Assert.assertTrue(true);
				}
				else
				{
					sh.s.log("mail has not been send");
					Assert.assertTrue(false);
				}
			}
			catch(Exception ex)
			{
				byte[] b=sh.driver.getScreenshotAs(OutputType.BYTES);
				sh.s.attach(b, "image/png", ex.getMessage());
				Assert.assertTrue(false);
			}
		}
	}
	@Then("compose a mail by taking data from EXcel file using {string}")
	public void compose_using_excelfiledata(String fp) throws Exception
	{
		fp=System.getProperty("user.dir")+"\\"+fp;
		sh.cp=new ComposePage(sh.driver,sh.wait);
		sh.eu.OpenExcelFile(fp);
		try
		{
			sh.eu.opensheet("Sheet1");
			int nour=sh.eu.getRowCount();
			System.out.println(nour);
			int nouc=sh.eu.getColumnsCount(0);
			System.out.println(nouc);
			sh.eu.createResultsColumn(nouc);
			for(int i=1;i<nour;i++)
			{
				sh.cp.clickCompose();
				sh.cp.fillToaddress(sh.eu.getCellValue(i, 0));
				sh.cp.fillSubject(sh.eu.getCellValue(i, 1));
				sh.cp.fillMessageBody(sh.eu.getCellValue(i, 2));
				sh.cp.attachFile(sh.eu.getCellValue(i, 3));
				sh.cp.sendMail();
				if(sh.cp.isMailSend())
				{
					sh.s.log("file attached and mail has been send");
					sh.eu.setCellValue(i, nouc, "mail has been send");
					Assert.assertTrue(true);
				}
				else
				{
					byte[] b=sh.driver.getScreenshotAs(OutputType.BYTES);
					sh.s.attach(b, "image/png","mail has been send");
					sh.eu.setCellValue(i, nouc, "mail has been send");
					Assert.assertTrue(false);
				}
			}
		}
		catch(Exception ex)
		{
			sh.s.log(ex.getMessage());
		}
		sh.eu.saveAndClose();	
	}
}
