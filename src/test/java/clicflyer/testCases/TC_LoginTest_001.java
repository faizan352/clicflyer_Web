package clicflyer.testCases;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;

import org.testng.Reporter;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import clicflyer.pageObjects.LoginPage;
import clicflyer.utilities.Xls_Reader;

import org.testng.AssertJUnit;
import org.testng.Assert;

public class TC_LoginTest_001 extends BaseClass

{
	@Test(priority = 1)
	public void loginTest() throws InterruptedException, IOException
	{
		//Logger log=Logger.getLogger(TC_LoginTest_001.class);
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	//	log.info("URL is oepn");
		Xls_Reader reader= new Xls_Reader();
		String Username= reader.getCellData("Sheet1","username", 2);
		String Password= reader.getCellData("Sheet1","password", 2);
		LoginPage lp= new LoginPage(driver);
		lp.clickLogin();
		lp.setUserName(Username);
	//	Reporter.log( "Message", true );
	//	log.info("Enter username");	
		Thread.sleep(2000);
		lp.setPassword(Password);
	//	log.info("Enter password");
		lp.clickSubmit();
		
//		if(driver.getTitle().equals("Best KSA Price & Offers in  Riyadh in Panda, Lulu, Othaim, Danube, Nesto, Dukan"))
//		{
//			Assert.assertTrue(true);
//		}
//		else
//		{
//			captureScreen(driver,"loginTest");
//			Assert.assertTrue(false);
//		}
	}
}


