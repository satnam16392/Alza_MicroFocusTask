package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage {

	
	WebDriver driver;

	public ItemPage(WebDriver driver)
		{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}

		@FindBy(xpath="//div[@id='h1c']")
		public WebElement itemName;
		
		@FindBy(xpath="//div[@id='pricec']//span[contains(@class, 'price_withVat')")
		public WebElement itemPrice;
		
		@FindBy(xpath="//div[contains(@class, 'row bottom')]//span[contains(@class, 'text')]")
		public WebElement addToCart;
		
		@FindBy(xpath="//a[contains(@class,'confirm-button') and text()='Purchase without services']")
		public WebElement purchaseWithoutServices;
		
		@FindBy(xpath="//div[@id='alzaDialog']//div[@class=\"close\"]")
		public WebElement closedialog;
		
		@FindBy(xpath="//div[@class='buttonGroup']//a[@class='btn green']")
		public WebElement proceedToCheckOut;
		
		
				

}
