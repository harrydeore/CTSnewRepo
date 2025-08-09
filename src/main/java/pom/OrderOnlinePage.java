package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderOnlinePage {

	// Existing Elements
	@FindBy(id = "food_item")
	private WebElement selectItem;

	@FindBy(id = "special_item")
	private WebElement specialItem;

	@FindBy(id = "food_quantity")
	private WebElement quantity;

	@FindBy(id = "delivery_type")
	private WebElement deliveryType;

	@FindBy(id = "delivery_area")
	private WebElement deliveryArea;

	@FindBy(id = "submitBtn")
	private WebElement submitButton;

	@FindBy(id = "resetBtn")
	private WebElement resetButton;

	// New Elements for Validation
	@FindBy(id = "foodItemErr")
	private WebElement foodItemError;

	@FindBy(id = "specialItemErr")
	private WebElement specialItemError;

	@FindBy(id = "foodQuantityErr")
	private WebElement foodQuantityError;

	@FindBy(id = "deliveryTypeErr")
	private WebElement deliveryTypeError;

	@FindBy(id = "deliveryAreaErr")
	private WebElement deliveryAreaError;

	@FindBy(id = "bookingconfirm")
	private WebElement confirmationMessage;
	
	@FindBy(id = "ttab")
	private WebElement confirmationTable;

	@FindBy(id = "result")
	private WebElement totalCost;


	// Constructor
	public OrderOnlinePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Existing Methods
	public WebElement getSelectItem() {
		return selectItem;
	}

	public WebElement getSpecialItem() {
		return specialItem;
	}

	public void enterQuantity(String quant) {
		getQuantity().clear();
		getQuantity().sendKeys(quant);
	}
	
	public WebElement getQuantityField() {
		return getQuantity();
	}

	public WebElement getDeliveryType() {
		return deliveryType;
	}

	public WebElement getDeliveryArea() {
		return deliveryArea;
	}

	public void clickSubmit() {
		submitButton.click();
	}

	public void clickReset() {
		resetButton.click();
	}
	
	// New Getter Methods for Validation
	public WebElement getFoodItemError() {
		return foodItemError;
	}
	
	public WebElement getSpecialItemError() {
		return specialItemError;
	}
	
	public WebElement getFoodQuantityError() {
		return foodQuantityError;
	}

	public WebElement getDeliveryTypeError() {
		return deliveryTypeError;
	}

	public WebElement getDeliveryAreaError() {
		return deliveryAreaError;
	}

	public WebElement getConfirmationMessage() {
		return confirmationMessage;
	}

	public WebElement getConfirmationTable() {
		return confirmationTable;
	}
	
	public WebElement getTotalCost() {
		return totalCost;
	}

	public WebElement getQuantity() {
		return quantity;
	}

	public void setQuantity(WebElement quantity) {
		this.quantity = quantity;
	}
}