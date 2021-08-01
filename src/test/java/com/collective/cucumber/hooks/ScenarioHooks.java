package com.collective.cucumber.hooks;

import com.collective.selenium.webdriver.BrowserDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class ScenarioHooks {
    private static WebDriver driver;
    private Logger log = LogManager.getLogger(ScenarioHooks.class);

    static{
        try {
            driver = BrowserDriver.getDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void beforeHook(Scenario scenario){
        String scenarioName = scenario.getName();
        log.debug("Starting to run Scenario " + scenarioName);

    }

    @After
    public void afterHook(Scenario scenario){
        BrowserDriver.destroyDriver();
    }

}
