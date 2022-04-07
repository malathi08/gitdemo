package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class LogoutPage 
{
	public RemoteWebDriver driver;
	public FluentWait<RemoteWebDriver> wait;
	
	@FindBy(how=How.ID,using="ybarAccountMenuOpener")
	private WebElement profpic;
	
	@FindBy(how=How.XPATH,using="//*[text()='Sign out']")
	private WebElement logout;
	
	//constructor method
	public LogoutPage(RemoteWebDriver driver,FluentWait<RemoteWebDriver> wait)
	{
		AjaxElementLocatorFactory af=new AjaxElementLocatorFactory(driver,50);
		PageFactory.initElements(af, this);
		this.driver=driver;
		this.wait=wait;
	}
	public void clickProfilePic()
	{
		wait.until(ExpectedConditions.visibilityOf(profpic)).click();;
	}
	public void clickLogout()
	{
		wait.until(ExpectedConditions.visibilityOf(logout)).click();;
	}
}
