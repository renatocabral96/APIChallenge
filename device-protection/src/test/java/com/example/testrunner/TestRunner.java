package com.example.testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.example.stepdefinitions",
    plugin = { "pretty", "html:target/cucumber-html-reports", "json:target/cucumber-json-reports/Cucumber.json" },
    monochrome = true,
    publish = true,
    tags = "@ThiReq"
    
)
public class TestRunner {
}
