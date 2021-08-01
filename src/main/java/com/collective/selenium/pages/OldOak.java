package com.collective.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OldOak extends HomePage{


    private final By BOOK_NOW_CENTER_360_DEGREE_BUTTON = By.cssSelector("div[class='generic-hero__buttons--wrapper']");
    private final By COOKIE_BANNER = By.cssSelector("section[class='cookie-banner']");
    private final By MAIN_TITLE = By.cssSelector("img[class=' lazyloaded']");
    private final String URL = "/locations/old-oak";
    private final String TITLE = "The Collective Old Oak | Co-Living London | Limited Offer 2 Months Rent Free";

    public OldOak(WebDriver driver){
        super(driver);
    }

    public void waitUntilLoaded(){
      new WebDriverWait(driver, 20).pollingEvery(Duration.ofMillis(50)).until(ExpectedConditions
      .and(ExpectedConditions.presenceOfElementLocated((BOOK_NOW_CENTER_360_DEGREE_BUTTON)),
              ExpectedConditions.presenceOfElementLocated(COOKIE_BANNER),
              ExpectedConditions.presenceOfElementLocated(MAIN_TITLE)));
    }

    public boolean verifyUserIsOnPage(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(COOKIE_BANNER));
        System.out.println();
        return  driver.getCurrentUrl().contains(URL)
                && driver.getTitle().equalsIgnoreCase(TITLE);

    }


}
