package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import utilities.WebsitUtility;

public class PasswordPage
{
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	
	@FindBy(how=How.NAME,using="password")
	private WebElement pwd;
	
	@FindBy(how=How.NAME,using="verifyPassword")
	private WebElement next;
	
	@FindBy(how=How.XPATH,using="//div[@title='Mail']")
	private WebElement mail;
	//error-msg
	@FindBy(how=How.XPATH,using="//p[contains(@data-error,'ERROR_EMPTY_PASSWORD')]")
	private WebElement pwderr;
	
	@FindBy(how=How.XPATH,using="//p[contains(@data-error,'ERROR_INVALID_PASSWORD')]")
	private WebElement pwdinvalid;
	
	//constructor method
		public PasswordPage(RemoteWebDriver driver,FluentWait<RemoteWebDriver> wait)
		{
		//	AjaxElementLocatorFactory af=new AjaxElementLocatorFactory(driver,50);
			PageFactory.initElements(driver, this);
			this.driver=driver;
			this.wait=wait;
		}
		//operational methods
		public void fillpwd(String x)
		{
			wait.until(ExpectedConditions.visibilityOf(pwd)).sendKeys(x);
		}
		public void clickNext()
		{
			wait.until(ExpectedConditions.visibilityOf(next)).click();
		}
		public void clickMailicon()
		{
			wait.until(ExpectedConditions.visibilityOf(mail)).click();
		}
		public boolean isBlankPassword()
		{
			try
			{	
				wait.until(ExpectedConditions.visibilityOf(pwderr));
				return(true);
			}
			catch(Exception ex)
			{
				return(false);
			}
		}
		public boolean isPwdInvalid()
		{
			try
			{
				wait.until(ExpectedConditions.visibilityOf(pwdinvalid));
				return(true);
			}
			catch(Exception ex)
			{
				return(false);
			}
		}
		public boolean isComposeVisible()
		{
			try
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Compose")));
				return(true);
			}
			catch(Exception ex)
			{
				return(false);
			}
		}

}
