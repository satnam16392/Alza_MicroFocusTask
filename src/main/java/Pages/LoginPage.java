package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
	
	
WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@id='alzaDialog']//div[contains(@class, 'close')]")
	public WebElement ClosePopUp;
	
	@FindBy(xpath="//a[text()='I understand']")
	public WebElement clickIUnderstand;
	
	@FindBy(xpath="//a[@id='lblLogin']")
	public WebElement clickLogin;
	
	@FindBy(xpath="//iframe[(@id='loginIframe')]")
	public WebElement LoginFrame;
	
	@FindBy(xpath="//input[(@id='userName')]")
	private WebElement enterEmail;
	
	@FindBy(xpath="//input[(@id='password')]")
	private WebElement enterPassword;
	
	public void setUsername(String UserName)
	{
		enterEmail.sendKeys(UserName);		
	}
	
	public void setPassword(String Password)
	{
		enterPassword.sendKeys(Password);
	}
	
	public LoginPage loginDetails(String Username, String Password)
	{
		setUsername(Username);
		setPassword(Password);
		return clickSubmit();
	}
	
	@FindBy(xpath="//span[@id='loginButtonText']")
	public WebElement submitButton;
	
	public LoginPage clickSubmit()
	{
		submitButton.click();
		return new LoginPage(driver);
	}
	
	@FindBy(xpath="//a[(@class='loggedUser')]")
	public WebElement userLoginPage;
	
	@FindBy(xpath="//span[@id='lblSignOut']")
	public WebElement userLogoutIcon;
	
	
	@FindBy(xpath="//span[@id='loginButtonText' and text()='Invalid username or password']")
	public WebElement invalidLogin;


	
	
}
