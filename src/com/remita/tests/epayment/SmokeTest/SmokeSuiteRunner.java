package com.remita.tests.epayment.SmokeTest;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.remita.tests.epayment.LoginApprovalTest;
import com.remita.tests.epayment.LogoutTest;
import com.remita.tests.epayment.PayVendorApprovalTest;



@RunWith(Suite.class)
@SuiteClasses({

LoginApprovalTest.class,
PayVendorApprovalTest.class,
LogoutTest.class

/*
LoginTest.class,
PayVendorEcheckTest.class,
PayVendorSwift103Test.class,
PayRegisteredBillerTest.class,
PayRRRBillsInvoicesTest.class,
UploadSalariesTest.class,
UploadSalariesAllTest.class,
UploadSalariesPensionTest.class,
//UploadSalariesTaxTest.class, KIV - not worked today
//PayPensionTest.class,
PayTaxesTest.class,
LogoutTest.class
*/
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

