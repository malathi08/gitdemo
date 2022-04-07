package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

public class WebsitUtility 
{
	public RemoteWebDriver OpenBrowser(String browsername)
	{
		RemoteWebDriver driver;
		if(browsername.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browsername.equalsIgnoreCase("opera"))
		{
			WebDriverManager.operadriver().setup();
			driver=new OperaDriver();
		}
		else if(browsername.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else  //ie
		{
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}
		return(driver);
	}
	public FluentWait<RemoteWebDriver> defineWait(RemoteWebDriver driver) throws Exception
	{
		String temp1=PropertyFileUtility.getValueInPropertyFile("maxwait");
		int value1=Integer.parseInt(temp1);
		String temp2=PropertyFileUtility.getValueInPropertyFile("interval");
		int value2=Integer.parseInt(temp2);
		FluentWait<RemoteWebDriver> wait=new FluentWait<RemoteWebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(value1));
		wait.pollingEvery(Duration.ofMillis(value2));
		return(wait);
	}
	public void launchSite(RemoteWebDriver driver,String x) throws Exception
	{
		String temp=PropertyFileUtility.getValueInPropertyFile(x);
		driver.get(temp);
		//driver.manage().window().maximize();
	}
	public String captureScreenShot(RemoteWebDriver driver) throws Exception
	{
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		String fn=System.getProperty("user.dir")+"\\target\\"+sf.format(dt)+".png";
		File src=driver.getScreenshotAs(OutputType.FILE);
		File dest=new File(fn);
		FileHandler.copy(src, dest);
		return(dest.getAbsolutePath());
	}
	public String fullPageScreenShot(RemoteWebDriver driver) throws Exception
	{
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		String fn=System.getProperty("user.dir")+"\\target\\"+sf.format(dt)+".png";
		File dest=new File(fn);
		AShot as=new AShot();
		ShootingStrategy shs=ShootingStrategies.viewportPasting(2000);
		Screenshot ss=as.shootingStrategy(shs).takeScreenshot(driver);
		ImageIO.write(ss.getImage(), "PNG", dest);
		return(dest.getAbsolutePath());
	}
	public void closeSite(RemoteWebDriver driver)
	{
		driver.quit();
	}
	public By getByFromWebElement(WebElement e)
	{
		String q[]=e.toString().split("->");
		String p[]=q[1].split(": ");
		String locatortype=p[0].trim();
		String locatorvalue=p[1].substring(0, p[1].length()-1).trim();
		By b=null;
		if(locatortype.equalsIgnoreCase("name"))
		{
			b=By.name(locatorvalue);
		}
		else if(locatortype.equalsIgnoreCase("id"))
		{
			b=By.id(locatorvalue);
		}
		else if(locatortype.equalsIgnoreCase("linktext"))
		{
			b=By.linkText(locatorvalue);
		}
		else if(locatortype.equalsIgnoreCase("partiallinktext"))
		{
			b=By.partialLinkText(locatorvalue);
		}
		else if(locatortype.equalsIgnoreCase("classname"))
		{
			b=By.className(locatorvalue);
		}
		else if(locatortype.equalsIgnoreCase("tagname"))
		{
			b=By.tagName(locatorvalue);
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			b=By.xpath(locatorvalue);
		}
		else //cssSelector
		{
			b=By.cssSelector(locatorvalue);
		}
		return(b);
	}
	public ArrayList<String> switchTonewTab(RemoteWebDriver driver)
	{
		driver.switchTo().newWindow(WindowType.TAB);
		Set<String> wn=driver.getWindowHandles();
		ArrayList<String> wh=new ArrayList<String>(wn);
		return(wh);
	}
	public void backToWindow(RemoteWebDriver driver,ArrayList<String> x)
	{
		driver.switchTo().window(x.get(0));
		
	}
	

}
