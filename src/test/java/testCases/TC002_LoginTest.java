package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Sanity", "Master"})
	public void verify_login()
	{
		try {
		//HomePage
		//Log info
		logger.info("** Starting TC_002_LoginTest **");
		//create Object for Homepage class
		HomePage hp = new HomePage(driver);
		
		hp.clickMyAccount();		
		hp.clickLogin();
		
		//Login page
		LoginPage lp = new LoginPage(driver);
		//Test case Valid email and Password
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//MyAccount Page
		MyAccountPage mac = new MyAccountPage(driver);
		boolean targetPage = mac.isMyAccountPageExists();
		
			AssertJUnit.assertTrue(targetPage); //Assert.assertEquals(targetPage, true,"Login Failed");

		}catch(Exception e)
		{
			Assert.fail();
		}
	}

}
