package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* Data is valid - login success - test pass - logout
 * Data is valid - login failed - test fail
 * 
 * Data is invalid - login success - test fail - logout
 * Data is invalid - login failed - test pass
 * 
 */

public class TC003_LoginDDT extends BaseClass {
	
	//data Provider method
	//getting data provider from difference
	@Test(dataProvider="LoginData" , dataProviderClass=DataProviders.class,groups="Datadriven")
	
		public void verify_loginDDT(String email, String pwd, String exp)
	{
		logger.info("**** Starting TC_003_LoginDDTest ****");
		
		try {
			
		//HomePage
				//Log info
				
				//create Object for Homepage class
				HomePage hp = new HomePage(driver);
				
				hp.clickMyAccount();		
				hp.clickLogin();
				
				//Login page
				LoginPage lp = new LoginPage(driver);
				//Test case Valid email and Password
				lp.setEmail(email);
				lp.setPassword(pwd);
				lp.clickLogin();
				
				//MyAccount Page
				MyAccountPage mac = new MyAccountPage(driver);
				boolean targetPage = mac.isMyAccountPageExists();
/* Data is valid - login success - test pass - logout
     			login failed - test fail
				  
Data is invalid - login success - test fail - logout
 login failed - test pass
		
 */				
			if(exp.equalsIgnoreCase("Valid"))
				{
					if(targetPage==true)
					{
						mac.clickLogout(); // Assertions always should be before the actions
						AssertJUnit.assertTrue(true);
						}
					else
					{
						AssertJUnit.assertTrue(false);
					}	
				}
				if(exp.equalsIgnoreCase("Invalid"))
				{
					if(targetPage==true)
					{
						mac.clickLogout();
						AssertJUnit.assertTrue(false);
					}				
					else
			{
				AssertJUnit.assertTrue(true);
			}
			}
		}
		catch(Exception e)
		{
			AssertJUnit.fail();
		}
			
			logger.info(" **** Ended TC003_LoginDDTest **** ");
}
	
}
