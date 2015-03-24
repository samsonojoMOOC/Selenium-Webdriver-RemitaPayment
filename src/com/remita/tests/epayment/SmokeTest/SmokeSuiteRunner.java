package com.remita.tests.epayment.SmokeTest;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
















import com.remita.tests.epayment.LoginApprovalTest;
import com.remita.tests.epayment.LoginBankApprovalTest;
import com.remita.tests.epayment.LoginBankTest;
import com.remita.tests.epayment.LoginPayrollTest;
import com.remita.tests.epayment.LoginTellerApprovalTest;
import com.remita.tests.epayment.LoginTellerTest;
import com.remita.tests.epayment.LoginTest;
import com.remita.tests.epayment.LogoutTest;
import com.remita.tests.epayment.MassApprovalPaymentTest;
import com.remita.tests.epayment.MassRejectPaymentApprovalTest;
import com.remita.tests.epayment.PayVendorApprovalTest;
import com.remita.tests.epayment.PayVendorSingleTest;
import com.remita.tests.epayment.RejectPaymentApprovalTest;
import com.remita.tests.epayment.WelcomeTest;
import com.remita.tests.epayment.BankBranchOps.PaySalaryViaRRRApprovalTest;
import com.remita.tests.epayment.BankBranchOps.PaySalaryViaRRRTest;
import com.remita.tests.epayment.colletions.DefineCollectionRuleTest;
import com.remita.tests.epayment.colletions.EditCollectionRuleTest;
import com.remita.tests.epayment.colletions.ResendUnpaidInvoiceTest;
import com.remita.tests.epayment.colletions.SendOutInvoiceUploadTest;
import com.remita.tests.epayment.colletions.SendOutInvoicesTest;



@RunWith(Suite.class)
@SuiteClasses({

	LoginTellerApprovalTest.class,
	PaySalaryViaRRRApprovalTest.class,


/*
//LoginBankApprovalTest.class,
//ReverseWrongTransApprovalTest.class,
//LoginBankTest.class,
//ReverseWrongTransactionTest.class,

/*
LoginTest.class,
LoginApprovalTest.class,
RejectPaymentApprovalTest.class,
MassApprovalPaymentTest.class,
MassRejectPaymentApprovalTest.class,



//UserSetupTest.class,
//UserSetupUpdateTest.class,
//LoginApprovalTest.class,
//UserSetupApprovalTest.class,

//PaymentReceivedReportHTMLTest.class,
//ReportAllPaymentMadeHTMLTest.class,
//PayPensionTest.class,

/*
LoginApprovalTest.class,
RejectPaymentApprovalTest.class,
MassApprovalPaymentTest.class,
MassRejectPaymentApprovalTest.class,
LoginPayrollTest.class,
ManageStaffRecordTest.class,



//Bank Teller Payment via RRR
LoginTellerTest.class,
PaySalaryViaRRRTest.class,
LoginTellerApprovalTest.class,
PaySalaryViaRRRApprovalTest.class,


LoginBankTest.class,
BankBranchSetupTest.class,
LoginBankApprovalTest.class,
BankBranchSetupApprovalTest.class,

LoginBankTest.class,
BankBranchSetupTest.class,
LoginBankApprovalTest.class,
ReverseWrongTransApprovalTest.class,
ReverseWrongTransactionTest.class,
LoginApprovalTest.class,
StandingOrderApprovalTest.class,

	

LoginTest.class,
WelcomeTest.class,
DefineCollectionRuleTest.class,
EditCollectionRuleTest.class,
SendOutInvoicesTest.class,
SendOutInvoiceUploadTest.class,
ResendUnpaidInvoiceTest.class,
UploadPaymentVendorEchequeTest.class,
PayVendorSwift103Test.class,
PayVendorSingleTest.class,
PayRegisteredBillerTest.class,
PayRRRBillsInvoicesTest.class,

ContractPaymentUploadTest.class,
UploadSalariesTest.class,
UploadSalariesAllTest.class,
UploadSalariesPensionTest.class,
//UploadSalariesTaxTest.class, KIV - not worked today
//PayPensionTest.class,
PayTaxesTest.class,
StandingOrderDDDTest.class,
StandingOrderDateTest.class,
StandingOrderOthersTest.class,

*/
//LogoutTest.class

})

public class SmokeSuiteRunner {
	
	@BeforeClass
	public static void setUp() throws SQLException {
		System.out.println("*************Setting up database********************");
		/*
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "test";
		String driver = "com.mysql.jdbc.Driver"; //This is what connects Java to Database
		String userName = "root";
		String password = "root";
		
		try{
			Class.forName(driver).newInstance(); //create object of Driver class
			conn = DriverManager.getConnection(url+dbName, userName,password);
			//Connection will be established
			

			//********************Add row through INSERT**************
			PreparedStatement pstmt = conn.prepareStatement("insert into users values (?, ?, ?)");
			pstmt.setString(1, "Abdulmalik");
			pstmt.setString(2, "Abia");
			pstmt.setString(3, "M");
			
			int i = pstmt.executeUpdate();
			if(i==1){
				System.out.println("Inserted the record successfully");
			}
			
			System.out.println("****************************");
			// ******************PREPARED STATEMENT*******THIS IS MORE OF PARAMETERIZATION - USING TWO FIELDS*********
			pstmt = conn.prepareStatement("select * from users where name = ? and sex = ?");
			pstmt.setString(1, "B");
			pstmt.setString(2, "F");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				//print out the entire table below
				System.out.println(rs.getString(1) + "--- "+rs.getString(2)+" -- "+rs.getString(3));
			}

			
		}catch (Exception e){
			e.printStackTrace();
			
			
		}finally{
			conn.close();
		}*/
	}
	
	@AfterClass
	public static void tearDown() {
		System.out.println("****************Tearing down database*******************");
		
	}

}

