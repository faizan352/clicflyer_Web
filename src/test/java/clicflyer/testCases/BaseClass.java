package clicflyer.testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.github.bonigarcia.wdm.WebDriverManager;




public class BaseClass {
	public String baseURL="https://clicflyer.com/shoppers/en/saudi-arabia/riyadh/home#";
	public String username="faizanm3522@gmail.com";
	public String password="12345678";
	public static WebDriver driver;
	
	@BeforeClass
	public void setup()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@AfterClass
	public void teardown()
	{
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		//driver.quit();
	}

}
