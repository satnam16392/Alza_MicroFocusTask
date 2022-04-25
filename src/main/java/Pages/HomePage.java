package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	

	WebDriver driver;

public HomePage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

@FindBy(xpath="//input[@id='edtSearch']")
public WebElement searchBox;

@FindBy(xpath="//div[@id='btnSearch']")
public WebElement clickSearchButton;

@FindBy(xpath="//a[text()='Price High to Low']")
public WebElement clickHighToLow;

@FindBy(xpath="(//a[contains(@class,'name browsinglink impression-binded')])")
public List<WebElement> selectItems;

@FindBy(xpath="//li[@aria-labelledby=\"ui-id-3\" and @aria-selected=\"true\"]")
public WebElement HighToLow;


}
