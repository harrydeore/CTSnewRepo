// TC-001: Verify all dropdowns display correct options
package orderPageTests;

import java.io.IOException;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import genericLibraries.BaseClass;
import pom.HomePage;
import pom.OrderOnlinePage;

public class TestOrderDropdownOptions extends BaseClass {
	
    /*
     * This test case validates that all dropdowns on the Order Online page 
     * (Food Item, Special Item, Delivery Type, Delivery Area) contain the 
     * expected list of options. The actual dropdown values fetched from 
     * the UI are compared with the expected values defined in the properties file.
     * This ensures dropdown data integrity and usability for the user.
     */

	
    @Test
    public void validateDropdownOptions() throws IOException {
        HomePage hm = new HomePage(driver);
        hm.orderPage();

        OrderOnlinePage op = new OrderOnlinePage(driver);

        // 1. Food Item Dropdown
        List<String> actualFoodItems = utilities.getDropdownOptions(op.getSelectItem());
        List<String> expectedFoodItems = utilities.getExpectedDropdownList("food_item_list", readProperty);
        Assert.assertEquals(actualFoodItems, expectedFoodItems, "❌ Mismatch in Food Item dropdown");

        // 2. Special Item Dropdown
        List<String> actualSpecialItems = utilities.getDropdownOptions(op.getSpecialItem());
        List<String> expectedSpecialItems = utilities.getExpectedDropdownList("special_item_list", readProperty);
        Assert.assertEquals(actualSpecialItems, expectedSpecialItems, "❌ Mismatch in Special Item dropdown");

        // 3. Delivery Type Dropdown
        List<String> actualDeliveryTypes = utilities.getDropdownOptions(op.getDeliveryType());
        List<String> expectedDeliveryTypes = utilities.getExpectedDropdownList("delivery_type_list", readProperty);
        Assert.assertEquals(actualDeliveryTypes, expectedDeliveryTypes, "❌ Mismatch in Delivery Type dropdown");

        // 4. Delivery Area Dropdown
        List<String> actualDeliveryAreas = utilities.getDropdownOptions(op.getDeliveryArea());
        List<String> expectedDeliveryAreas = utilities.getExpectedDropdownList("delivery_area_list", readProperty);
        Assert.assertEquals(actualDeliveryAreas, expectedDeliveryAreas, "❌ Mismatch in Delivery Area dropdown");

        System.out.println("✅ All dropdowns display correct options as expected.");
    }
}
