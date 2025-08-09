/*
 * TC-002: Quantity Field Should Reject Invalid Inputs
 * Validates quantity field by filling all other fields correctly.
 * Checks that alphabets, symbols, and negative numbers are rejected or block form submission.
 * Also confirms correct error appears when left empty.
 */

package orderPageTests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import genericLibraries.BaseClass;
import pom.HomePage;
import pom.OrderOnlinePage;

public class TestOrderQuantityValidation extends BaseClass {

    @Test
    public void validateQuantityField() throws IOException, InterruptedException {
        HomePage hm = new HomePage(driver);
        hm.orderPage();

        OrderOnlinePage op = new OrderOnlinePage(driver);

        // Fill all valid values from .properties
        utilities.dropDown(op.getSelectItem(), readProperty.getData("food_item_success"));
        utilities.dropDown(op.getSpecialItem(), readProperty.getData("special_item_success"));
        utilities.dropDown(op.getDeliveryType(), readProperty.getData("delivery_type_success"));
        utilities.dropDown(op.getDeliveryArea(), readProperty.getData("delivery_area_success"));

        // Test: Alphabets
        op.enterQuantity("abc");
        op.clickSubmit();
        Assert.assertFalse(op.getConfirmationMessage().isDisplayed(), "❌ Order confirmed with alphabets!");
        System.out.println("✅ Alphabets rejected.");

        // Test: Symbols
        op.enterQuantity("@$%");
        op.clickSubmit();
        Assert.assertFalse(op.getConfirmationMessage().isDisplayed(), "❌ Order confirmed with symbols!");
        System.out.println("✅ Symbols rejected.");

        // Test: Negative Number
        op.enterQuantity("-5");
        op.clickSubmit();
        Assert.assertFalse(op.getConfirmationMessage().isDisplayed(), "❌ Order confirmed with negative quantity!");
        System.out.println("✅ Negative numbers rejected.");

        // Test: Empty input
        op.enterQuantity(""); 
        op.clickSubmit();
        String expectedError = readProperty.getData("error_quantity");
        String actualError = op.getFoodQuantityError().getText();
        Assert.assertEquals(actualError, expectedError, "❌ Required field error not displayed!");
        System.out.println("✅ Empty input shows correct error message.");
    }
}
