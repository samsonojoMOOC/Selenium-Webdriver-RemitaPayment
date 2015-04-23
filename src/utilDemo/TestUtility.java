package utilDemo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import testsDemo.TestBase;
import datatable.Xls_Reader;

public class TestUtility extends TestBase {
	
	public static void doLogin(String username, String password, String OrgId) throws InterruptedException{
		
		if(isLoggedIn){
			//return;
			logout();
		}
		//getObjectByName("rdo_CorpPersonal").click();
		getObjectByXpath("txt_UserName").sendKeys(username);
		getObjectById("txt_Password").sendKeys(password);
		getObjectById("txt_OrganizationID").sendKeys(OrgId);
		getObjectById("btn_Login").click();
		

		
		
		Thread.sleep(5000l);
		
		try{
			//I dont want this line to report an error- hence I Will not use getObject instead I used findElement
			
			String displayedUserName=driver.findElement(By.xpath(OR.getProperty("txt_loggedInUser"))).getText();			
			
			
			if(displayedUserName.contains(username)){
				isLoggedIn=true;
			}else{
				isLoggedIn=false;
			}
		}catch (Throwable t){
			isLoggedIn=false;
		}
		
		
	}
	public static void logout(){
		if(isLoggedIn){
			
			//Start Log out
			Actions act = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			
			new FluentWait<WebDriver>(driver)
			.withTimeout(120, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.ignoring(NoSuchElementException.class)	
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='headerbar']/div[2]/div/div/div[2]/div/div[2]/ul/li/a/strong/small[2]")));
			
			
			
			WebElement txtUser = driver.findElement(By.xpath("//*[@id='headerbar']/div[2]/div/div/div[2]/div/div[2]/ul/li/a/strong/small[2]"));
			
			act.moveToElement(txtUser).click().build().perform();
			
			act = new Actions(driver);
			
			//Logout
			WebElement lnkLogOut = driver.findElement(By.xpath("//*[@id='headerbar']/div[2]/div/div/div[2]/div/div[2]/ul/li/ul/li[6]/a"));
			act.moveToElement(lnkLogOut).build().perform();

			
			// after clicking sign_Out link, then I need to make 
			// the state of this variable - isLoggedIn false
			isLoggedIn=false;
			driver.quit();
		}
	}
	

	// get the skip condition based on the xlsx file of Test Cases
	// true - N
	// false - Y
	public static boolean isSkip(String testCase){
		
		for(int rowNum=2 ; rowNum<=datatable.getRowCount("Test Cases");rowNum++){
			if(testCase.equals(datatable.getCellData("Test Cases", "TCID", rowNum))){
				if(datatable.getCellData("Test Cases", "Runmode", rowNum).equals("Y")){
					return false;
				}
					
				else{
					return true;
				}
					
			}
		}
		
		return false;
		// if for loop ends and nothing is found - skip this test was not found in XSLX file
	}
	
	// a method to get the data from xls file
	public static Object[][] getDataFromExcelSheet(String testName){
		// return test data
		// read test data from xlsx
		if(datatable==null){
			// Load the MS XLSX file sheet
		datatable = new Xls_Reader(System.getProperty("user.dir")+"//src//configDemo//ePaymentConnector.xlsx");
		}
		
		
		int rows=datatable.getRowCount(testName)-1;
		if(rows <=0){
			// I am assuming there is test but without test data.
			// so the test should run at least once
			Object[][] testData = new Object[1][0];
			return testData;
			
		}
		rows = datatable.getRowCount(testName); //3
		int cols = datatable.getColumnCount(testName);
		System.out.println("Test Name -- "+testName);
		System.out.println("Total rows -- "+rows);
		System.out.println("Tottal cols -- "+cols);
		Object data[][] = new Object[rows-1][cols];
		
		// start iterating from number 2 bcos data start there
		for(int rowNum = 2 ; rowNum <= rows ; rowNum++){
			for(int colNum=0 ; colNum < cols ; colNum++){
				data[rowNum-2][colNum] = datatable.getCellData(testName, colNum, rowNum);
			}
		}
		
		return data;
		
	}
	
	// store screenshots
	public static void takeScreenShot(String fileName){
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    try {
	    	
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"\\screenshots\\"+fileName+new SimpleDateFormat("yyyyMMddhhmm").format(new Date())+".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    /*
	     * NOTE: I will be calling this method everywhere in my test cases where there are errors especially in "my TestBase class"
	     */
	    
	}

}
