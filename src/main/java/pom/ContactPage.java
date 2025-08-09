package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

	@FindBy(name = "Name")
	private WebElement name;

	@FindBy(name = "Subject")
	private WebElement subject;

	@FindBy(name = "Email")
	private WebElement email;

	@FindBy(name = "Message")
	private WebElement message;

	@FindBy(xpath = "//button[text()='Submit']")
	private WebElement submit;

	// Assuming there is a Phone field with name="Phone"
	@FindBy(name = "Phone")
	private WebElement phone;

	// Error message locators (adjust these XPaths according to actual app)
	@FindBy(id = "name-error")
	private WebElement nameError;

	@FindBy(id = "email-error")
	private WebElement emailError;

	@FindBy(id = "phone-error")
	private WebElement phoneError;

	public ContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void enterName(String nameText) {
		name.clear();
		name.sendKeys(nameText);
	}

	public void enterSubject(String subjectText) {
		subject.clear();
		subject.sendKeys(subjectText);
	}

	public void enterEmail(String emailText) {
		email.clear();
		email.sendKeys(emailText);
	}

	public void enterMessage(String messageText) {
		message.clear();
		message.sendKeys(messageText);
	}

	public void enterPhone(String phoneText) {
		phone.clear();
		phone.sendKeys(phoneText);
	}

	public void clickSubmit() {
		submit.click();
	}

	public String getNameError() {
		return nameError.getText();
	}

	public String getEmailError() {
		return emailError.getText();
	}

	public String getPhoneError() {
		return phoneError.getText();
	}
	
	public WebElement getEmailField() {
	    return email;
	}

}
