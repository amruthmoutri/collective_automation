package com.collective.selenium.webdriver;

import com.collective.configuration.Config;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class BrowserDriver {
    public static Map<Long, WebDriver> driverCache = new HashMap<>();
    private static WebDriver driver;
    private static Object WebDriver;
    public static ChromeOptions chromeOptions = null;

    private static Logger log = LogManager.getLogger(BrowserDriver.class);

    public static WebDriver getDriver() throws IOException {
        return getDriver(Thread.currentThread().getId());
    }

    public static WebDriver getDriver(long tag) throws IOException {
        WebDriver driver = driverCache.get(tag);
        if (driver == null) {
            return createDriver();
        }
        return driver;
    }

    public static void destroyDriver() {
        log.debug("Destroying driver for tag '" + Thread.currentThread().getId() + "'");
        WebDriver driver = driverCache.remove(Thread.currentThread().getId());
        if (driver != null) {
            driver.quit();
        }
    }

    private static WebDriver createDriver() throws IOException {
        String browserName = Config.getProperty("BROWSER_NAME");
        String seleniumHub = Config.getProperty("SELENIUM_HUB");
        String url = Config.getProperty("URL");

        Capabilities caps;
        if ("chrome".equalsIgnoreCase(browserName)) {
            caps = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            caps = caps.merge(options);
        } else if ("firefox".equalsIgnoreCase(browserName)) {
            caps = DesiredCapabilities.firefox();
            FirefoxProfile profile = new FirefoxProfile();
            FirefoxOptions options = new FirefoxOptions().setProfile(profile);
            caps = caps.merge(options);
        } else {
            throw new RuntimeException("Unknown browser: " + browserName);
        }

        log.debug("Starting selenium server local host - " + seleniumHub);
        driver = new RemoteWebDriver(new URL(seleniumHub), caps);
        driver.navigate().to(url);
        log.debug("Instantiating browser " + browserName);
        log.debug("Navigating to url " + url);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driverCache.put(Thread.currentThread().getId(), driver);

        return driver;
    }

}
