package com.remita.tests.epayment;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.remita.tests.epayment.BankBranchOps.CorporateRegistrationTest;
import com.remita.tests.epayment.BankBranchOps.PayPensionViaRRRTest;
import com.remita.tests.epayment.BankBranchOps.PayRegisteredBillerTellerTest;
import com.remita.tests.epayment.BankBranchOps.PaySalaryViaRRRTest;
import com.remita.tests.epayment.BankBranchOps.PayTaxesRRRTest;
import com.remita.tests.epayment.BankBranchOps.PayVendorsAndSuppliersTest;
import com.remita.tests.epayment.BankBranchOps.StandingOrderDateTest;
import com.remita.tests.epayment.BankBranchOps.StandingOrderFamilyTest;




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
