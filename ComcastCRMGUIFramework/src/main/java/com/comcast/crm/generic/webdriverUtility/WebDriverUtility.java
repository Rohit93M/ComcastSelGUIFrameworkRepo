package com.comcast.crm.generic.webdriverUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void maximizeWindow(WebDriver driver) {
        //You have to pass browser session id in the parameter
		driver.manage().window().maximize();
	}
	
	public void minimizeWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}

	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void waitForElementPresent(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitTillElementToBeClickable(WebDriver driver,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForATitle(WebDriver driver,String title) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.titleContains(title));
	}

	public void waitForAUrl(WebDriver driver,String url) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.urlContains(url));
	}

	public void switchToWindowOnUrl(WebDriver driver, String partialUrl) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> iterator = set.iterator();
		// Create iterator because Set collection is not index based. You have to take
		// the help of iterator to
		// capture data from the set

		while (iterator.hasNext()) {
			// using while loop we will capture the first window id, and then switch to that
			// window id
			String windowID = iterator.next();
			driver.switchTo().window(windowID);
			// using url capture the current url and compare it with the argument you have
			// passed
			String actualUrl = driver.getCurrentUrl();
			if (actualUrl.contains(partialUrl)) {
				break;
			}
		}
	}

	public void switchToWindowOnTitle(WebDriver driver, String partialTitle) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> iterator = set.iterator();
		// Create iterator because Set collection is not index based. You have to take
		// the help of iterator to
		// capture data from the set

		while (iterator.hasNext()) {
			// using while loop we will capture the first window id, and then switch to that
			// window id
			String windowID = iterator.next();
			driver.switchTo().window(windowID);
			// using url capture the current title and compare it with the argument you have
			// passed
			String actualTitle = driver.getTitle();
			if (actualTitle.contains(partialTitle)) {
				break;
			}
		}
	}

	public void switchtoFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	public void switchtoFrame(WebDriver driver, String nameID) {
		driver.switchTo().frame(nameID);
	}

	public void switchtoFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void switchToAlertAndDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public Select dropdown(WebElement element) {
		Select sel = new Select(element);
		return sel;
	}

	public void handleDropdown(WebElement element, String text) {
		dropdown(element).selectByVisibleText(text);
	}

	public void handleDropdown(WebElement element, int index) {
		dropdown(element).selectByIndex(index);
	}
	
	public void getAllOptionsFromDropdown(WebElement element) {
		List<WebElement> options = dropdown(element).getOptions();
		for (WebElement option : options) 
		{
			String text = option.getText();
			System.out.println(text);
		}
	}

	public Actions action(WebDriver driver) {
		Actions act = new Actions(driver);
		return act;
	}

	public void mousehoverOnElement(WebDriver driver, WebElement element) {
		action(driver).moveToElement(element).perform();
	}

	public void click(WebDriver driver, WebElement element) {
		action(driver).click().perform();
	}

	public void doubleClick(WebDriver driver, WebElement element) {
		action(driver).doubleClick().perform();
	}

	public void contextClick(WebDriver driver, WebElement element) {
		action(driver).doubleClick().perform();
	}

	public void dragAndDrop(WebDriver driver, WebElement src, WebElement dest) {
		action(driver).dragAndDrop(src, dest).perform();
	}

	public void scrollUntilElementToBeVisible(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int y = element.getLocation().getY();
		// js.executeScript("window.ScrollBy(0,"+y+")");
		js.executeScript("arguments[0].scrollIntoView()", element);
	}

	public void clickOnElementWithJS(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);
	}
	
	public String takeScreenShot(WebDriver driver,String screenShotName) throws IOException {
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String timeStamp=LocalDateTime.now().toString().replace(':', '-');
		File dst = new File("./screenshots/"+screenShotName+""+timeStamp+".png");
		FileHandler.copy(src, dst);
	    return screenShotName;
	}
	
	public WebDriver checkDriver(String browser) {
		WebDriver driver;
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")){
			driver= new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}else {
			driver=new ChromeDriver();
		}
		return driver;
	}
}