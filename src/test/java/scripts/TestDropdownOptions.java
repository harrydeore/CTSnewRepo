package scripts;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import genericLibraries.Screenshot;
import pom.HomePage;
import pom.OrderOnlinePage;

public class TestDropdownOptions extends BaseClass {

    @Test
    public void validateAllDropdownOptions_STORY_2_1() throws IOException, InterruptedException {
        // Navigate to Order Online page
        HomePage home = new HomePage(driver);
        home.orderPage();
        Thread.sleep(1000);

        OrderOnlinePage orderPage = new OrderOnlinePage(driver);

        // Validate Food Item dropdown
        List<String> actualFoodItems = utilities.getDropdownOptions(orderPage.getSelectItem());
        List<String> expectedFoodItems = utilities.getExpectedDropdownList("food_item_list", readProperty);
        try {
            Assert.assertEquals(actualFoodItems, expectedFoodItems, "Food Item dropdown options mismatch");
        } catch (AssertionError e) {
            new Screenshot().screenShot(driver);
            System.out.println("Bug: Food Item dropdown options mismatch - " + e.getMessage());
            throw e;
        }

        // Validate Special Item dropdown
        List<String> actualSpecialItems = utilities.getDropdownOptions(orderPage.getSpecialItem());
        List<String> expectedSpecialItems = utilities.getExpectedDropdownList("special_item_list", readProperty);
        try {
            Assert.assertEquals(actualSpecialItems, expectedSpecialItems, "Special Item dropdown options mismatch");
        } catch (AssertionError e) {
            new Screenshot().screenShot(driver);
            System.out.println("Bug: Special Item dropdown options mismatch - " + e.getMessage());
            throw e;
        }

        // Validate Delivery Type dropdown
        List<String> actualDeliveryTypes = utilities.getDropdownOptions(orderPage.getDeliveryType());
        List<String> expectedDeliveryTypes = utilities.getExpectedDropdownList("delivery_type_list", readProperty);
        try {
            Assert.assertEquals(actualDeliveryTypes, expectedDeliveryTypes, "Delivery Type dropdown options mismatch");
        } catch (AssertionError e) {
            new Screenshot().screenShot(driver);
            System.out.println("Bug: Delivery Type dropdown options mismatch - " + e.getMessage());
            throw e;
        }

        // Validate Delivery Area dropdown
        List<String> actualDeliveryAreas = utilities.getDropdownOptions(orderPage.getDeliveryArea());
        List<String> expectedDeliveryAreas = utilities.getExpectedDropdownList("delivery_area_list", readProperty);
        try {
            Assert.assertEquals(actualDeliveryAreas, expectedDeliveryAreas, "Delivery Area dropdown options mismatch");
        } catch (AssertionError e) {
            new Screenshot().screenShot(driver);
            System.out.println("Bug: Delivery Area dropdown options mismatch - " + e.getMessage());
            throw e;
        }

        System.out.println("All dropdown options validated successfully.");
    }
}
