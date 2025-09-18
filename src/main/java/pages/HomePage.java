package pages;

import base.BasePage;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class HomePage extends BasePage {
    // Locators
    private static final String GET_STARTED_BUTTON = "text=Get started";
    private static final String DOCS_LINK = "text=Docs";
    private static final String API_LINK = "text=API";
    private static final String COMMUNITY_LINK = "text=Community";
    private static final String PAGE_TITLE = "h1";
    private static final String SEARCH_BOX = "[placeholder='Search docs']";

    public HomePage(Page page) {
        super(page);
    }

    @Step("Click Get Started button")
    public void clickGetStarted() {
        waitForElement(GET_STARTED_BUTTON);
        click(GET_STARTED_BUTTON);
    }

    @Step("Click Docs link")
    public void clickDocs() {
        click(DOCS_LINK);
    }

    @Step("Click API link")
    public void clickAPI() {
        click(API_LINK);
    }

    @Step("Click Community link")
    public void clickCommunity() {
        click(COMMUNITY_LINK);
    }

    @Step("Get page title text")
    public String getPageTitle() {
        waitForElement(PAGE_TITLE);
        return getText(PAGE_TITLE);
    }

    @Step("Search in docs")
    public void searchDocs(String searchTerm) {
        if (isVisible(SEARCH_BOX)) {
            fill(SEARCH_BOX, searchTerm);
            page.keyboard().press("Enter");
        }
    }

    @Step("Verify home page is loaded")
    public boolean isHomePageLoaded() {
        return isVisible(GET_STARTED_BUTTON) &&
                getTitle().contains("Playwright");
    }

    @Step("Navigate to home page")
    public void navigateToHomePage() {
        navigateTo("https://playwright.dev");
        waitForPageLoad();
    }
}
