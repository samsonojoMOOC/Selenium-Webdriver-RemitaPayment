package com.remita.demo.epayment;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.remita.demo.epayment.BankBranchOps.CorporateRegistrationTest;
import com.remita.demo.epayment.BankBranchOps.PayPensionViaRRRTest;
import com.remita.demo.epayment.BankBranchOps.PayRegisteredBillerTellerTest;
import com.remita.demo.epayment.BankBranchOps.PaySalaryViaRRRTest;
import com.remita.demo.epayment.BankBranchOps.PayTaxesRRRTest;
import com.remita.demo.epayment.BankBranchOps.PayVendorsAndSuppliersTest;
import com.remita.demo.epayment.BankBranchOps.StandingOrderDateTest;
import com.remita.demo.epayment.BankBranchOps.StandingOrderFamilyTest;




@RunWith(Suite.class)
@SuiteClasses({

	
LoginTellerTest.class,
CorporateRegistrationTest.class,
//StandingOrderFamilyTest.class,
//StandingOrderDateTest.class,
//PayRegisteredBillerTellerTest.class,
//PayPensionViaRRRTest.class
//PayTaxesRRRTest.class
//PaySalaryViaRRRTest.class,
//PayVendorsAndSuppliersTest.class,
//LogoutTest.class

})

public class SuiteRunnerTeller {
	


}
