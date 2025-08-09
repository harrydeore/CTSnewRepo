package scripts;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import genericLibraries.Screenshot;
import pom.HomePage;
import pom.OrderOnlinePage;

public class TestDynamicCostCalculation extends BaseClass {

    @Test
    public void verifyRealTimeCostCalculation_STORY_2_3() throws IOException, InterruptedException {
        // Navigate to Order Online page
        HomePage home = new HomePage(driver);
        home.orderPage();
        Thread.sleep(1000);

        OrderOnlinePage orderPage = new OrderOnlinePage(driver);

        // Read test data from properties
        String foodItem = readProperty.getData("food_item_success");
        String specialItem = readProperty.getData("special_item_success");
        String quantity = readProperty.getData("quantity_success");
        String deliveryType = readProperty.getData("delivery_type_success");
        String deliveryArea = readProperty.getData("delivery_area_success");

        // Select options using utilities
        utilities.dropDown(orderPage.getSelectItem(), foodItem);
        utilities.dropDown(orderPage.getSpecialItem(), specialItem);
        orderPage.enterQuantity(quantity);
        utilities.dropDown(orderPage.getDeliveryType(), deliveryType);
        utilities.dropDown(orderPage.getDeliveryArea(), deliveryArea);
        Thread.sleep(500); // Wait for JS calculation

        // Calculate expected cost based on HTML JS logic (values from option attributes)
        int foodValue = getOptionValue(orderPage.getSelectItem(), foodItem);
        int specialValue = getOptionValue(orderPage.getSpecialItem(), specialItem);
        int qtyValue = Integer.parseInt(quantity);
        int typeValue = getOptionValue(orderPage.getDeliveryType(), deliveryType);
        int areaValue = getOptionValue(orderPage.getDeliveryArea(), deliveryArea);
        int expectedCost = (foodValue * 50) + (specialValue * 30) + (qtyValue * 50) + (typeValue * 50) + (areaValue * 20);

        // Get displayed cost
        String displayedCostStr = orderPage.getTotalCost().getText();
        int displayedCost = Integer.parseInt(displayedCostStr);

        // Assert cost
        try {
            Assert.assertEquals(displayedCost, expectedCost, "Dynamic cost calculation mismatch");
        } catch (AssertionError e) {
            new Screenshot().screenShot(driver);
            logBug("Cost Calculation Error", "Selected items and quantities", "Displayed: " + displayedCost, "Expected: " + expectedCost);
            throw e;
        }

        // Test decimal quantity bug
        orderPage.enterQuantity("1.5");
        Thread.sleep(500);
        displayedCostStr = orderPage.getTotalCost().getText();
        try {
            Assert.assertEquals(displayedCostStr, "0", "Cost updated for invalid decimal quantity");
        } catch (AssertionError e) {
            new Screenshot().screenShot(driver);
            logBug("Decimal Quantity Cost Update", "Entered '1.5' quantity", "Cost updated", "Should not update for decimals");
            throw e;
        }

        System.out.println("Dynamic cost calculation validated.");
    }

    private int getOptionValue(WebElement dropdown, String text) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(text); // Ensure selected for value retrieval
        return Integer.parseInt(select.getFirstSelectedOption().getAttribute("value"));
    }

    private void logBug(String title, String steps, String actual, String expected) {
        System.out.println("Bug Title: " + title);
        System.out.println("Steps: " + steps);
        System.out.println("Actual Result: " + actual);
        System.out.println("Expected Result: " + expected);
    }
}
