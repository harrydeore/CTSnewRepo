package scripts;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import pom.HomePage;
import pom.MenuPage;

public class TestMenuPage extends BaseClass {

    @Test
    public void validateMenuItems() throws InterruptedException {
        // Step 1: Go to Menu Page (only once)
        HomePage home = new HomePage(driver);
        home.menuPage(); // Clicks "Menu" tab
        Thread.sleep(1500); // Give time to load fully

        System.out.println("Navigated to Menu page successfully");

        MenuPage menuPage = new MenuPage(driver);

        // Step 2: Hover and validate Special Drink
        WebElement img1 = menuPage.getFirstImage();
        utilities.mouseHover(driver, img1);
        Thread.sleep(1000);
        String text1 = menuPage.getSpecialDrink().getText();
        Assert.assertTrue(text1.contains("Special Drink"));
        Assert.assertTrue(text1.contains("Rs. 100"));

        // Step 3: Hover and validate Special Dinner
        WebElement img2 = menuPage.getSecondImage();
        utilities.mouseHover(driver, img2);
        Thread.sleep(1000);
        String text2 = menuPage.getSpecialDinner().getText();
        Assert.assertTrue(text2.contains("Special Dinner"));
        Assert.assertTrue(text2.contains("Rs. 250"));

        // Step 4: Hover and validate Special Lunch
        WebElement img3 = menuPage.getThirdImage();
        utilities.mouseHover(driver, img3);
        Thread.sleep(1000);
        String text3 = menuPage.getSpecialLunch().getText();
        Assert.assertTrue(text3.contains("Special Lunch"));
        Assert.assertTrue(text3.contains("Rs. 450"));

        // Step 5: Hover and validate Special Sweets
        WebElement img4 = menuPage.getFourthImage();
        utilities.mouseHover(driver, img4);
        Thread.sleep(1000);
        String text4 = menuPage.getSpecialSweets().getText();
        Assert.assertTrue(text4.contains("Special Sweets"));
        Assert.assertTrue(text4.contains("Rs. 380"));

//        // Step 6: Click Item1 tab and validate
//        menuPage.clickItem1Tab();
//        Thread.sleep(1000);
//        Assert.assertTrue(menuPage.getItem1Content().isDisplayed(), "Item1 tab content not visible");
//
//        // Step 7: Click Item2 tab and validate
//        menuPage.clickItem2Tab();
//        Thread.sleep(1000);
//        Assert.assertTrue(menuPage.getItem2Content().isDisplayed(), "Item2 tab content not visible");

        System.out.println("image and text validations completed successfully");
    }
}
