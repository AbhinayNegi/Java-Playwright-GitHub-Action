package base;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    // Common page operations
    protected void click(String selector) {
        page.locator(selector).click();
    }

    protected void fill(String selector, String text) {
        page.locator(selector).fill(text);
    }

    protected String getText(String selector) {
        return page.locator(selector).textContent();
    }

    protected boolean isVisible(String selector) {
        return page.locator(selector).isVisible();
    }

    protected void waitForElement(String selector) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.VISIBLE));
    }

    protected void waitForElementToBeHidden(String selector) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.HIDDEN));
    }

    protected Locator getElement(String selector) {
        return page.locator(selector);
    }

    protected void navigateTo(String url) {
        page.navigate(url);
    }

    protected String getCurrentUrl() {
        return page.url();
    }

    protected String getTitle() {
        return page.title();
    }

    // Wait for page to load completely
    protected void waitForPageLoad() {
        page.waitForLoadState();
    }

    // Scroll operations
    protected void scrollToElement(String selector) {
        page.locator(selector).scrollIntoViewIfNeeded();
    }

    protected void scrollToBottom() {
        page.evaluate("window.scrollTo(0, document.body.scrollHeight)");
    }

    // Dropdown operations
    protected void selectByValue(String selector, String value) {
        page.locator(selector).selectOption(value);
    }

    protected void selectByText(String selector, String text) {
        page.locator(selector).selectOption(new String[]{text});
    }

    // Frame operations
    protected void switchToFrame(String selector) {
        page.frameLocator(selector);
    }
}
