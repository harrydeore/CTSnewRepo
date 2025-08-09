package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPage {

//    @FindBy(xpath = "/html/body/div/header/nav/div/ul/li[2]/a") // Menu button
//    private WebElement menuButton;

    @FindBy(xpath = "/html/body/div/div/div/div/div[2]/div[2]/div/div[1]/div/div[1]/div/img")
    private WebElement firstImage;

    @FindBy(xpath = "/html/body/div/div/div/div/div[2]/div[2]/div/div[1]/div/div[2]/div/img")
    private WebElement secondImage;

    @FindBy(xpath = "/html/body/div/div/div/div/div[2]/div[2]/div/div[1]/div/div[3]/div/img")
    private WebElement thirdImage;

    @FindBy(xpath = "/html/body/div/div/div/div/div[2]/div[2]/div/div[1]/div/div[4]/div/img")
    private WebElement fourthImage;
    
    @FindBy(id = "v-pills-profile-tab")
    private WebElement item1Tab;

    @FindBy(id = "v-pills-messages-tab")
    private WebElement item2Tab;

    @FindBy(id = "specialdrin2")
    private WebElement item1Content;

    @FindBy(id = "specialsweets1")
    private WebElement item2Content;

    @FindBy(id = "specialdrink")
    private WebElement specialDrink;

    @FindBy(id = "specialdinner")
    private WebElement specialDinner;

    @FindBy(id = "speciallunch")
    private WebElement specialLunch;

    @FindBy(id = "specialsweets")
    private WebElement specialSweets;

    // Constructor
    public MenuPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Click menu button
//    public void clickMenuButton() {
//        menuButton.click();
//    }

    // Getters for images
    public WebElement getFirstImage() {
        return firstImage;
    }

    public WebElement getSecondImage() {
        return secondImage;
    }

    public WebElement getThirdImage() {
        return thirdImage;
    }

    public WebElement getFourthImage() {
        return fourthImage;
    }
    
    public void clickItem1Tab() {
        item1Tab.click();
    }

    public void clickItem2Tab() {
        item2Tab.click();
    }

    public WebElement getItem1Content() {
        return item1Content;
    }

    public WebElement getItem2Content() {
        return item2Content;
    }

    public WebElement getSpecialDrink() {
        return specialDrink;
    }

    public WebElement getSpecialDinner() {
        return specialDinner;
    }

    public WebElement getSpecialLunch() {
        return specialLunch;
    }

    public WebElement getSpecialSweets() {
        return specialSweets;
    }
}