package com.remita.tests.epayment;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

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

public class LoginValidateTest extends TestBase {
	//Validation steps:
	static String CaseAErr = "Invalid UserName or Password.";
	static String CaseBErr = "Invalid UserName or Password.";
	static String CaseCErr = "Invalid UserName or Password.";
	static String CaseDErr = "Invalid UserName or Password.";
	
	
	// 2nd Step to parameterization
	
		public String username;
		public String password;
		public String OrgId;
		public String positiveData;
		
		
		// 3rd Step
		
		public LoginValidateTest(String username, String password, String OrgId, String positiveData){
			this.username = username;
			this.password = password;
			this.OrgId = OrgId;
			this.positiveData = positiveData;

		}
	
	@Before
	public void beforeTest() throws IOException{
		System.out.println("Initializing the system");
		initialize();
		
		// xlsx file
		if(TestUtility.isSkip("LoginTest"))
			Assume.assumeTrue(false);
	}
	
	@Test
	public void loginTest() throws InterruptedException {
		
		// selenium code
		driver.get(CONFIG.getProperty("testSiteName"));
		// login
		/*
		 * Note: I am going to get my data from
		 * an Excel File later. Pls, dont forget!
		 */
		
		//driver.manage().window().maximize();
		
		TestUtility.doLogin(username, password, OrgId);
		if(isLoggedIn && positiveData.equals("Y")){

			System.out.println("User is able to login Successfully with Valid credential - "+username+"----"+password);
			Assert.assertTrue("User is able to login Successfully with Valid credential - "+username+"----"+password, true);
		}else if(!isLoggedIn && positiveData.equals("N")){
			// report error - able to login with wrong credential
			TestUtility.takeScreenShot("LoginUnsuccessful");
			System.out.println("User unable to login with wrong credential - "+username+"----"+password);
			Assert.assertTrue("User unable to login with wrong credential - "+username+"----"+password, false);
		}
		
		//TestUtility.logout();
			
	}
	
	
	@Parameters
	public static Collection<Object[]> dataSupplier(){
		System.out.println("Collecting data");
		
		// read data from xls file and write in into Object array.
		// The variable below is the xlsx worksheet name - LoginTest
				Object[][] data = TestUtility.getDataFromExcelSheet("LoginTest");
				return Arrays.asList(data);
		
	}


}
