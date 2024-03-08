package clicflyer.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver)
	{
		ldriver= rdriver;
		PageFactory.initElements(rdriver, this);
		
	}
	
	@FindBy (xpath = "//span[@class='hidden-xs'][normalize-space()='Login']")
	WebElement clickLogin;
	
	@FindBy(xpath ="//form[@id='loginuser']//input[@id='Email']")
	WebElement txtUserName;
	
	@FindBy(xpath="//input[@id='Password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//button[@id='loginbtn']")
	WebElement btnLogin;
	
	public void clickLogin(){
		clickLogin.click();
	}
	public void setUserName(String username){
		txtUserName.sendKeys(username);
	}
	public void setPassword(String password){
		txtPassword.sendKeys(password);
	}
	public void clickSubmit(){
		btnLogin.click();
	}
	

}
