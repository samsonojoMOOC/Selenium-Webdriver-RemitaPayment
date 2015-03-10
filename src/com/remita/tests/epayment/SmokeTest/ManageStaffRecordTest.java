package com.remita.tests.epayment.SmokeTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.junit.After;
//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import tests.TestBase;
import Util.TestUtility;


//1st step to parameterization
	@RunWith(Parameterized.class)

	public class ManageStaffRecordTest extends TestBase {
		// 2nd Step to parameterization
		
		Logger ApplicationLogs = Logger.getLogger("devpinoyLogger");
		//ePayment details here
			
			
			public String title;
			public String surname;
			public String otherNames;
			public String maidenSurname;
			public String maritalStatus;
			public String gender;
			public String noOfChildren;
			public String noOfOtherDependants;
			public String mobilePhone;
			public String email;
			public String nationality;
			public String stateOfOrigin;
			public String townOfOrigin;
			public String lgaOfOrigin;
			public String religion;
			public String dateOfBirth;
			public String typeOfEmployment;
			public String accountType;
			public String confirmationDate;
			public String bank;
			public String acctNumber;
			public String residentialState;
			public String taxNumber;
			public String pfaName;
			public String pensionRSAPin;
			public String contactAddress;
			public String location;
			public String addressState;
			public String nexKinName;
			public String nexKinMobilePhone;
			public String nexKinAddress;
			public String nexKinRelationship;
			public String nexKinEmail;
			public String NHFId;
			public String NSITFId;
			public String NHIId;
			public String eduInstitutionName;
			public String eduInstTypeQualification;
			public String eduGrade;
			public String eduYearObtained;
			public String eduCourse;
			public String payGroup;
			public String effectiveSalaryStartDate;
			public String analysisDept;
			public String appraisalForm;
			public String positiveData;
				
			// 3rd Step
			public ManageStaffRecordTest(String title, String surname, String otherNames,String maidenSurname, String maritalStatus, 
										String gender, String noOfChildren, String noOfOtherDependants, String mobilePhone,
										String email, String nationality, String stateOfOrigin, String townOfOrigin,
										String lgaOfOrigin, String religion, String dateOfBirth, String typeOfEmployment,
										String accountType, String confirmationDate, String bank, String acctNumber,
										String residentialState, String taxNumber, String pfaName, String pensionRSAPin,
										String contactAddress, String location, String addressState, String nexKinName,
										String nexKinMobilePhone, String nexKinAddress, String nexKinRelationship,
										String nexKinEmail, String NHFId, String NSITFId, String NHIId, String eduInstitutionName,
										String eduInstTypeQualification, String eduGrade, String eduYearObtained, String eduCourse,
										String payGroup, String effectiveSalaryStartDate, String analysisDept, 
										String appraisalForm, String positiveData ){
				
				this.title = title;
				this.surname = surname;
				this.otherNames = otherNames;
				this.maidenSurname = maidenSurname; 
				this.maritalStatus = maritalStatus;
				this.gender = gender;
				this.noOfChildren =  noOfChildren;
				this.noOfOtherDependants = noOfOtherDependants;
				this.mobilePhone= mobilePhone;
				this.email= email;
				this.nationality = nationality;
				this.stateOfOrigin = stateOfOrigin;
				this.townOfOrigin = townOfOrigin;
				this.lgaOfOrigin= lgaOfOrigin;
				this.religion = religion;
				this.dateOfBirth = dateOfBirth;
				this.typeOfEmployment = typeOfEmployment;
				this.accountType = accountType;
				this.confirmationDate = confirmationDate;
				this.bank = bank;
				this.acctNumber = acctNumber;
				this.residentialState = residentialState;
				this.taxNumber = taxNumber;
				this.pfaName = pfaName;
				this.pensionRSAPin = pensionRSAPin;
				this.contactAddress = contactAddress;
				this.location = location;
				this.addressState = addressState;
				this.nexKinName = nexKinName;
				this.nexKinMobilePhone = nexKinMobilePhone;
				this.nexKinAddress = nexKinAddress;
				this.nexKinRelationship = nexKinRelationship;
				this.nexKinEmail = nexKinEmail;
				this.NHFId = NHFId;
				this.NSITFId = NSITFId;
				this.NHIId = NHIId;
				this.eduInstitutionName = eduInstitutionName;
				this.eduInstTypeQualification = eduInstTypeQualification;
				this.eduGrade = eduGrade;
				this.eduYearObtained = eduYearObtained;
				this.eduCourse = eduCourse;
				this.payGroup = payGroup;
				this.effectiveSalaryStartDate = effectiveSalaryStartDate;
				this.analysisDept = analysisDept;
				this.appraisalForm = appraisalForm;
				this.positiveData = positiveData;
			}
		
		@Before
		public void beforeTest() throws IOException{
			
			initialize();
			
			ApplicationLogs.debug("Initializing the ManageStaffRecord Test");
			
			// xlsx file
			if(TestUtility.isSkip("ManageStaffRecordTest"))
				Assume.assumeTrue(false);
		}
		@After
		  public void tearDown() throws Exception {
		    try {
		      driver.switchTo().defaultContent();
		    } catch (Exception e) {
		      e.getMessage();
		    }
		  }
		
		@SuppressWarnings("deprecation")
		@Test
		public void sendOutInvoicesTest() throws IOException{
			
								
			Actions act = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement menuPayment = driver.findElement(By.xpath("//*[@id='mainmenu']/li[6]/a"));
			act.moveToElement(menuPayment).click().build().perform();
			
			ApplicationLogs.debug("Manage Staff Record Module: Moved to the Main Menu");
			
			WebElement submenuPayment = driver.findElement(By.xpath("//a[text()='Manage Staff Records']"));
			act.moveToElement(submenuPayment).click().perform();
			ApplicationLogs.debug("Manage Staff Record Module:: Clicked the Manage Staff Record Menu");
			
			int size = driver.findElements(By.tagName("iframe")).size();
			System.out.println("Total frames in page- "+size);
			
			driver.switchTo().frame(0);
			int allElement = driver.findElements(By.tagName("input")).size();
			System.out.println("Total input in page - "+ allElement);
			
					
			
			ApplicationLogs.debug("Define Collection Rule Module: Selected Status");
			getObjectByName("btn_CollectRule_Save").click();
			ApplicationLogs.debug("Define Collection Rule Module: Clicked Save button");
			
			
			String actualText = driver.findElement(By.xpath("html/body/div[2]")).getText();
			
			
			String expectedText = "Saved Successfully";
			Assert.assertEquals("Define Collection Rule was unsuccessful", expectedText, actualText);
			//Assert.assertTrue("Define Collection Rule was unsuccessful", expectedText = actualText);
			ApplicationLogs.debug("Define Collection Rule Module: We verified that Collection Rule has been successful");
			
					
		
			driver.switchTo().defaultContent();
			

			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("Pay Vendor Test");
				System.out.println("Define Collection Rule was successfully");
				Assert.assertTrue("Define Collection Rule was successfully", true);
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("DefineCollectionRule Test");
				System.out.println("Define Collection Rule was unsuccessful");
				Assert.assertTrue("Define Collection Rule was unsuccessful", false);
			}

		}
		
		@Parameters
		public static Collection<Object[]> dataSupplier(){
			System.out.println("Collecting data");
			
			// read data from xls file and write in into Object array.
			// The variable below is the xlsx worksheet name - LoginTest
					Object[][] data = TestUtility.getDataFromExcelSheet("ManageStaffRecordTest");
					return Arrays.asList(data);
			
					
		}
		
		
}


