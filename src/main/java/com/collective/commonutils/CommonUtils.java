package com.collective.commonutils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtils {

    public static void waitForElementByLocator(By locator, int duration, WebDriver driver){
        new WebDriverWait(driver, duration).until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(locator));
    }

    public static void waitForElementByElementVisibility(WebElement element, int duration, WebDriver driver){
        new WebDriverWait(driver, duration).until(ExpectedConditions
                .visibilityOfAllElements(element));

    }
}
