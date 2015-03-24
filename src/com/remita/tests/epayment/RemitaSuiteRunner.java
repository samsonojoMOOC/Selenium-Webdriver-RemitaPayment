package com.remita.tests.epayment;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.remita.tests.epayment.SmokeTest.PayPensionTest;
import com.remita.tests.epayment.SmokeTest.PayRRRBillsInvoicesTest;
import com.remita.tests.epayment.SmokeTest.PayRegisteredBillerTest;
import com.remita.tests.epayment.SmokeTest.PayTaxesTest;
import com.remita.tests.epayment.SmokeTest.PayVendorSwift103Test;
import com.remita.tests.epayment.SmokeTest.UploadPaymentVendorEchequeTest;
import com.remita.tests.epayment.SmokeTest.UploadSalariesAllTest;
import com.remita.tests.epayment.SmokeTest.UploadSalariesPensionTest;
import com.remita.tests.epayment.SmokeTest.UploadSalariesTaxTest;
import com.remita.tests.epayment.SmokeTest.UploadSalariesTest;


@RunWith(Suite.class)
@SuiteClasses({

	
LoginTest.class,
PayVendorApprovalTest.class,
LogoutTest.class

})

public class RemitaSuiteRunner {
	
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
