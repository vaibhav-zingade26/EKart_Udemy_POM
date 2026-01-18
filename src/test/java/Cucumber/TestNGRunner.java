package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Cucumber",
        glue = "StepDefinitions", monochrome = true,
        tags = "@Today",
        plugin = {"html:src/test/java/Cucumber/report.html",
                "json:src/test/java/Cucumber/cucumber.json",
                "junit:src/test/java/Cucumber/cucumber.xml",
                "pretty",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"})

public class TestNGRunner extends AbstractTestNGCucumberTests {


}
