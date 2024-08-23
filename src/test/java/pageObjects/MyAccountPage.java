package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath="(//div[@id='content']/child::h2)[1]")
	WebElement msgHeading;
	
	@FindBy(xpath="(//a[text()='Logout'])[2]")
	WebElement linkLogout;
	
	public boolean isMyAccountPageExists()
	{
		try
		{
			return (msgHeading.isDisplayed());

		}
		catch(Exception e)
		{
			return false;
		}
	}
	public void clickLogout()
	{
		linkLogout.click();
	}
}
