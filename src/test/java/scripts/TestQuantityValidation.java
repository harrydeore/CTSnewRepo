package scripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import genericLibraries.Screenshot;
import pom.HomePage;
import pom.OrderOnlinePage;

public class TestQuantityValidation extends BaseClass {

    @Test
    public void validateQuantityNumericInput_STORY_2_2() throws IOException, InterruptedException {
        // Navigate to Order Online page
        HomePage home = new HomePage(driver);
        home.orderPage();
        Thread.sleep(1000);

        OrderOnlinePage orderPage = new OrderOnlinePage(driver);

        // Test empty quantity validation (other fields empty to isolate error)
        orderPage.clickSubmit();
        String emptyError = orderPage.getFoodQuantityError().getText();
        String expectedError = readProperty.getData("error_quantity");
        Assert.assertEquals(emptyError, expectedError, "Empty quantity error message mismatch");

        // Fill valid data for other required fields (from properties)
        String validFoodItem = readProperty.getData("food_item_success");
        String validSpecialItem = readProperty.getData("special_item_success");
        String validDeliveryType = readProperty.getData("delivery_type_success");
        String validDeliveryArea = readProperty.getData("delivery_area_success");
        utilities.dropDown(orderPage.getSelectItem(), validFoodItem);
        utilities.dropDown(orderPage.getSpecialItem(), validSpecialItem);
        utilities.dropDown(orderPage.getDeliveryType(), validDeliveryType);
        utilities.dropDown(orderPage.getDeliveryArea(), validDeliveryArea);

        // Test non-numeric input (UI blocks, confirm no confirmation)
        orderPage.enterQuantity("abc");
        String enteredValue = orderPage.getQuantity().getAttribute("value");
        boolean isInvalidEntered = !enteredValue.matches("\\d+");
        orderPage.clickSubmit();
        boolean isOrderConfirmed = orderPage.getConfirmationMessage().getText().contains(readProperty.getData("confirmation_message_success"));
        try {
            Assert.assertFalse(isOrderConfirmed, "Order confirmed with invalid quantity");
        } catch (AssertionError e) {
            new Screenshot().screenShot(driver);
            logBug("Invalid Quantity Accepted", "Filled valid fields, entered 'abc' in quantity, and submitted", "Order confirmed", "Should not confirm");
            throw e;
        }

        // Reset quantity and test decimal input (bug: accepts but should reject)
        orderPage.enterQuantity("1.5");
        orderPage.clickSubmit();
        isOrderConfirmed = orderPage.getConfirmationMessage().getText().contains(readProperty.getData("confirmation_message_success"));
        try {
            Assert.assertFalse(isOrderConfirmed, "Order confirmed with decimal quantity");
        } catch (AssertionError e) {
            new Screenshot().screenShot(driver);
            logBug("Decimal Quantity Accepted", "Filled valid fields, entered '1.5', and submitted", "Order confirmed", "Should reject decimals");
            throw e;
        }

        // Reset quantity and test zero quantity (bug: accepts but should not)
        orderPage.enterQuantity("0");
        orderPage.clickSubmit();
        isOrderConfirmed = orderPage.getConfirmationMessage().getText().contains(readProperty.getData("confirmation_message_success"));
        try {
            Assert.assertFalse(isOrderConfirmed, "Order confirmed with zero quantity");
        } catch (AssertionError e) {
            new Screenshot().screenShot(driver);
            logBug("Zero Quantity Accepted", "Filled valid fields, entered '0', and submitted", "Order confirmed", "Should reject zero");
            throw e;
        }

        // Test leading zeros (should interpret as numeric, e.g., 0005 -> 5)
        orderPage.enterQuantity("0005");
        Assert.assertEquals(orderPage.getQuantity().getAttribute("value"), "5", "Leading zeros not handled correctly");

        System.out.println("Quantity numeric validation completed.");
    }

    private void logBug(String title, String steps, String actual, String expected) {
        System.out.println("Bug Title: " + title);
        System.out.println("Steps: " + steps);
        System.out.println("Actual Result: " + actual);
        System.out.println("Expected Result: " + expected);
    }
}
