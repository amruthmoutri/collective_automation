package com.collective.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CanaryWharf extends HomePage{
    public CanaryWharf(WebDriver driver) {
        super(driver);
    }

    private final By BOOK_NOW_CENTER_360_DEGREE_BUTTON = By.cssSelector("div[class='generic-hero__buttons--wrapper']");
    private final By COOKIE_BANNER = By.cssSelector("section[class='cookie-banner']");
    private final By MAIN_TITLE = By.cssSelector("img[class=' lazyloaded']");
    private final By CONTENT = By.cssSelector("div[id='generic-hero-image']");
    private final String URL = "/locations/canary-wharf";
    private final String TITLE = "City Of London Co-Living | The Collective";

    public void waitUntilLoaded(){
        new WebDriverWait(driver, 20).pollingEvery(Duration.ofMillis(50)).until(ExpectedConditions
                .and(ExpectedConditions.presenceOfElementLocated((BOOK_NOW_CENTER_360_DEGREE_BUTTON)),
                        ExpectedConditions.presenceOfElementLocated(COOKIE_BANNER),
                        ExpectedConditions.visibilityOfElementLocated(MAIN_TITLE)));
    }

    public boolean verifyUserIsOnCanaryWharf(){
        new WebDriverWait(driver, 15).until(ExpectedConditions.and(
                ExpectedConditions.presenceOfElementLocated(COOKIE_BANNER),
                ExpectedConditions.elementToBeClickable(CONTENT)));
        this.waitUntilLoaded();
        return driver.getCurrentUrl().contains(URL);


    }
}
