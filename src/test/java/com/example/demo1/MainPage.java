package com.example.demo1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MainPage {
	
	public WebDriver driver;
	
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void setupDriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}		
	}
    
    public void browserUrl(String browser,String url) {
		setupDriver(browser);
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void closeBrowser() {
		driver.close();
	}
	
	public static void log(String message) {
		Reporter.log(message, true);
	}
    
	public void navigateTo(String url) {
		driver.navigate().to(url);
		log("Navigate to " + url);
	}
    
    public String getText(By locator) {
		return driver.findElement(locator).getText();
	}
    
    public String getUrl() {
    	return driver.getCurrentUrl();
    }
    
    public String getAttribute(By locator, String Attribute) {
		return driver.findElement(locator).getAttribute(Attribute);
	}
    
    public void highlightElement(By locator) {
    	WebElement element = driver.findElement(locator);
        for (int i = 0; i <2; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 2px solid yellow;");
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
            }
        }
}
