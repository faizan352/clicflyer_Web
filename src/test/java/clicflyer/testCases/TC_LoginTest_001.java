package clicflyer.testCases;

import org.testng.annotations.Test;

import clicflyer.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass

{
	@Test
	public void loginTest()
	{
		driver.get(baseURL);
		LoginPage lp= new LoginPage(driver);
		lp.clickLogin();
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmit();
	}
}


