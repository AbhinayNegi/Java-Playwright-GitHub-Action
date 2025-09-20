package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test(description = "Verify that home page loads correctly and displays expected elements", groups = {"smoke"})
    public void testHomePageLoad() {
        Assert.assertTrue(homePage.isHomePageLoaded(),
                "Home page should be loaded with expected elements");

        String pageTitle = homePage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Playwright"),
                "Page title should contain 'Playwright'");

        takeScreenshot("home_page_loaded");
    }
}
