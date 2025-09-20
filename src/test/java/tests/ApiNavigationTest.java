package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiNavigationTest extends BaseTest {

    @Test(description = "Verify navigation to API page")
    public void testAPINavigation() {
        homePage.clickAPI();
        waitFor(2);

        String currentUrl = page.url();
        Assert.assertTrue(currentUrl.contains("api"),
                "Should navigate to API page");

        takeScreenshot("api_page");
    }
}
