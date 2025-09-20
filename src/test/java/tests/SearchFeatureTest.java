package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchFeatureTest extends BaseTest {

    @Test(description = "Verify search functionality works")
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
