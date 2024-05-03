package clicflyer.testCases;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.relevantcodes.extentreports.ExtentReports;

import clicflyer.utilities.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;




public class BaseClass {
	ReadConfig readconfig= new ReadConfig();
	//eadconfig.getApplicationURL();
	public String baseURL="https://clicflyer.com/shoppers/en/saudi-arabia/riyadh/home#";
//	public String username="faizanm3522@gmail.com";
//	public String password="12345678";
	public static WebDriver driver;
	
	public static Logger logger;
	
	@BeforeClass
	public void setup()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		logger = Logger.getLogger("clicFlyer");
		try {
	        System.setProperty("log4j.configuration", new File(".", File.separatorChar+"log4j.properties").toURL().toString());
	    } catch (MalformedURLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
		PropertyConfigurator.configure("log4j.properties");
	}
	
	@AfterClass
	public void teardown()
	{
		driver.manage().window().maximize();
	}
	public void captureScreen(WebDriver driver, String tname) throws IOException{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("User.dir") + "\\Screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot Taken");
		
	}

}
