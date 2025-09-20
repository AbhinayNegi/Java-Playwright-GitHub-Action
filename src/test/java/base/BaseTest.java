package base;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

import java.nio.file.Paths;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected HomePage homePage;

    private static final String BROWSER_TYPE = System.getProperty("browser", "chromium");
    private static final boolean HEADLESS = Boolean.parseBoolean(System.getProperty("headless", "true"));
    private static final String BASE_URL = System.getProperty("baseUrl", "https://playwright.dev");

    @BeforeClass
    public void setUp() {
        // Create Playwright instance
        playwright = Playwright.create();

        // Launch browser based on system property
        BrowserType browserType = getBrowserType();
        browser = browserType.launch(new BrowserType.LaunchOptions()
                .setHeadless(HEADLESS)
                .setSlowMo(50));

        // Create browser context with common settings
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
                .setRecordVideoDir(Paths.get("target/videos/"))
                .setRecordVideoSize(1920, 1080));

        // Enable tracing for debugging
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    @BeforeMethod
    public void setUpTest() {
        // Create new page for each test
        page = context.newPage();
        page.navigate(BASE_URL);

        initializePageObjects();
    }

    public void initializePageObjects() {
        homePage = new HomePage(page);
    }

    @AfterMethod
    public void tearDownTest() {
        if (page != null) {
            page.close();
        }
    }

    @AfterClass
    public void tearDown() {
        // Stop tracing and save
        if (context != null) {
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("target/traces/trace_" + System.currentTimeMillis() + ".zip")));
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }

    private BrowserType getBrowserType() {
        switch (BROWSER_TYPE.toLowerCase()) {
            case "firefox":
                return playwright.firefox();
            case "webkit":
            case "safari":
                return playwright.webkit();
            case "chromium":
            case "chrome":
            default:
                return playwright.chromium();
        }
    }

    // Utility method for taking screenshots
    protected void takeScreenshot(String fileName) {
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("target/screenshots/" + fileName + ".png"))
                .setFullPage(true));
    }

    // Utility method for waiting
    protected void waitFor(int seconds) {
        page.waitForTimeout(seconds * 1000);
    }
}
