package testBase;

import org.testng.annotations.AfterMethod;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; //Log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	//declare driver object
		public static WebDriver driver;
		
	//declare a variable name as logger (object)
		public Logger logger; //Log4j
		
	//create a property class variable
		public Properties p;
		
		
		
	@BeforeClass(groups= {"Sanity","Regression","Master"})//"DataDriven" we can put, but it takes a lot of time to load so not putting
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		//Loading config.properties file
		//Location of the file in the braces
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		
		
		//from LogManager class call getLogger Method
		logger = LogManager.getLogger(this.getClass()); //this method gets the log4j2 file
		//Launch Chromedriver
		//driver = new ChromeDriver();
		//Launch multiple browser
		
		//if you run the application in remote environment, need to differnt condition here
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//capabilities.setPlatform(Platform.WIN11);
			//capabilities.setBrowserName("chrome");
			
			// we need to check OS and Browser
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching OS found");
				return;
			}
			
			
			//Browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); 
			break;
			
			case "Edge": capabilities.setBrowserName("MicrosoftEdge"); 
			break;
			
			case "firefox": capabilities.setBrowserName("firefox"); 
			break;
			
			default: System.out.println("No Matching browser"); break;
			}
			
			driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"),capabilities);
		}
		
		//This is for local environemt
		switch(br.toLowerCase())
		{
		case "chrome" : driver=new ChromeDriver(); break;
		case "edge" : driver= new EdgeDriver(); break;
		case "firefox" : driver=new FirefoxDriver();break;
		default: System.out.println("Invalid browser name");return;
		}
		
		
		driver.manage().deleteAllCookies();
		
		//go to url
		//driver.get("https://tutorialsninja.com/demo/");
		//reading url from properties file
		driver.get(p.getProperty("appURL"));
		//Wait time
		driver .manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
				
		//Maximize the window 
		driver.manage().window().maximize();
	}
	@AfterMethod
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}

	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String randomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		
		return (str+"@"+num);
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date(0));
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	
}
