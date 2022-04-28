package TestCase;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.CartPage;
import Pages.HomePage;
import Pages.ItemPage;
import Pages.LoginPage;
import Settings.Base;
import TestData.TestData;

public class TestCases extends Base {

	TestData data = new TestData();


	//Below Test Check the Functionality of Login with Valid Credentials
	@Test(priority=2)
		public void validLogin() throws Exception
		{
			LoginPage Login = new LoginPage(driver);

			Login.clickIUnderstand.click();
			Login.ClosePopUp.click();
			Login.clickLogin.click();
			driver.switchTo().frame(Login.LoginFrame);
			Login.loginDetails(data.getUsername(), data.getPassword());
			wait.until(ExpectedConditions.visibilityOf(Login.userLoginPage));
			String getUserName = Login.userLoginPage.getText();
			String Username = getUserName.substring(10, getUserName.length()); 
			System.out.println("The User " + Username +" is Logged In Successfully" );
			wait.until(ExpectedConditions.visibilityOf(Login.userLoginPage));
			Assert.assertTrue(Login.userLoginPage.isDisplayed());		
		}

	//Below Test Check the Functionality of Login with Invalid Credentials and Verify Error Message is Displayed
	@Test(priority=1)
	public void invalidLogin() throws Exception
	{
		LoginPage Login = new LoginPage(driver);
		Login.clickIUnderstand.click();
		Login.ClosePopUp.click();
		Login.clickLogin.click();
		//Enter UserName And Password 
		driver.switchTo().frame(Login.LoginFrame);
		Login.loginDetails(data.getUsername(), data.getInvalidPassword());
		wait.until(ExpectedConditions.visibilityOf(Login.invalidLogin));
		String errorLogin = Login.invalidLogin.getText();
		System.out.println("The Error Message is : " +errorLogin);
		String expectedErrorMessage = "Invalid username or password";
		Assert.assertEquals(errorLogin, expectedErrorMessage);

	}
	//Below Test Check the Functionality if Total Price of Items Added by User is same as Total Amount displayed  in Cart Section 
	@Test(priority=5)
	public void priceVerification() throws Exception
	{
		// Creating Objects of the Classes which are implemented
		LoginPage Login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		ItemPage item = new ItemPage(driver);
		CartPage cart = new CartPage(driver);

		Login.clickIUnderstand.click();
		Login.ClosePopUp.click();
		Login.clickLogin.click();

		//Enter UserName And Password 
		driver.switchTo().frame(Login.LoginFrame);
		Login.loginDetails(data.getUsername(), data.getPassword());
		wait.until(ExpectedConditions.visibilityOf(home.searchBox));
		home.searchBox.click();
		home.searchBox.sendKeys(data.searchProduct);
		home.clickSearchButton.click();
		home.clickHighToLow.click();

		// Adding two High Amount Item of searched product into Cart

		home.selectItems.size();

		for(int i=0;i<=1;i++)
		{
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(home.HighToLow));
			home.selectItems.get(i).click();

			item.addToCart.click();
			wait.until(ExpectedConditions.elementToBeClickable(item.closedialog));
			item.closedialog.click();

			if(i==0){
				driver.navigate().back();
				driver.navigate().back();
			}
		}

		item.proceedToCheckOut.click();
		int totalPriceOfItem = 0;
		//Below Code is Used to get the Total Price of all Items added by user 

		cart.itemPrice.size();

		for(int j=0;j<=1;j++)
		{
			String getItemPrice = cart.itemPrice.get(j).getText();			
			//below code is used to remove String KC and spaces from the item price
			String getInterger = getItemPrice.replace("Kč", "");
			String trimAmount = getInterger.trim();
			String removeSpace = trimAmount.replaceAll("\\s","");
			int itemPriceInInt = Integer.parseInt(removeSpace);
			totalPriceOfItem = totalPriceOfItem + itemPriceInInt;
		}

		System.out.println("The Total price of item added by User is : " +totalPriceOfItem +" CZK");
		//Below Code is Used to get the Total Price displayed in Cart
		String getCartPrice = cart.cartPrice.getText();
		String cartPriceInt = getCartPrice.replace("Kč", "");
		String trimcartPriceInt = cartPriceInt.trim();
		String removeCartPriceSpace = trimcartPriceInt.replaceAll("\\s","");

		int cartPriceInInt = Integer.parseInt(removeCartPriceSpace);
		System.out.println("The Total price of item displayed in Cart is : " +cartPriceInInt +" CZK");
		System.out.println();
		//Below it is verified Total Price of Items Added by User is same as Total Amount displayed  in Cart Section
		Assert.assertEquals(totalPriceOfItem, cartPriceInInt);

	}

	//Below Test Check the Functionality of Buy Later(Correct Item is moved to Saved For Later page once user select item as Buy Later)
	@Test(priority=4)
	public void buyLater() throws Exception
	{
		// Creating Objects of the Classes which are implemented
		LoginPage Login = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		ItemPage item = new ItemPage(driver);
		CartPage cart = new CartPage(driver);


		Login.clickIUnderstand.click();
		Login.ClosePopUp.click();
		Login.clickLogin.click();

		//Enter UserName And Password 
		driver.switchTo().frame(Login.LoginFrame);
		Login.loginDetails(data.getUsername(), data.getPassword());
		wait.until(ExpectedConditions.visibilityOf(home.searchBox));
		home.searchBox.click();
		home.searchBox.sendKeys("Television");
		home.clickSearchButton.click();
		home.selectItems.size();

		for(int i=0;i<=0;i++)
		{	
			home.selectItems.get(i).click();

			item.addToCart.click();
			//wait.until(ExpectedConditions.elementToBeClickable(item.closedialog));
			//item.closedialog.click();
		}

		item.proceedToCheckOut.click();
		wait.until(ExpectedConditions.visibilityOf(cart.buylaterDropDown));

		String productNameInCartPage = cart.itemNameInCartpage.getText();

		System.out.println("The Name of the Product in Cart Page is " +productNameInCartPage);
		cart.buylaterDropDown.click();
		//Thread.sleep(2000);
		cart.buyLater.click();

		cart.saveForLaterTab.click();
		wait.until(ExpectedConditions.visibilityOf(cart.itemNameInBuyLater));
		String productNameInBuyLater = cart.itemNameInBuyLater.getText();

		System.out.println("The Name of the Product in Buy Later Page is " +productNameInBuyLater);

		Assert.assertTrue(productNameInCartPage.contains(productNameInBuyLater));


	}

	//Below Test Check the Search Functionality(Related Item are displayed once User Search for a Product )

	@Test(priority=3)
	public void searchVerification() throws Exception
	{
		// Creating Objects of the Classes which are implemented
		LoginPage Login = new LoginPage(driver);
		HomePage home = new HomePage(driver);



		Login.clickIUnderstand.click();
		Login.ClosePopUp.click();
		Login.clickLogin.click();

		//Enter UserName And Password 
		driver.switchTo().frame(Login.LoginFrame);
		Login.loginDetails(data.getUsername(), data.getPassword());
		wait.until(ExpectedConditions.visibilityOf(home.searchBox));
		home.searchBox.click();
		home.searchBox.sendKeys(data.searchProductVerification);
		home.clickSearchButton.click();
		int noOfItem = home.selectItems.size();

		for(int i=1;i<noOfItem;i++)
		{
			StringBuffer productName = new StringBuffer(home.selectItems.get(i).getText()) ;
			Assert.assertTrue(productName.toString().contains(data.searchProductVerification));

		}

	}

}