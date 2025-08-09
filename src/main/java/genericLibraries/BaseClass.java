package genericLibraries;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
	public WebDriver driver;
	public ReadingDataPropertyFile readProperty = new ReadingDataPropertyFile();
	public WebDriverUtilities utilities = new WebDriverUtilities();
	
	@BeforeMethod
	public void openApp() throws FileNotFoundException, IOException {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(readProperty.getData("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterMethod
	public void closeApp() {
		driver.quit();
	}

}
