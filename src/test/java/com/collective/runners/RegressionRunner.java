package com.collective.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"com.collective.cucumber"},
        features = {"classpath:features"},
        plugin = {"html:build/Login/cucumber-html-report.html"},
        tags = "@sanity or @now"
)
public class RegressionRunner {}
