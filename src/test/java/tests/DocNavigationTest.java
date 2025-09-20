package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DocNavigationTest extends BaseTest {

    @Test(description = "Verify navigation to Docs page", groups = {"smoke"})
    public void testDocsNavigation() {
        homePage.clickDocs();
        waitFor(2);

        String currentUrl = page.url();
        Assert.assertTrue(currentUrl.contains("docs") || currentUrl.contains("intro"),
                "Should navigate to docs page");

        takeScreenshot("docs_page");
    }
}
