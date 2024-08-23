package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {
		
		logger.info("***** Starting TC001_AccountRegistrationTest *****");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("***** Clicked on MyAccount Link *****");
		
		hp.clickRegister();
		logger.info("***** Clicked on Register Link *****");
	
	AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
	regpage.setFirstName(randomeString().toUpperCase());
	regpage.sefLastname(randomeString().toUpperCase());
	//generate email randomly
	regpage.setEmail(randomeString() + "@gmail.com");
	regpage.setTelephone(randomeNumber());
	//for password create a variable
	String password = randomeAlphaNumeric();
	
	regpage.setPassword(password);
	regpage.setConfirmPassword(password);
	regpage.setPrivacyPolicy();
	regpage.setAgree();
	

	regpage.clickContinue();
	
	
	
	
	  //Validating the confirmation msg 
	logger.info("***** Validating expected message *****");
	String confMsg = regpage.getConfirmationMsg();
	
	if(confMsg.equals("Your Account Has Been Created!"))
	{
		AssertJUnit.assertTrue(true);
	}
	else
	{
		logger.error("Test Failed");
		logger.debug("Debug logs..");
		AssertJUnit.assertTrue(false);
	}
	
	  //Assert.assertEquals(confMsg,"Your Account Has Been Created!" );
		}catch(Exception e)
		{
			
			Assert.fail();
		}
		logger.info("*** Finished TC001_AccountRegistration Test ***");
	 
	}
	public String randomeString() 
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	public String randomeNumber()
	{
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}

	public String randomeAlphaNumeric()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedstring + "@" + generatednumber);
		
		
				
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
