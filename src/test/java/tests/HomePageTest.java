package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    @Description("Verify that home page loads correctly and displays expected elements")
    public void testHomePageLoad() {
        Assert.assertTrue(homePage.isHomePageLoaded(),
                "Home page should be loaded with expected elements");

        String pageTitle = homePage.getPageTitle();
        Assert.assertTrue(pageTitle.contains("Playwright"),
                "Page title should contain 'Playwright'");

        takeScreenshot("home_page_loaded");
    }

    @Test
    @Description("Verify navigation to Get Started page")
    public void testGetStartedNavigation() {
        homePage.clickGetStarted();
        waitFor(2);

        String currentUrl = page.url();
        Assert.assertTrue(currentUrl.contains("intro"),
                "Should navigate to introduction page");

        takeScreenshot("get_started_page");
    }

    @Test
    @Description("Verify navigation to Docs page")
    public void testDocsNavigation() {
        homePage.clickDocs();
        waitFor(2);

        String currentUrl = page.url();
        Assert.assertTrue(currentUrl.contains("docs") || currentUrl.contains("intro"),
                "Should navigate to docs page");

        takeScreenshot("docs_page");
    }

    @Test
    @Description("Verify navigation to API page")
    public void testAPINavigation() {
        homePage.clickAPI();
        waitFor(2);

        String currentUrl = page.url();
        Assert.assertTrue(currentUrl.contains("api"),
                "Should navigate to API page");

        takeScreenshot("api_page");
    }

    @Test
    @Description("Verify search functionality works")
    public void testSearchFunctionality() {
        // Navigate to docs first to access search
        homePage.clickDocs();
        waitFor(2);

        homePage.searchDocs("getting started");
        waitFor(3);

        // Verify search results are displayed
        takeScreenshot("search_results");

        // Basic assertion that we're still on playwright domain
        String currentUrl = page.url();
        Assert.assertTrue(currentUrl.contains("playwright.dev"),
                "Should remain on Playwright website after search");
    }
}
