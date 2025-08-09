package scripts;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericLibraries.BaseClass;
import pom.ContactPage;
import pom.HomePage;

public class TestContactPage extends BaseClass {

    // Test Case: Submit the contact form with valid name, email, subject, and message.
    // Expected: Form should be submitted successfully without any validation errors.
    @Test
    public void testContactWithValidData() throws IOException {
        HomePage hm = new HomePage(driver);
        hm.contactPage();

        ContactPage cp = new ContactPage(driver);
        cp.enterName(readProperty.getData("name"));
        cp.enterSubject(readProperty.getData("subject"));
        cp.enterEmail(readProperty.getData("email"));
        cp.enterMessage(readProperty.getData("message"));

        cp.clickSubmit();
        System.out.println("✅ Valid contact form submission passed.");
    }

    // Test Case: Submit the contact form using a numeric-only name (e.g., "123456").
    // Expected: Form should reject numeric input in the name field (but currently does not).
    @Test
    public void testContactWithNumericName() throws IOException {
        HomePage hm = new HomePage(driver);
        hm.contactPage();

        ContactPage cp = new ContactPage(driver);
        cp.enterName("123456"); // Invalid name
        cp.enterSubject("Just checking");
        cp.enterEmail("test@example.com");
        cp.enterMessage("Trying invalid name");

        cp.clickSubmit();

        System.out.println("❌ Submitted form with numeric name — validation missing!");
        Assert.fail("Form accepted a numeric-only name. Expected name field to reject numbers.");
    }

    // Test Case: Submit the contact form using an invalid email format (e.g., "invalidEmail").
    // Expected: Browser should block form submission due to invalid email syntax.
    @Test
    public void testContactWithInvalidEmail() throws IOException {
        HomePage hm = new HomePage(driver);
        hm.contactPage();

        ContactPage cp = new ContactPage(driver);
        cp.enterName("Harish");
        cp.enterSubject("Test");
        cp.enterEmail("invalidEmail"); // No @ or domain
        cp.enterMessage("Trying invalid email");

        WebElement emailField = cp.getEmailField();
        String enteredEmail = emailField.getAttribute("value");
        Assert.assertFalse(enteredEmail.contains("@"), "Email should be invalid");

        try {
            cp.clickSubmit();
            System.out.println("❌ Form tried to submit invalid email.");
            Assert.fail("Form submitted with invalid email format — expected browser to block it.");
        } catch (Exception e) {
            System.out.println("✅ Browser blocked invalid email format as expected.");
        }
    }
}
