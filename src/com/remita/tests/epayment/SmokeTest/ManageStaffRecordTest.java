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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
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
			public String dobMonth;
			public String dobYear;
			public String employmentDate;
			public String jobTitle;
			public String typeOfEmployment;
			public String accountType;
			public String employmentTenure;
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
			public String grossAmount;
			public String tax;
			public String positiveData;
				
			// 3rd Step
			public ManageStaffRecordTest(String title, String surname, String otherNames,String maidenSurname, String maritalStatus, 
										String gender, String noOfChildren, String noOfOtherDependants, String mobilePhone,
										String email, String nationality, String stateOfOrigin, String townOfOrigin,
										String lgaOfOrigin, String religion, String dobMonth, String dobYear, String employmentDate, String typeOfEmployment,
										String jobTitle, String accountType, String employmentTenure, String bank, String acctNumber,
										String residentialState, String taxNumber, String pfaName, String pensionRSAPin,
										String contactAddress, String location, String addressState, String nexKinName,
										String nexKinMobilePhone, String nexKinAddress, String nexKinRelationship,
										String nexKinEmail, String NHFId, String NSITFId, String NHIId, String eduInstitutionName,
										String eduInstTypeQualification, String eduGrade, String eduYearObtained, String eduCourse,
										String payGroup, String effectiveSalaryStartDate, String analysisDept, 
										String appraisalForm, String grossAmount, String tax, String positiveData ){
				
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
				this.dobMonth = dobMonth;
				this.dobYear = dobYear;
				this.employmentDate = employmentDate;
				this.jobTitle = jobTitle;
				this.typeOfEmployment = typeOfEmployment;
				this.accountType = accountType;
				this.employmentTenure = employmentTenure;
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
				this.grossAmount = grossAmount;
				this.tax = tax;
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
		public void manageStaffRecordTest() throws IOException, InterruptedException{
								
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
			
			
			int allElement = driver.findElements(By.tagName("div")).size();
			//driver.findElements(By.)
			System.out.println("Total table in page - "+ allElement);
			
			driver.navigate().to("http://192.9.200.11/remita/payroll/staffrecorddata.htm?actionType=N");
			
			new FluentWait <WebDriver> (driver)
			.withTimeout(120, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.until(ExpectedConditions.visibilityOfElementLocated(By.name("title")));
			
			new Select(getObjectByName("txt_StaffRecord_Title")).selectByValue(title);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Title");
			
			getObjectByName("txt_StaffRecord_Surname").sendKeys(surname);
			ApplicationLogs.debug("Manage Staff Record Module: Inserted Employees Surname");
			
			getObjectByName("txt_StaffRecord_Othername").sendKeys(otherNames);
			ApplicationLogs.debug("Manage Staff Record Module: Inserted Employees Other Names");
			
			getObjectByName("txt_StaffRecord_MaidenSurname").sendKeys(maidenSurname);
			ApplicationLogs.debug("Manage Staff Record Module: Inserted Employees Maiden Surname");
			
			new Select(getObjectByName("drd_StaffRecord_MaritalStatus")).selectByValue(maritalStatus);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Marital Status");
			
			new Select(getObjectByName("drd_StaffRecord_Gender")).selectByValue(gender);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Gender");
			
			getObjectByName("txt_StaffRecord_NoOfChildren").sendKeys(noOfChildren);
			ApplicationLogs.debug("Manage Staff Record Module: Inserted Employees No of Children");
			
			getObjectByName("txt_StaffRecord_OtherDependents").sendKeys(noOfOtherDependants);
			ApplicationLogs.debug("Manage Staff Record Module: Inserted Employees No of Other Dependants");
			
			getObjectByName("txt_StaffRecord_PhoneNo").sendKeys(mobilePhone);
			ApplicationLogs.debug("Manage Staff Record Module: Inserted Employees Mobile Phone");
			
			getObjectByName("txt_StaffRecord_Email").sendKeys(email);
			ApplicationLogs.debug("Manage Staff Record Module: Inserted Employees Email Address");
			
			new Select(getObjectByName("drd_StaffRecord_Nationality")).selectByValue(nationality);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Nationality");
			
			new Select(getObjectByName("drd_StaffRecord_StateOfOrigin")).selectByValue(stateOfOrigin);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees State of Origin");
			
			getObjectByName("txt_StaffRecrod_TownOfOrigin").sendKeys(townOfOrigin);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Town of Origin");
			
			new FluentWait <WebDriver> (driver)
			.withTimeout(120, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.until(ExpectedConditions.visibilityOfElementLocated(By.name("lga")));
						
			new Select(getObjectByName("drd_StaffRecord_LgaOfOrigin")).selectByValue(lgaOfOrigin);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees LGA of Origin");
			
			new Select(getObjectByName("drd_StaffRecord_Religion")).selectByValue(religion);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Religion");
			
			getObjectByName("txt_StaffRecord_BirthDate").click();
			new Select(getObjectByXpath("drd_StaffRecord_month")).selectByValue(dobMonth);
			new Select(getObjectByXpath("drd_StaffRecord_year")).selectByValue(dobYear);
			getObjectByXpath("lnk_StaffRecord_07").click();
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Date of Birth");
			
			getObjectByName("txt_StaffRecord_EmploymentDate").clear();
			getObjectByName("txt_StaffRecord_EmploymentDate").sendKeys(employmentDate + Keys.TAB);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Employment Date");
			
			getObjectByName("txt_StaffRecord_JobTitle").sendKeys(jobTitle);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Job Title");
			
			new Select(getObjectByName("drd_StaffRecord_EmploymentType")).selectByValue(typeOfEmployment);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Type of Employment");
			
			new Select(getObjectByName("drd_StaffRecord_AcctType")).selectByValue(accountType);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Account Type");
			
			getObjectByName("txt_StaffRecord_EmploymentTenure").sendKeys(employmentTenure);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Employment Tenure");
			
			new Select(getObjectByName("drd_StaffRecord_Bank")).selectByValue(bank);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Bank");
			
			getObjectByName("txt_StaffRecord_AcctNo").sendKeys(acctNumber);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Account Number");
			
			new Select(getObjectByName("drd_StaffRecord_ResidentialState")).selectByValue(residentialState);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Residential State");
			
			getObjectByName("txt_StaffRecord_TaxNo").sendKeys(taxNumber);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Tax Number");
			
			new Select(getObjectByName("drd_StaffRecord_PfaNo")).selectByValue(pfaName);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees PFA Name");
			
			getObjectByName("txt_StaffRecord_PensionRSAPIN").sendKeys(pensionRSAPin);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Pension RSA PIN");
			
			getObjectByName("txt_StaffRecord_ContactAddress").sendKeys(contactAddress);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Contact Address");
			
			getObjectByName("txt_StaffRecord_Location").sendKeys(location);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Location");
			
			new Select(getObjectByName("drd_StaffRecord_AddressState")).selectByValue(addressState);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees State Address");
			
			getObjectByName("txt_StaffRecord_NextOfKinName").sendKeys(nexKinName);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Next of Kin Name");
			
			new Select(getObjectByName("drd_StaffRecord_NextOfKinRelationship")).selectByValue(nexKinRelationship);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Next of Kin Relationship");
			
			getObjectByName("txt_StaffRecord_NextOfKinMobilePhone").sendKeys(nexKinMobilePhone);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Next of Kin Mobile Phone");
			
			getObjectByName("txt_StaffRecord_NextOfKinEmail").sendKeys(nexKinEmail);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Next of Kin Email");
			
			getObjectByName("txt_StaffRecord_NextOfKinAddress").sendKeys(nexKinAddress);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Next of Kin Address");
			
			getObjectByName("chk_StaffRecord_Scheme").click();
			ApplicationLogs.debug("Manage Staff Record Module: Checked Employees Schemes");
			
			getObjectByName("txt_StaffRecord_NhfNo").sendKeys(NHFId);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees NHF Number");
			
			getObjectByName("txt_StaffRecord_NsitfNo").sendKeys(NSITFId);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees NSITF Id");
			
			getObjectByName("txt_StaffRecord_NhisNo").sendKeys(NHIId);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees NHI Id");
			
			getObjectByName("btn_StaffRecord_AddNewQualification").click();
			ApplicationLogs.debug("Manage Staff Record Module: Clicked Add New Qualification");
			
			getObjectByName("txt_StaffRecord_InstitutionName").sendKeys(eduInstitutionName);
			ApplicationLogs.debug("Manage Staff Record Module: Inserted Employees Education Institution Name");
			
			new Select(getObjectByName("drd_StaffRecord_InstitutionType")).selectByValue(eduInstTypeQualification);
			ApplicationLogs.debug("Manage Staff Record Module: Inserted Employees Education Type Qualification");
			
			new Select(getObjectByName("drd_StaffRecord_Qualitifcation")).selectByValue(eduGrade);
			ApplicationLogs.debug("Manage Staff Record Module: Inserted Employees Education Qualification Grade");
			
			new Select(getObjectByName("drd_StaffRecord_Grade")).selectByValue(eduGrade);
			ApplicationLogs.debug("Manage Staff Record Module: Inserted Employees Education Grade");
			
			getObjectByName("txt_StaffRecord_YearObtained").sendKeys(eduYearObtained);
			ApplicationLogs.debug("Manage Staff Record Module: Inserted Employees Education Year Obtained");
			
			new Select(getObjectByName("drd_StaffRecord_Course")).selectByValue(eduCourse);
			ApplicationLogs.debug("Manage Staff Record Module: Inserted Employees Education Course");
			
			new Select(getObjectByName("drd_StaffRecord_PayGroup")).selectByValue(payGroup);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Employees Pay Group");
			
			getObjectByName("txt_StaffRecord_EffectiveSalaryDate").sendKeys(effectiveSalaryStartDate);
			ApplicationLogs.debug("Manage Staff Record Module: Inserted Employees Effective Salary Start Date");
			
			new Select(getObjectByName("drd_StaffRecord_Dept")).selectByValue(analysisDept);
			ApplicationLogs.debug("Manage Staff Record Module: Selected Analysis Department");
			
			getObjectByName("chk_StaffRecord_UseAppraisal").click();
			ApplicationLogs.debug("Manage Staff Record Module: Checked User Appraisal");
			
			getObjectByName("txt_StaffRecord_AppraisalForm").sendKeys(appraisalForm);
			ApplicationLogs.debug("Manage Staff Record Module: Inserted Employees Appraisal");
			
			new FluentWait <WebDriver> (driver)
			.withTimeout(140, TimeUnit.SECONDS)
			.pollingEvery(5, TimeUnit.SECONDS)
			.until(ExpectedConditions.visibilityOfElementLocated(By.name("grossAmount")));
			
			getObjectByName("txt_StaffRecord_GrossAmount").clear();
			getObjectByName("txt_StaffRecord_GrossAmount").sendKeys(grossAmount);
			
			getObjectByXpath("txt_StaffRecord_Tax").clear();
			getObjectByXpath("txt_StaffRecord_Tax").sendKeys(tax);
			
			getObjectByName("btn_StaffRecord_Save").click();
			ApplicationLogs.debug("Manage Staff Record Module: Clicked Save Record");
			
				
			
			String actualText = driver.findElement(By.xpath("html/body/div[2]")).getText();
						
			Assert.assertTrue("Staff Record was unsuccessfully submitted", actualText.startsWith("Staff Record successfully saved with StaffNumber"));
			ApplicationLogs.debug("Manage Staff Record Module: We verified that Staff Record has been successful");
			
			driver.get("http://192.9.200.11/remita/home.do#");
		
			driver.switchTo().defaultContent();
			

			if(positiveData.equals("Y")){
				// let's report error
				//TestUtility.takeScreenShot("Pay Vendor Test");
				ApplicationLogs.debug("Manage Staff Record was successful");
				Assert.assertTrue("Manage Staff Record was successfully done", true);
			}else if(positiveData.equals("N")){
				// report error - able to login with wrong credential
				TestUtility.takeScreenShot("ManageStaffRecord Test");
				System.out.println("Manage Staff Record was unsuccessful");
				Assert.assertTrue("Manage Staff Record was unsuccessful", false);
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


