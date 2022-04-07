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

public class ComposePage
{
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	
	@FindBy(how=How.LINK_TEXT,using="Compose")
	private WebElement comps;
	
	@FindBy(how=How.ID,using="message-to-field")
	private WebElement to;
	
	@FindBy(how=How.XPATH,using="//input[@aria-label='Subject']")
	private WebElement subject;
	
	@FindBy(how=How.XPATH,using="//*[@aria-label='Message body']")
	private WebElement msgbody;
	
	@FindBy(how=How.XPATH,using="//input[@title='Attach files']")
	private WebElement attach;
	
	@FindBy(how=How.XPATH,using="//div[@role='progressbar']")
	private WebElement loading;
	
	@FindBy(how=How.XPATH,using="//span[text()='Send']/parent::button")
	private WebElement send;
	
	@FindBy(how=How.XPATH,using="//div[@role='status']/descendant::span[1]")
	private WebElement msg;
	
	//constructor method
	public ComposePage(RemoteWebDriver driver,FluentWait<RemoteWebDriver> wait)
	{
		AjaxElementLocatorFactory af=new AjaxElementLocatorFactory(driver,50);
		PageFactory.initElements(af, this);
		this.driver=driver;
		this.wait=wait;
	}
	
	//operation methods
	public void clickCompose()
	{
		wait.until(ExpectedConditions.visibilityOf(comps)).click();
	}
	public void fillToaddress(String x)
	{
		wait.until(ExpectedConditions.visibilityOf(to)).sendKeys(x);
	}
	public void fillSubject(String x)
	{
		wait.until(ExpectedConditions.visibilityOf(subject)).sendKeys(x);
	}
	public void fillMessageBody(String x)
	{
		wait.until(ExpectedConditions.visibilityOf(msgbody)).sendKeys(x);
	}
	public void attachFile(String fp)
	{
		WebsitUtility su=new WebsitUtility();
		By b=su.getByFromWebElement(attach);
		wait.until(ExpectedConditions.presenceOfElementLocated(b)).sendKeys(fp);
	}
	public boolean isFileAttached()
	{
		try
		{
			try {
			wait.until(ExpectedConditions.visibilityOf(loading));
			wait.until(ExpectedConditions.invisibilityOf(loading));
			}
			catch(Exception ex)
			{
				wait.until(ExpectedConditions.invisibilityOf(loading));
			}
			return(true);
		}
		catch(Exception ex)
		{
			return(false);
		}
	}
	public void sendMail()
	{
		
		wait.until(ExpectedConditions.visibilityOf(send)).click();;
	}
	public boolean isMailSend()
	{
		try
		{
			try {
			wait.until(ExpectedConditions.visibilityOf(msg));
			
			}
			catch(Exception ex)
			{
				wait.until(ExpectedConditions.invisibilityOf(msg));
			}
			return(true);
		}
		catch(Exception ex)
		{
			return(false);
		}
		
	}
	
}
