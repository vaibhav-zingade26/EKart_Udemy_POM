package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/java/Cucumber", glue = "StepDefinations", monochrome = true,tags = "@NegativeTests", plugin ={"html:src/test/java/Cucumber/report.html"} )
public class TestNGRunner extends AbstractTestNGCucumberTests {


}
