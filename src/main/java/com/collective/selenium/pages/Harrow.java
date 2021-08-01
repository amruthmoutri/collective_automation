package com.collective.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Harrow extends HomePage {
    public Harrow(WebDriver driver) {
        super(driver);
    }

    private final By BOOK_NOW_CENTER_360_DEGREE_BUTTON = By.cssSelector("div[class='generic-hero__buttons--wrapper']");
    private final By COOKIE_BANNER = By.cssSelector("section[class='cookie-banner']");
    private final By MAIN_TITLE = By.cssSelector("img[class=' lazyloaded']");
    private final String URL = "/locations/harrow";
    private final String TITLE = "Harrow | City Of London Co-Living | The Collective";

    public void waitUntilLoaded(){
        new WebDriverWait(driver, 20).pollingEvery(Duration.ofMillis(50)).until(ExpectedConditions
                .and(ExpectedConditions.presenceOfElementLocated((BOOK_NOW_CENTER_360_DEGREE_BUTTON)),
                        ExpectedConditions.presenceOfElementLocated(COOKIE_BANNER),
                        ExpectedConditions.presenceOfElementLocated(MAIN_TITLE)));
    }

    public boolean verifyUserIsOnHarrowPage(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(COOKIE_BANNER));
        return  driver.getCurrentUrl().contains(URL)
                && driver.getTitle().equalsIgnoreCase(TITLE);


    }
}
