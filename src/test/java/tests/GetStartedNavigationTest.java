package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetStartedNavigationTest extends BaseTest {

    @Test(description = "Verify navigation to Get Started page")
    public void testGetStartedNavigation() {
        homePage.clickGetStarted();
        waitFor(2);

        String currentUrl = page.url();
        Assert.assertTrue(currentUrl.contains("intro"),
                "Should navigate to introduction page");

        takeScreenshot("get_started_page");
    }
}
