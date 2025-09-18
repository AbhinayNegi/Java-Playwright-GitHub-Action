package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static ConfigManager instance;
    private Properties properties;

    private ConfigManager() {
        loadProperties();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    private void loadProperties() {
        properties = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) {
                properties.load(is);
            } else {
                System.err.println("config.properties file not found in classpath");
            }
        } catch (IOException e) {
            System.err.println("Error loading config.properties: " + e.getMessage());
        }
    }

    public String getProperty(String key) {
        // Check system properties first, then config file
        String value = System.getProperty(key);
        if (value == null) {
            value = properties.getProperty(key);
        }
        return value;
    }

    public String getProperty(String key, String defaultValue) {
        String value = getProperty(key);
        return value != null ? value : defaultValue;
    }

    public boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }

    public int getIntProperty(String key, int defaultValue) {
        String value = getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // Specific configuration getters
    public String getBrowser() {
        return getProperty("browser", "chromium");
    }

    public boolean isHeadless() {
        return getBooleanProperty("headless", true);
    }

    public String getBaseUrl() {
        return getProperty("baseUrl", "https://playwright.dev");
    }

    public int getTimeout() {
        return getIntProperty("timeout", 30000);
    }

    public int getViewportWidth() {
        return getIntProperty("viewport.width", 1920);
    }

    public int getViewportHeight() {
        return getIntProperty("viewport.height", 1080);
    }

    public boolean isScreenshotOnFailure() {
        return getBooleanProperty("screenshot.onFailure", true);
    }

    public boolean isVideoRecord() {
        return getBooleanProperty("video.record", true);
    }

    public boolean isTraceEnabled() {
        return getBooleanProperty("trace.enabled", true);
    }

    public int getParallelThreads() {
        return getIntProperty("parallel.threads", 3);
    }

    public String getEnvironment() {
        return getProperty("environment", "dev");
    }
}
