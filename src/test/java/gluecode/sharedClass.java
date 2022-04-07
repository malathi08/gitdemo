package gluecode;

import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.support.ui.FluentWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pages.ComposePage;
import pages.LogoutPage;
import pages.PasswordPage;
import pages.SigninPage;
import utilities.ExcelFileUtility;
import utilities.WebsitUtility;

public class sharedClass 
{
	public RemoteWebDriver driver;
    public FluentWait<RemoteWebDriver> wait; 
    public WebsitUtility wu;
    public ExcelFileUtility eu;
    public SigninPage sp;
    public ComposePage cp;
    public PasswordPage pp;
    public LogoutPage lop;
    public Scenario s;
    
    @Before
    public void method1(Scenario s)
    {
    	driver=null;
    	wait=null;
    	wu=new WebsitUtility();
    	eu=new ExcelFileUtility();
    	 this.s=s;
    	 s.log("\""+s.getName()+"\""+ " execution started");
    	 
    }
    @After
    public void method2(Scenario s)
    {
    	s.log("\""+s.getName()+" execution finished and status is "+s.getStatus().name());
    }
    
    
	
}
