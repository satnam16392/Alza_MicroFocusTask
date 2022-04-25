package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	WebDriver driver;

	public CartPage(WebDriver driver)
	{
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	
	
		
	@FindBy(xpath="//div[@id='basket']//span[contains(@class, 'count')]")
	public WebElement cartItemCount;
	
	@FindBy(xpath="//div[@id='basket']//span[contains(@id, 'price')]")
	public WebElement cartPrice;
	
	@FindBy(xpath="//tr[contains(@class, 'ui-draggable')]//td[contains(@class,'c5') and contains(text(),'K')]")
	public List<WebElement> itemPrice;
	
	@FindBy(xpath="//span[contains(@class, 'item-options__trigger js-item-options-trigger')]")
	public WebElement buylaterDropDown;
	
	@FindBy(xpath="//div[contains(@class, 'nameContainer')]//a[contains(@class, 'mainItem')]")
	public WebElement itemNameInCartpage;
	
	@FindBy(xpath="(//div[@class='basketTab']//span[text()='Buy later'])[2]")
	public WebElement buyLater;
	
	@FindBy(xpath="//span[contains(@class, 'normalBlock')]//span[text()='Saved for later']")
	public WebElement saveForLaterTab;
	
	@FindBy(xpath="//a[contains(@class, 'name')]")
	public WebElement itemNameInBuyLater;
	


}
