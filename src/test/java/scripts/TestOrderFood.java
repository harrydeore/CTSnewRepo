package scripts;

import java.io.FileNotFoundException;

import java.io.IOException;

import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import pom.HomePage;
import pom.OrderOnlinePage;

public class TestOrderFood extends BaseClass {
	
	@Test
	public void orderFoodOnline() throws FileNotFoundException, IOException, InterruptedException {
		HomePage hm = new HomePage(driver);
		hm.orderPage();

		OrderOnlinePage op = new OrderOnlinePage(driver);

		// Read values from property file
		String foodItem = readProperty.getData("food_item"); // French Fries
		String specialItem = readProperty.getData("special_item"); // Yes
		String quantity = readProperty.getData("quantity"); // e.g., 3
		String deliveryType = readProperty.getData("delivery_type"); // Home
		String deliveryArea = readProperty.getData("delivery_area"); // Adyar

		// Perform form actions using WebDriverUtilities
		utilities.dropDown(op.getSelectItem(), foodItem);
		utilities.dropDown(op.getSpecialItem(), specialItem);
		op.enterQuantity(quantity); // this method already sends value
		utilities.dropDown(op.getDeliveryType(), deliveryType);
		Thread.sleep(2000);
		utilities.dropDown(op.getDeliveryArea(), deliveryArea);
		
		Thread.sleep(2000);
		op.clickSubmit(); // finally, click on submit
	}
}
