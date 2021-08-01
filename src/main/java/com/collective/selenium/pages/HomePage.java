package com.collective.selenium.pages;

import com.collective.commonutils.CommonUtils;
import com.collective.exception.ButtonNotAvailableException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomePage {
    public WebDriver driver;
    private final static String URL = "thecollective.com";
    private final static String TITLE = "Co-living | The New Way To Live | The Collective";
    private final static By COOKIE_BANNER = By.cssSelector("section[class='cookie-banner']");
    private final static By HOMEPAGE_IMAGE = By.cssSelector("div[id='homepage-hero-image']");
    private final static By HEADER_LINKS = By.cssSelector("nav[class='main-header__navigation-wrapper'] li");
    private final static By BOOK_NOW_BUTTON = By.cssSelector("nav[class='main-header__navigation-wrapper'] button");
    private final static By BOOK_NOW_NAVIGATION_LINKS = By.cssSelector("div[class='booking-nav'] div");
    private final static By LOCATION_DROPDOWN_FIELD = By.cssSelector("div[id='v-select']");
    private final static By LOCATION_REQUIRED_DROPDOWN_LIST = By.cssSelector("div[class='input-select__dropdown'] ul li");
    private final static By CLICK_CALENDER = By.cssSelector("button[class='input-date__trigger']");
    private final static By DATE_PICKER = By.cssSelector("span[class^='cell day'] :not(span[class='cell day-header'])");
    private final static By MONTH_PICKER = By.cssSelector("span[class='month']");
    private final static By MONTH_PICKER_SUBMIT_BUTTON = By.cssSelector("button[class='btn--dark-green collective-community__button']");
    private final static By MONTH_PICKER_NEXT_BUTTON = By.cssSelector("span[class='next']");
    private final static By HEADER_OUR_LOCATION = By.cssSelector("a[href='/locations']");
    private final static By LOCATION_LIST = By.cssSelector("span[class='navigation__primary-second-title']");
    private final static By HEADER_ABOUT_US = By.cssSelector("span[class='next']");
    private final static By HEADER_THE_JOURNAL = By.cssSelector("span[class='next']");
    private final static By BUTTON_SUBMIT = By.cssSelector("button[class='btn--dark-green submit']");
    private final static By LOCATION_OLD_OAK = By.cssSelector("a[href='/locations/old-oak'] span[class='navigation__primary-second-title']");
    private final static By LOCATION_CANARY_WHARF = By.cssSelector("a[href='/locations/canary-wharf'] span[class='navigation__primary-second-title']");
    private final static By LOCATION_PAPER_FACTORY = By.cssSelector("a[href='/locations/paper-factory'] span[class='navigation__primary-second-title']");
    private final static By LOCATION_HARROW = By.cssSelector("a[href='/locations/harrow'] span[class='navigation__primary-second-title']");
    private final static By HOMEPAGE_BUTTON_STAY_FOR_FEW_NIGHTS = By.cssSelector("button[class='homepage-hero__button']");
    private final static By FORM_FIELD_ACTIVE = By.cssSelector("div[class='booking-nav-item active']");
    private final static By BOOKING_SIDE_BAR = By.cssSelector("div[class='v--modal-box booking-sidebar']");


    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void waitUntilLoaded() {
       new WebDriverWait(driver, 10).until((ExpectedCondition<Boolean>) wd ->
               ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public List<String> getHeaderLinks(){
        List<WebElement> elements = driver.findElements(HEADER_LINKS);
        List<String> actualHeaderLinks = new ArrayList<>();
        elements.forEach(ele -> {
            actualHeaderLinks.add(ele.getText());
        });
        return actualHeaderLinks;
    }

    public boolean verifyUserIsOnHomePage(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.and(
                ExpectedConditions.presenceOfElementLocated(COOKIE_BANNER),
                ExpectedConditions.elementToBeClickable(HOMEPAGE_IMAGE)));

        return  driver.getCurrentUrl().contains(URL)
                && driver.getTitle().equalsIgnoreCase(TITLE);

    }

    public boolean isBookNowButtonAvailable() throws ButtonNotAvailableException {
        try {
            return driver.findElement(BOOK_NOW_BUTTON).isDisplayed();
        } catch (Exception e){
            throw new ButtonNotAvailableException("book now");
        }
    }


    public List<String> bookNowButtonHasNavigationLinks(){
        List<String>actualNavLinks= new ArrayList<>();
        driver.findElements(BOOK_NOW_NAVIGATION_LINKS).forEach(navLinks->{
            actualNavLinks.add(navLinks.getText());
        });
        return actualNavLinks;
    }

    public void  clickBookNowButton(){
        driver.findElement(BOOK_NOW_BUTTON).click();
        CommonUtils.waitForElementByLocator(BOOKING_SIDE_BAR, 10, driver);
    }

    public void fillStayForAFewNightForm(Map<String, String> fillForm) {
        for (Map.Entry<String, String> entry : fillForm.entrySet()) {
            String key = entry.getKey();
            switch (key) {
                case "Location":
                    driver.findElement(LOCATION_DROPDOWN_FIELD).click();

                    CommonUtils.waitForElementByElementVisibility
                            (driver.findElement(LOCATION_REQUIRED_DROPDOWN_LIST), 10, driver);

                    for (WebElement element : driver.findElements(LOCATION_REQUIRED_DROPDOWN_LIST)) {
                        if (element.getText().equalsIgnoreCase(entry.getValue())) {
                            element.click();
                            break;
                        }
                    }
                    break;
                case "Arrival Date":
                case "Departure Date":
                    monthPicker(entry.getValue());
                    driver.findElement(MONTH_PICKER_SUBMIT_BUTTON).click();
                    break;
            }

        }
        driver.findElement(BUTTON_SUBMIT).click();
    }

    public void enterDate(String date) {
        driver.findElement(CLICK_CALENDER).click();
        List<WebElement> dates = driver.findElements(DATE_PICKER);
        for (WebElement element : dates) {
            if (element.getText().equalsIgnoreCase(date)) {
                element.click();
                break;
            }
        }
    }

    public void monthPicker(String date){
        String[] dateSplit = date.split("/");
        List<WebElement> months = driver.findElements(MONTH_PICKER);
        for (WebElement element : months) {
            if (element.getText().equalsIgnoreCase(dateSplit[1])) {
                break;
            } else if (!(element.getText().equalsIgnoreCase(dateSplit[1]))) {
                driver.findElement(MONTH_PICKER_NEXT_BUTTON).click();
            }
        }
        enterDate(dateSplit[0]);
    }

    public void clickOnHeader(String headerName) throws InterruptedException {
        switch (headerName){
            case "Our location":
               Thread.sleep(2000);
                driver.findElement(HEADER_OUR_LOCATION).click();
                new WebDriverWait(driver, 5).until(ExpectedConditions.or(
                        ExpectedConditions.attributeContains(HEADER_OUR_LOCATION,
                        "class","active main-header__navigation-link"),
                        ExpectedConditions.attributeContains(HEADER_OUR_LOCATION,
                                "class", "main-header__navigation-link"),
                        ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='navigation__pane navigation__pane--secondary']"))));
                break;
            case "about us":
                driver.findElement(HEADER_ABOUT_US).click();
                new WebDriverWait(driver, 5).until(ExpectedConditions.attributeToBe(HEADER_OUR_LOCATION,
                        "class","main-header__navigation-link active"));
                break;
            case "The Journal":
                driver.findElement(HEADER_THE_JOURNAL).click();
                new WebDriverWait(driver, 5).until(ExpectedConditions.attributeToBe(HEADER_OUR_LOCATION,
                        "class","main-header__navigation-link active"));
                break;
        }
    }

    public List<String> getAllAvailableLocations() {
        List<WebElement> locationList = driver.findElements(LOCATION_LIST);
        List<String>locationsAvailable = new ArrayList<>();
        locationList.forEach(location->{
            locationsAvailable.add(location.getText());
            System.out.println(locationsAvailable);
        });
        return locationsAvailable;
    }

    public boolean verifyViewButtonInLocations(String locationName) {
        String element  = "a[href='/locations/"+locationName+"'] div[class='navigation__primary-second-banner'] span";
        String viewText = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].outerText;",
                driver.findElement(By.cssSelector(element)));
        return viewText.equalsIgnoreCase("view");
        }

    public OldOak clickOnOldOakLocation() {
      driver.findElement(LOCATION_OLD_OAK).click();
      OldOak oldOak = new OldOak(driver);
      oldOak.waitUntilLoaded();
      return oldOak;
    }

    public CanaryWharf clickOnCanaryWharfLocation() throws InterruptedException {
        driver.findElement(LOCATION_CANARY_WHARF).click();
        CanaryWharf canaryWharf = new CanaryWharf(driver);
        canaryWharf.waitUntilLoaded();
        return canaryWharf;
    }

    public PaperFactory clickOnPaperFactoryLocation() {
        driver.findElement(LOCATION_PAPER_FACTORY).click();
        PaperFactory paperFactory = new PaperFactory(driver);
        paperFactory.waitUntilLoaded();
        return paperFactory;
    }

    public Harrow clickOnHarrowLocation() {
        driver.findElement(LOCATION_HARROW).click();
        Harrow harrow = new Harrow(driver);
        harrow.waitUntilLoaded();
        return harrow;
    }

    public void clickOnHomePageButton(String buttonName) {
        WebElement elementHomePage = driver.findElement(HOMEPAGE_BUTTON_STAY_FOR_FEW_NIGHTS);
        if(buttonName.contains("few nights")){
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", elementHomePage);
        } else{
            ((JavascriptExecutor)driver).executeScript("arguments[1].click();", elementHomePage);
        }
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[class='v--modal-box booking-sidebar']")));
    }

    public String verifyFieldFewNightsNowButton() {
         return driver.findElement(FORM_FIELD_ACTIVE).getText();
    }
}
