package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
			
	}
	//Locate email input field
	@FindBy(xpath="//input[@name='email']")
	WebElement txtEmail;
	
	//Locate password input field
	@FindBy(xpath="//input[@name='password']")
	WebElement txtPassword;
	
	//Locate Login button
	@FindBy(xpath="//input[@type='submit']")
	WebElement btnLogin;
	
	
	//Actions
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	public void clickLogin()
	{
		btnLogin.click();
	}
	
	
	
	

}
