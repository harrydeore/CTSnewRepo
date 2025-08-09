package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	@FindBy(xpath = "//a[text()='Order Online Now ']")
	private WebElement orderOnline;
	
	@FindBy(id="menu")
	private WebElement menu;
	
	@FindBy(id="contactus")
	private WebElement contact;
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void orderPage() {
		orderOnline.click();
	}
	
	public void menuPage() {
		menu.click();
	}
	
	public void contactPage() {
		contact.click();
	}
	
	
}
