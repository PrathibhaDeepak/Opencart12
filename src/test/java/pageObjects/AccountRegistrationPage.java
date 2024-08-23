package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

//create constructor with driver parameter	
	public AccountRegistrationPage(WebDriver driver){
		super(driver);	
	}
	//Locate the Register Account page elements
			@FindBy(xpath="//input[@name='firstname']")
			WebElement txtFirstname;
			
			@FindBy(xpath="//input[@name='lastname']")
			WebElement txtLastname;
	
			@FindBy(xpath="//input[@name='email']")
			WebElement txtEmail;
			
			@FindBy(xpath="//input[@name='telephone']")
			WebElement txtTelephone;
			
			
			@FindBy(xpath="//input[@name='password']")
			WebElement txtPassword;
			@FindBy(xpath="//input[@name='confirm']")
			WebElement txtConfirmPassword;
			
			@FindBy(xpath="//input[@name='newsletter']")
			WebElement chkdPolicy;
			@FindBy(xpath="//input[@name='agree']")
			WebElement chkAgree;
		
			@FindBy(xpath="//input[@type='submit']")
			WebElement btnContinue;
			
			@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
			WebElement msgConfirmation;
			
	//perform actions on located page elements		
			
			public void setFirstName(String fname)
			{
				txtFirstname.sendKeys(fname);
			}
			public void sefLastname(String lname)
			{
				txtLastname.sendKeys(lname);
			}
			public void setEmail(String email)

			{
				txtEmail.sendKeys(email);
			}
			public void setTelephone(String tel)

			{
				txtTelephone.sendKeys(tel);
			}
			public void setPassword(String pwd)
			{
				txtPassword.sendKeys(pwd);
			}
			public void setConfirmPassword(String pwd)
			{
				txtConfirmPassword.sendKeys(pwd);
			}
			public void setPrivacyPolicy()
			{
				chkdPolicy.click();
			}
			public void setAgree()
			{
				chkAgree.click();
			}

			public void clickContinue()
			{
				
				Actions act = new Actions(driver);
				act.moveToElement(btnContinue).click().perform();
				//btnContinue.click();
			
			
			//JavascriptExecutor js = (JavascriptExecutor)driver;
			//js.executeScript("arguments[0].click()", btnContinue);
			}
			
			public String getConfirmationMsg() 
			{
				try {
					return(msgConfirmation.getText());
				}
				catch(Exception e) {
					return(e.getMessage());
				}
				
			}
			
}