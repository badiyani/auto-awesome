package com.example.demo1;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class MainPageTest {
    public WebDriver driver;
    MainPage mainPage = new MainPage(driver);
	MainPageElements ele = new MainPageElements();
	MainPageConstants cons = new MainPageConstants();

    @BeforeClass
    public void setUp() {
    	mainPage.browserUrl(cons.browser, cons.url);
    }

    @AfterClass
    public void tearDown() {
        mainPage.closeBrowser();
    }

    @Test(priority = 1)
    public void retrieveData() {
    	
    	String retrieveText = mainPage.getText(ele.findText);
    	mainPage.highlightElement(ele.findText);
    	System.out.println("The Retrieved text is "+retrieveText);
    	Assert.assertEquals(retrieveText, cons.expectedRetrievedText);
    	
    	String bannerName = mainPage.getAttribute(ele.bannerTitle, "name");
    	mainPage.highlightElement(ele.bannerTitle);
    	System.out.println("The Banner name is "+bannerName);
    	Assert.assertEquals(bannerName, cons.expectedBannerName);
    }

    @Test(priority = 2)
    public void navitageToPage() {
        mainPage.navigateTo(cons.navigateUrl);
        Assert.assertTrue(mainPage.getUrl().equals(cons.navigateUrl), "URL Validation failed");
    }

}
