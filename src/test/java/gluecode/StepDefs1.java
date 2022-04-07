package gluecode;

import java.util.List;
//import java.util.Map;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
//import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ComposePage;
import pages.LogoutPage;
import pages.PasswordPage;
import pages.SigninPage;
import utilities.WebsitUtility;

public class StepDefs1
{
	public sharedClass sh;
	public StepDefs1(sharedClass sh)
	{
		this.sh=sh;
	}
	@When("title of the  Page is {string}")
	public void veritytitle(String expected)
	{
		String actual=sh.driver.getTitle();
		if(actual.contains(expected))
		{
			sh.s.log("title validation test passed");
			Assert.assertTrue(true);
		}
		else
		{
			byte[] b=sh.driver.getScreenshotAs(OutputType.BYTES);
    		sh.s.attach(b, "image/png", "uid test failed");
    		Assert.assertTrue(false);
		}
	}
	@Given("Launch site using {string} browser")
	public void launch_site_using_browser(String bn) throws Exception
	{
		sh.wu=new WebsitUtility();
	    sh.driver=sh.wu.OpenBrowser(bn);
	    sh.wait=sh.wu.defineWait(sh.driver);
	}
	@And("Lunch site")
	public void launchsite() throws Exception
	{
		sh.wu.launchSite(sh.driver, "url");
	}
	@When("enter userid as {string}")
	public void enter_userid_as(String uid)
	{
		sh.sp=new SigninPage(sh.driver,sh.wait);
	  sh.sp.clickSigin();
	  sh.sp.filluid(uid);
	}

	@When("click userid next button")
	public void click_userid_next_button() throws Exception
	{
	    sh.sp.clickNext();
	    Thread.sleep(5000);
	}

	@When("validate outcome related to userid criteria as {string}")
	public void validate_outcome_related_to_userid_criteria_as(String uidc) 
	{
	    try
	    {
	    	if(uidc.contains("blank") && sh.sp.isBlankInvaliduiderr())
	    	{
	    		sh.s.log("blank uid test passed");
	    		Assert.assertTrue(true);
	    	}
	    	else if(uidc.contains("invalid") && sh.sp.isBlankInvaliduiderr())
	    	{
	    		sh.s.log("balnk/invalid uid test passed");
	    		Assert.assertTrue(true);
	    	}
	    	else if(uidc.contains("valid") && sh.sp.isPasswordVisible())
	    	{
	    		sh.s.log("valid uid test passed");
	    		Assert.assertTrue(true);
	    	}
	    	else
	    	{
	    		byte[] b=sh.driver.getScreenshotAs(OutputType.BYTES);
	    		sh.s.attach(b, "image/png", "blank/uid test failed");
	    		Assert.assertTrue(false);
	    	}
	    }
	    catch(Exception ex)
	    {
	    	byte[] b=sh.driver.getScreenshotAs(OutputType.BYTES);
    		sh.s.attach(b, "image/png", "uid test failed");
    		Assert.assertTrue(false);
	    }
	}

	@When("enter password  {string}")
	public void enter_password(String pwd)
	{
		sh.pp=new PasswordPage(sh.driver,sh.wait);
		try {
			if (pwd.contains("N/A"))                               
			{
				sh.s.log("password test not required due to wrong uid");                           
				 Assert.assertTrue(true);// sh.pp.fillpwd(pwd);
			}
			else
			{
				sh.pp.fillpwd(pwd);
				Assert.assertTrue(true);
			}
		}
		catch(Exception ex)
		{
			byte[] b=sh.driver.getScreenshotAs(OutputType.BYTES);
    		sh.s.attach(b, "image/png", "uid test failed");
    		Assert.assertTrue(false);
		}
		
	}

	@When("click pwd next button")
	public void click_pwd_next_button()
	{
		try {
		if(sh.sp.isPasswordVisible())
		{
			sh.pp.clickNext();
		}
		}
		catch(Exception ex)
		{
			byte[] b=sh.driver.getScreenshotAs(OutputType.BYTES);
    		sh.s.attach(b, "image/png", ex.getMessage());
    		Assert.assertTrue(false);;
		}
	}

	@When("validate outcome related to password criteria as {string}")
	public void validate_outcome_related_to_password_criteria_as(String pc)
	{
		try
		{
			if(sh.pp.isBlankPassword() && pc.contains("blank"))
			{
				sh.s.log("blank pwd test passed");
	    		Assert.assertTrue(true);
			}
			else if(sh.pp.isPwdInvalid()  && pc.contains("invalid"))
			{
				sh.s.log("invalid pwd test passed");
	    		Assert.assertTrue(true);
			}
			else if(pc.contains("N/A"))
			{
				sh.s.log("wrong uid details");
	    		Assert.assertTrue(true);
			}
			else //if(sh.pp.isComposeVisible())
			{
				sh.pp.clickMailicon();
				if(sh.pp.isComposeVisible()) 
				{
				sh.s.log("valid password test passed");
				Assert.assertTrue(true);	
				}
				else
				{
				byte[] b=sh.driver.getScreenshotAs(OutputType.BYTES);
	    		sh.s.attach(b, "image/png", "pwd test failed");
	    		Assert.assertTrue(false);
				}
			}
		}
		catch(Exception ex)
		{
			byte[] b=sh.driver.getScreenshotAs(OutputType.BYTES);
    		sh.s.attach(b, "image/png", ex.getMessage());
    		Assert.assertTrue(false);
		}
	}
	@And("close yahoo site")
	@Then("quite site")
	public void quite_site() 
	{
	  sh.wu.closeSite(sh.driver);
	}
	@Then("validate login page")
	public void validate_login()
	{
		try
		{
			sh.pp.clickMailicon();
			if(sh.pp.isComposeVisible())
			{
				sh.s.log("login page validated");
				Assert.assertTrue(true);
			}
			else
			{
				sh.s.log("validation failed");
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
	@Then("compose a mail to different ids and validate")
	public void compose_mail(DataTable dt)
	{
		sh.cp=new ComposePage(sh.driver,sh.wait);
		List<Map<String,String>> m=dt.asMaps();
		System.out.println(m.size());
		for(int i=0;i<m.size();i++)
		{	
			sh.cp.clickCompose();
			sh.cp.fillToaddress(m.get(i).get("to"));
			sh.cp.fillSubject(m.get(i).get("sub"));
			sh.cp.fillMessageBody(m.get(i).get("messagebody"));
			sh.cp.attachFile(m.get(i).get("attach"));
			sh.cp.sendMail(); 
			try {
			if(sh.cp.isMailSend())
			{
				sh.s.log("mail has been send");
				Assert.assertTrue(true);
			}
			else
			{
				sh.s.log("mail has not been sent");
				Assert.assertTrue(false);
			}}
			catch(Exception ex)
			{
				byte[] b=sh.driver.getScreenshotAs(OutputType.BYTES);
	    		sh.s.attach(b, "image/png", ex.getMessage());
	    		Assert.assertTrue(false);
			}
		}
	}
	@Then("logout mail")
	public void dologout()
	{
		sh.lop=new LogoutPage(sh.driver,sh.wait);
		sh.lop.clickProfilePic();
		sh.lop.clickLogout();
	}
}
