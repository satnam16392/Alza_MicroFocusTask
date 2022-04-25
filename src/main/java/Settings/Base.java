package Settings;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class Base {

	
public WebDriver driver;
public WebDriverWait wait;
	
	@BeforeMethod
	public void loginAlza() throws Exception
	{
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",path+"/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.alza.cz/EN/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));	
		
		
	}
	
	@AfterMethod
	public void logoutAlza() throws Exception
	{
		driver.quit();
	}

	
}
