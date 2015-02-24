package tests;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import Util.TestUtility;
import datatable.Xls_Reader;

public class TestBase {
	
	// Initializing the properties file reading
	/*
	 * I am declaring these properties globally and as static so
	 * so that I can call them directly from anywhere in the package
	 * TestBase class by directly writing TestBase.dr, 
	 * TestBase.CONFIG, TestBase.OR, etc for instance
	 */
	public static Properties CONFIG=null;
	public static Properties OR=null;
	public static WebDriver dr= null;
	public static EventFiringWebDriver driver=null;
	public static boolean isLoggedIn=false;
	public static Xls_Reader datatable=null;
	
	
	public void initialize() throws IOException{
		if (driver == null){		
		// CONFIG property file
		CONFIG= new Properties();
		FileInputStream fn = new FileInputStream(System.getProperty("user.dir")+"//src//config/config.properties");
		CONFIG.load(fn);
		
		// OR property file
		OR= new Properties();
		fn = new FileInputStream(System.getProperty("user.dir")+"//src//config/OR.properties");
		//CONFIG.load(fn);
		OR.load(fn);
		
			
		// Initializing the WebDriver and EventFiringWebDriver
		
			if (CONFIG.getProperty("browser").equals("Firefox")){
				Proxy proxy = new Proxy();
				proxy.setProxyAutoconfigUrl("192.9.200.10:3128");				
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability(CapabilityType.BROWSER_NAME,	"firefox");
				cap.setCapability(CapabilityType.PROXY, proxy);				
				dr = new FirefoxDriver(cap);
			}else if(CONFIG.getProperty("browser").equals("IE")){
				System.setProperty("webdriver.ie.driver", "C://Users//USER//Downloads//IEDriverServer_x64_2.39.0//IEDriverServer.exe");
				dr = new InternetExplorerDriver();
			}else if(CONFIG.getProperty("browser").equals("Chrome")){
				System.setProperty("webdriver.chrome.driver", "src\\config\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("no-sandbox");
				options.setBinary("src\\config\\chromedriver.exe");				
				dr = new ChromeDriver();
			}else if(CONFIG.getProperty("browser").equals("Headless")){
				dr  = new HtmlUnitDriver(BrowserVersion.FIREFOX_24);
			}
			
			
			// I will be using EventFiringWebDriver so that we can implement Listeners, MouseClick,etc
			// Passing WebDriver object - dr into it.	
			driver = new EventFiringWebDriver(dr); 
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		} 
	}
	
	/** 
	This method is used to easily get WebElement(s) directly from Object Repository (OR.properties)file By.XPath.
	If the object is not found, the method handles the error accordingly and screen-shot of the error is captured in each module
	so that the test step(s) can be easily re-produced to provide detailed information to especially the developers and automation guys    
	@param this method accepts a single parameter xPathKey of the object in question to find and locate the WebElement 
	@return It returns an object type of WebElement.  
	*/
	public static WebElement getObjectByXpath(String xpathKey) {
		try{
			return driver.findElement(By.xpath(OR.getProperty(xpathKey)));
		}catch (Throwable t){
			//report the error if any xpathKey is not found and take screenshot
			TestUtility.takeScreenShot(xpathKey);
			Assert.assertTrue(t.getMessage(), false);
			
			return null;
		}
		
		/*Note: Yusuf should give me most used Methods in By Class so that I can write different getObject Methods for each.
		 * I can equally rename getObject to getObjectByXpath(), getObjectById(), getObjectByClass(), etc.
		 * so that we can cover almost every possibilities here 
		 */	
	}
	
	public static WebElement getObjectClassName(String className){
		try{
			return driver.findElement(By.className(OR.getProperty(className)));
		}catch (Throwable t){
			TestUtility.takeScreenShot(className);
			Assert.assertTrue(t.getMessage(), false);
			
			return null;
		}
	}
	
	public static WebElement getObjectCssSelector(String cssSelectorKey){
		try{
		return driver.findElement(By.cssSelector(OR.getProperty(cssSelectorKey)));	
		}catch (Throwable t){
			TestUtility.takeScreenShot(cssSelectorKey);
			Assert.assertTrue(t.getMessage(), false);
			
			return null;
		}
		
		
	}
	
	public static WebElement getObjectById(String idKey){
		try{
			return driver.findElement(By.id(OR.getProperty(idKey)));
		}catch (Throwable t){
			TestUtility.takeScreenShot(idKey);
			Assert.assertTrue(t.getMessage(), false);
			
			return null;
		}
		
	}
	
	public static WebElement getObjectByName(String nameKey){
		try{
			return driver.findElement(By.name(OR.getProperty(nameKey)));
		}catch (Throwable t){
		TestUtility.takeScreenShot(nameKey);
		Assert.assertTrue(t.getMessage(), false);
		
		return null;
		}
	}
		
	
	public static WebElement getObjectByLinkText(String linkTextKey){
		try{
			return driver.findElement(By.name(OR.getProperty(linkTextKey)));
		}catch (Throwable t){
		TestUtility.takeScreenShot(linkTextKey);
		Assert.assertTrue(t.getMessage(), false);
		
		return null;
		}
	}
	
	public static WebElement getObjectByParticalLinkText(String partialLinkTextKey){
		try{
		return driver.findElement(By.name(OR.getProperty(partialLinkTextKey)));
		}catch (Throwable t){
			TestUtility.takeScreenShot(partialLinkTextKey);
			Assert.assertTrue(t.getMessage(), false);
			
			return null;
		}
	}
	
	public static WebElement getObjectByTagName(String tagNameKey){
		try{
		return driver.findElement(By.name(OR.getProperty(tagNameKey)));
		}catch (Throwable t){
			TestUtility.takeScreenShot(tagNameKey);
			Assert.assertTrue(t.getMessage(), false);
			
			return null;
		}
	}
	
	/**
	13.*
	14.* @return True if JavaScript Alert is present on the page otherwise false
	15.*/
	public static boolean isAlertPresent(){
	try{
	driver.switchTo().alert();
	return true;
	}catch(NoAlertPresentException ex){
	return false;
	}
	
	}
	
	//true - present
	//false - not present
	public static boolean isElementPresent(String element_xpath){
	  int count = driver.findElements(By.xpath(element_xpath)).size();
	  if(count == 0){
	     return false;
	    }else{ 
	     return true;
	    }
	}

}
