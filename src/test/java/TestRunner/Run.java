package TestRunner;

//import org.junit.runner.RunWith;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;


//@RunWith(Cucumber.class)
@CucumberOptions(
		
		features = ".//Feature/Customer.feature",
	    //features = ".//Feature/",
		glue="StepDefination",
		dryRun = false,
		monochrome = true,
		tags = "@Sanity",
		//plugin = {"pretty","html:target/cucumber-reports/reports1.html"}
		plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)

public class Run extends AbstractTestNGCucumberTests {
	
	}
