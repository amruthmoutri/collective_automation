package com.collective.cucumber.step_defintions;

import com.collective.selenium.pages.*;
import com.collective.selenium.webdriver.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.hu.Ha;
import io.cucumber.java8.En;
import io.cucumber.java8.Th;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;
import java.util.Map;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GenericStepDefs implements En {

    private final WebDriver driver = BrowserDriver.getDriver();
    private final HomePage homePage = new HomePage(driver);
    private OldOak oldOak = null;
    private CanaryWharf canaryWharf = null;
    private PaperFactory paperFactory = null;
    private Harrow harrow = null;

    Logger log = LogManager.getLogger(GenericStepDefs.class);

    public GenericStepDefs() throws IOException {

        Given("^I am on homepage$", ()->{
            assertThat("User is not on homepage ", true, is(equalTo(homePage.verifyUserIsOnHomePage())));
        });

        When("^I click on navigation header \"([^\"]*)\"$", (String headerName)->{
            homePage.clickOnHeader(headerName);
        });

        Then("^I expect these locations$",(DataTable fields)->{
            List<String> locationsList = fields.asList();
            List<String>actualList = homePage.getAllAvailableLocations();
            log.debug(actualList);
            log.debug(locationsList);
            assertThat("Not all locations are being shown", actualList.containsAll(locationsList));
        });

        Then("^Verify navigation items are visible$", (DataTable fields) -> {
          List<String>expectedFields = fields.asList();
          assertThat("links are not available ",expectedFields.containsAll(homePage.getHeaderLinks()));
        });

        Then("^I verify button book now is visible$", ()->{
            assertThat("Button not available book now" , true , is(equalTo(homePage.isBookNowButtonAvailable())));
        });
        Then("^I verify view button is displayed in location \"([^\"]*)\"$", (String locationName) -> {
            assertThat("view button is not visible", homePage.verifyViewButtonInLocations(locationName), is(equalTo(true)));
        });
        And("^I click on location Old Oak$", () -> {
            oldOak = homePage.clickOnOldOakLocation();
        });
        And("^I click on location Canary Wharf$", () -> {
           canaryWharf =  homePage.clickOnCanaryWharfLocation();
        });
        And("^I click on location Paper Factory$", () -> {
            paperFactory =  homePage.clickOnPaperFactoryLocation();
        });
        And("^I click on location Harrow$", () -> {
            harrow =  homePage.clickOnHarrowLocation();
        });
        When("^I click on book now button$", homePage::clickBookNowButton);

        Then("^I enter fields in the form$", (DataTable fields)->{
            Map<String,String> table = fields.asMap(String.class, String.class);
            homePage.fillStayForAFewNightForm(table);
        });
        When("^I click on button \"([^\"]*)\"$", homePage::clickOnHomePageButton);

        Then("^I verify form \"([^\"]*)\" is active$", (String expectedFormActive) -> {
              String formActive = homePage.verifyFieldFewNightsNowButton();
              assertThat("From field is not active", formActive.equalsIgnoreCase(expectedFormActive));
        });
        Then("^I am on Old Oak page$", () -> {
            assertThat("User is not on Old Oak page ", true, is(equalTo(oldOak.verifyUserIsOnPage())));
        });
        Then("^I am on Canary Wharf page$", () -> {
            assertThat("User is not on Canary Wharf page", true, is(equalTo(canaryWharf.verifyUserIsOnCanaryWharf())));
        });
        Then("^I am on Paper Factory page$", () -> {
            assertThat("User is not on Paper factory page", true, is(equalTo(paperFactory.verifyUserIsOnPageFactoryPage())));
        });
        Then("^I am on Harrow page$", () -> {
                assertThat("User is not on Harrow page", true, is(equalTo(harrow.verifyUserIsOnHarrowPage())));
        });


    }

}
