package orderPageTests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import genericLibraries.BaseClass;
import pom.HomePage;
import pom.OrderOnlinePage;

public class TestResetOrderForm extends BaseClass {

    @Test
    public void testResetButton() throws InterruptedException, IOException {
        HomePage hm = new HomePage(driver);
        hm.orderPage();

        OrderOnlinePage op = new OrderOnlinePage(driver);

        // Enter data from the properties file
        utilities.dropDown(op.getSelectItem(), readProperty.getData("food_item_reset"));
        op.enterQuantity(readProperty.getData("quantity_reset"));
        
        Assert.assertNotEquals(op.getTotalCost().getText(), "0", "Total cost should have been calculated.");

        op.clickReset();
        Thread.sleep(500);

        // Assert that fields are cleared
        Assert.assertEquals(op.getSelectItem().getAttribute("value"), "");
        Assert.assertEquals(op.getQuantityField().getAttribute("value"), "");
        Assert.assertEquals(op.getTotalCost().getText(), "0");

        System.out.println("✅ TestResetOrderForm: Successfully verified that the reset button clears the form.");
    }
}