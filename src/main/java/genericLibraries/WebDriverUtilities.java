package genericLibraries;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtilities {
	//drop down
	public void dropDown(WebElement dropdown, String text) {
		Select s = new Select(dropdown);
		s.selectByVisibleText(text);
	}
	
	public void dropDown(WebElement dropdown, int index) { //Overloading
		Select s = new Select(dropdown);
		s.selectByIndex(index);
	}
	
	//mouse hover
	public void mouseHover(WebDriver driver, WebElement ele) {
		Actions a = new Actions(driver); 
		a.moveToElement(ele).perform();
	}
	
	public void rightClick(WebDriver driver, WebElement ele) {
		Actions a = new Actions(driver); 
		a.contextClick(ele).perform();
	}
	
	public void doubleClick(WebDriver driver, WebElement ele) {
		Actions a = new Actions(driver); 
		a.doubleClick(ele).perform();
	}
	
	public void dragandDrop(WebDriver driver, WebElement src, WebElement dest) {
		Actions a = new Actions(driver); 
		a.dragAndDrop(src, dest);
	}
	
	public void switchFrame(WebDriver driver) {
		driver.switchTo().frame(0);
	}
	
	public void switchFrameBack(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void alertPopupAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void alertPopupDismis(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public void scroll(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
	}
	
	public List<String> getDropdownOptions(WebElement dropdown) {
		Select select = new Select(dropdown);
		List<WebElement> options = select.getOptions();
		List<String> optionTexts = new ArrayList<>();

		for (WebElement option : options) {
			optionTexts.add(option.getText().trim());
		}
		return optionTexts;
	}

	
	public List<String> getExpectedDropdownList(String propertyKey, ReadingDataPropertyFile readProperty) throws FileNotFoundException, IOException {
		String rawData = readProperty.getData(propertyKey);
		String[] items = rawData.split(",");
		List<String> expectedList = new ArrayList<>();

		for (String item : items) {
			expectedList.add(item.trim());
		}
		return expectedList;
	}



}
