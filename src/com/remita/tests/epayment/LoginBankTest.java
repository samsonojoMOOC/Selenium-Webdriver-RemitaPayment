package com.remita.tests.epayment;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
//import org.openqa.selenium.By;





import tests.TestBase;
import Util.TestUtility;

//1st step to parameterization
@RunWith(Parameterized.class)

public class LoginBankTest extends TestBase {
	// 2nd Step to parameterization
	
	Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
	
		public String username;
		public String password;
		public String OrgId;
		public String positiveData;
		
		
		// 3rd Step
		
		public LoginBankTest(String username, String password, String OrgId, String positiveData){
			this.username = username;
			this.password = password;
			this.OrgId = OrgId;
			this.positiveData = positiveData;

		}
	
		
		
		
		
	@Before
	public void beforeTest() throws IOException{
		
		System.out.println("Initializing the system");
		ApplicationLogs.debug("Initializing the system");
	
		initialize();
		
		
		// xlsx file
		if(TestUtility.isSkip("LoginBankTest"))
			Assume.assumeTrue(false);
	}
	
	@Test
	public void loginBankTest() throws InterruptedException {
		// selenium code
		
		driver.get(CONFIG.getProperty("testSiteName"));
		
		ApplicationLogs.debug("Executing the Application Under Test");
		
		
		driver.manage().window().maximize();
		
		TestUtility.doLogin(username, password, OrgId);
		
		ApplicationLogs.debug("Login In...");
		
		if(isLoggedIn && positiveData.equals("Y")){

			Assert.assertTrue("User is able to login Successfully with Valid credential - "+username+"----"+password, true);
			ApplicationLogs.debug("User is able to login Successfully with Valid credential - "+username+"----"+password);
		}else if(!isLoggedIn && positiveData.equals("N")){
			// report error - able to login with wrong credential
			TestUtility.takeScreenShot("LoginUnsuccessful");
			Assert.assertTrue("User unable to login with wrong credential - "+username+"----"+password, false);
			ApplicationLogs.debug("User unable to login with wrong credential - "+username+"----"+password);
			
			
		}
		
			
	}
	
	
	@Parameters
	public static Collection<Object[]> dataSupplier(){
		System.out.println("Collecting data");
		
		
		// read data from xls file and write in into Object array.
				Object[][] data = TestUtility.getDataFromExcelSheet("LoginBankTest");
				return Arrays.asList(data);
				
		
	}


}
